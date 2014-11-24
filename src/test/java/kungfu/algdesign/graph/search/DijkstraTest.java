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

package kungfu.algdesign.graph.search;

import kungfu.algdesign.graph.Graph;
import kungfu.algdesign.graph.GraphFactoryTest;
import kungfu.algdesign.graph.Vertex;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author lcsontos
 */
public class DijkstraTest {

  @Test
  public void testFindShortestPath() throws Exception {
    Graph graph = GraphFactoryTest.loadGraph(DIJKSTRA_INPUT_NAME);

    Dijkstra.findShortestPath(graph, "1");

    String[] vertexNames = { "7", "37", "59", "82", "99", "115", "133", "165", "188", "197" };
    int[] expectedDistances = { 2599, 2610, 2947, 2052, 2367, 2399, 2029, 2442, 2505, 3068 };

    int index = 0;

    for (String vertexName : vertexNames) {
      Vertex vertex = graph.getVertex(vertexName);

      Assert.assertEquals(
        "Distance for vertex \"" + vertexName + "\" is wrong.", expectedDistances[index++],
        vertex.getDistance());
    }

    
  }

  private static final String DIJKSTRA_INPUT_NAME = "dijkstraData.txt.gz";

}
