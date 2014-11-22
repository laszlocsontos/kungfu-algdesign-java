/*
 * Copyright (C) 2014 - present, Laszlo Csontos
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package kungfu.algdesign.graph;

import org.junit.Test;

/**
 * @author lcsontos
 */
public class GraphParserTest {

  @Test(expected = InvalidGraphException.class)
  public void testParseInValidInput() throws Exception {
    new GraphParser().parse("1");
    new GraphParser().parse("1   ");
    new GraphParser().parse("1\t1,g");
    new GraphParser().parse("1\t  1,h");
    new GraphParser().parse(
      "6  141,8200  98,5594 66,6627 159,9500  143,x  129,8525  118,8547  88,2039 83,4949 "
        + "165,6473  162,6897  ");
  }

  @Test
  public void testParseValidInput() throws Exception {
    new GraphParser().parse("1 1");
    new GraphParser().parse("1\t1");
    new GraphParser().parse("1\t  1");
    new GraphParser().parse(
      "6  141,8200  98,5594 66,6627 159,9500  143,3110  129,8525  118,8547  88,2039 83,4949 "
        + "165,6473  162,6897  ");
  }

}
