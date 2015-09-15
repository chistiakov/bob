/*
 * This file is part of Bob.
 *
 * Bob is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Bob is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser Public License for more details.
 *
 * You should have received a copy of the GNU Lesser Public License
 * along with Bob. If not, see <http://www.gnu.org/licenses/>.
 */

package net.radai.bob.model;

import net.radai.bob.model.rpc.RpcProgram;
import net.radai.bob.model.xdr.XdrConstant;
import net.radai.bob.model.xdr.XdrDeclaration;
import net.radai.bob.model.Identifiable;
import net.radai.bob.model.Scope;

import java.util.HashMap;
import java.util.Map;

/**
 * not thread safe
 * @author Radai Rosenblatt
 */
public class Namespace implements Scope {
    private final Map<String, XdrConstant> constants = new HashMap<>();
    private final Map<String, XdrDeclaration> types = new HashMap<>();
    private final Map<String, RpcProgram> programs = new HashMap<>();

    public void register(XdrConstant constant) {
        verifyUnused(constant.getIdentifier());
        constants.put(constant.getIdentifier(), constant);
    }

    public void register(XdrDeclaration type) {
        verifyUnused(type.getIdentifier());
        types.put(type.getIdentifier(), type);
    }

    public void register(RpcProgram program) {
        verifyUnused(program.getName());
        programs.put(program.getName(), program);
    }

    @Override
    public Identifiable resolve(String identifier) {
        XdrConstant constant = getConstant(identifier);
        if (constant != null) {
            return constant;
        }
        XdrDeclaration type = getType(identifier);
        if (type != null) {
            return type;
        }
        return getProgram(identifier);
    }

    @Override
    public Scope getParent() {
        return null; //top level
    }

    public XdrConstant getConstant(String name) {
        return constants.get(name);
    }

    public XdrDeclaration getType(String name) {
        return types.get(name);
    }

    public RpcProgram getProgram(String name) {
        return programs.get(name);
    }

    private void verifyUnused(String name) throws IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("must provide a valid name");
        }
        if (resolve(name) != null) {
            throw new IllegalArgumentException("name " + name + " already defined within scope");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!constants.isEmpty()) {
            sb.append(constants.size()).append(" consts");
        }
        if (sb.length() == 0) {
            return "empty";
        }
        return sb.toString();
    }
}
