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

import java.util.Deque;

import kungfu.algdesign.graph.Graph;
import kungfu.algdesign.graph.GraphFactoryTest;
import kungfu.algdesign.graph.MockGraph;
import kungfu.algdesign.graph.Vertex;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author lcsontos
 */
public class DFSTest {

  @Test
  public void testSearch() throws Exception {
    Graph graph = GraphFactoryTest.loadGraph(GraphFactoryTest.SMALL_GRAPH_INPUT_NAME);

    Vertex startVertex = graph.getVertex("1");

    Deque<Vertex> recursivelyTraversedVertices = DFS.searchRecursive(graph, startVertex);

    graph.reset();

    Deque<Vertex> iterativelyTraversedVertices = DFS.searchIterative(graph, startVertex);

    Assert.assertEquals(recursivelyTraversedVertices, iterativelyTraversedVertices);

    graph = new MockGraph();

    startVertex = graph.getVertex("a");

    recursivelyTraversedVertices = DFS.searchRecursive(graph, startVertex);

    graph.reset();

    iterativelyTraversedVertices = DFS.searchIterative(graph, startVertex);

    Assert.assertEquals(recursivelyTraversedVertices, iterativelyTraversedVertices);
  }

}
