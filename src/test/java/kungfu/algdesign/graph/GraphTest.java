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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author lcsontos
 */
public class GraphTest {

  @Before
  public void setUp() {
    graph = new GraphImpl();

    graph.addEdge("b", "a");
  }

  @Test
  public void testAddEdge() {
    Assert.assertEquals(2, graph.size());

    testEdges(graph, "a", "b");
  }

  @Test
  public void testAddVertex() {
    Vertex newVertex = graph.addVertex("c");

    Assert.assertFalse(newVertex.incomingEdgesIterator().hasNext());
    Assert.assertFalse(newVertex.outgoingEdgesIterator().hasNext());

    graph.addEdge("c", "b");

    testEdges(graph, "b", "c");
  }

  @Test
  public void testGetVertex() {
    Assert.assertNotNull(graph.getVertex("a"));
    Assert.assertNull(graph.getVertex("z"));
  }

  @Test
  public void testIsVisited() {
    Vertex vertex = graph.getVertex("a");

    Assert.assertFalse(graph.isVisited(vertex));

    graph.visit(vertex);

    Assert.assertTrue(graph.isVisited(vertex));
  }

  @Test
  public void testReset() {
    Vertex vertex = graph.getVertex("a");

    graph.visit(vertex);
    graph.reset();

    Assert.assertFalse(graph.isVisited(vertex));
  }

  @Test
  public void testReverse() {
    Graph reversedGraph = graph.reverse();

    testEdges(reversedGraph, "b", "a");
  }

  @Test
  public void testVisit() {
    
  }

  private void testEdges(Graph graph, String tailName, String headName) {
    Vertex tail = graph.getVertex(tailName);

    Assert.assertNotNull(tail);
    Assert.assertTrue(tail.outgoingEdgesIterator().hasNext());

    Vertex head = graph.getVertex(headName);

    Assert.assertNotNull(head);
    Assert.assertTrue(head.incomingEdgesIterator().hasNext());

    Assert.assertEquals(head, tail.outgoingEdgesIterator().next().getHead());
    Assert.assertEquals(tail, head.incomingEdgesIterator().next().getTail());
  }

  private Graph graph;

}
