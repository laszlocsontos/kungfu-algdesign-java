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

/**
 * @author lcsontos
 */
public class MockGraph extends GraphImpl {

  public MockGraph() {
    super();

    addEdge("a", "b");
    addEdge("e", "a");
    addEdge("b", "e");
    addEdge("b", "f");

    addEdge("g", "f");
    addEdge("f", "g");

    addEdge("h", "g");

    addEdge("h", "d");
    addEdge("d", "h");
    addEdge("d", "c");
    addEdge("c", "d");

    addEdge("b", "c");
    addEdge("c", "g");
  }
}
