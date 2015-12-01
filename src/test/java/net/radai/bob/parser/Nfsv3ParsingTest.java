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

package net.radai.bob.parser;

import net.radai.bob.util.Util;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created by Radai Rosenblatt
 */
public class Nfsv3ParsingTest {

    @Test
    public void testParsingNfsv3() throws Exception {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("nfs3.x")) {
            Util.parse(is);
        }
        //if we got here it means the parse didnt explode
    }
}
