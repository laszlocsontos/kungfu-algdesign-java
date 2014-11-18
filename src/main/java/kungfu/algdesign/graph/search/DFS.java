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

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;

import kungfu.algdesign.graph.Edge;
import kungfu.algdesign.graph.Graph;
import kungfu.algdesign.graph.Vertex;

/**
 * @author lcsontos
 */
public final class DFS {

  private DFS() {
  }

  public static Deque<Vertex> searchIterative(Graph graph, Vertex vertex) {
    return searchIterative(graph, vertex, false);
  }

  public static Deque<Vertex> searchIterative(Graph graph, Vertex vertex, boolean reverse) {
    Deque<Vertex> traversedVertices = new LinkedList<Vertex>();

    Deque<Vertex> stack = new LinkedList<Vertex>();

    stack.push(vertex);

    vertex.visit();

    while (!stack.isEmpty()) {
      Vertex currentVertex = stack.peek();

      Collection<Edge> edges = null;

      if (reverse) {
        edges = currentVertex.getIncomingEdges();
      } else {
        edges = currentVertex.getOutgoingEdges();
      }

      Vertex unvisitedAdjacentVertex = null;

      for (Edge edge : edges) {
        if (reverse) {
          unvisitedAdjacentVertex = edge.getTail();
        } else {
          unvisitedAdjacentVertex = edge.getHead();
        }

        if (!unvisitedAdjacentVertex.isVisited()) {
          break;
        } else {
          unvisitedAdjacentVertex = null;
        }
      }

      // currentVertex has no unvisited adjacent vertices
      if (unvisitedAdjacentVertex == null) {
        stack.pop();

        // done with currentVertex
        traversedVertices.push(currentVertex);
      } else {
        unvisitedAdjacentVertex.visit();

        stack.push(unvisitedAdjacentVertex);
      }
    }

    graph.reset();

    return traversedVertices;
  }

  public static Deque<Vertex> searchRecursive(Graph graph, Vertex vertex) {
    Deque<Vertex> traversedVertices = new LinkedList<Vertex>();

    doSearchRecursive(graph, vertex, traversedVertices);

    graph.reset();

    return traversedVertices;
  }

  private static void doSearchRecursive(
    Graph graph, Vertex vertex, Deque<Vertex> traversedVertices) {

    vertex.visit();

    for (Edge edge : vertex.getOutgoingEdges()) {
      Vertex head = edge.getHead();

      if (!head.isVisited()) {
        doSearchRecursive(graph, head, traversedVertices);
      }
    }

    traversedVertices.push(vertex);
  }

}
