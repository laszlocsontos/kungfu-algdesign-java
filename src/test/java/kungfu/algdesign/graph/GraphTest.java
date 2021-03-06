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

import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableSet;

/**
 * @author lcsontos
 */
public class GraphTest {

  @Before
  public void setUp() {
    graph = new GraphImpl();

    graph.addEdge("a", "b");
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

    graph.addEdge("b", "c");

    testEdges(graph, "b", "c");
  }

  @Test
  public void testAddVertices() {
    Collection<Vertex> vertices = ImmutableSet.of(
      new Vertex("x"), new Vertex("y"), new Vertex("z"));

    graph.addVertices(vertices);

    Assert.assertNotNull(graph.getVertex("x"));
    Assert.assertNotNull(graph.getVertex("y"));
    Assert.assertNotNull(graph.getVertex("z"));
  }

  @Test
  public void testGetVertex() {
    Assert.assertNotNull(graph.getVertex("a"));
    Assert.assertNull(graph.getVertex("z"));
  }

  @Test(expected = NoSuchVertexException.class)
  public void testIsVisited() {
    Vertex vertex = graph.getVertex("a");

    Assert.assertFalse(graph.isVisited(vertex));

    graph.visit(vertex);

    Assert.assertTrue(graph.isVisited(vertex));

    graph.removeVertex(vertex);
    graph.isVisited(vertex);
  }

  @Test
  public void testRemoveVertex() {
    graph.addEdge("b", "c");
    graph.addEdge("c", "d");
    graph.addEdge("d", "e");

    Assert.assertEquals(5, graph.size());

    graph.removeVertex("c");

    Assert.assertEquals(4, graph.size());

    testEdges(graph, "a", "b");
    testEdges(graph, "d", "e");

    Assert.assertFalse(graph.getVertex("b").outgoingEdgesIterator().hasNext());
    Assert.assertFalse(graph.getVertex("d").incomingEdgesIterator().hasNext());
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
