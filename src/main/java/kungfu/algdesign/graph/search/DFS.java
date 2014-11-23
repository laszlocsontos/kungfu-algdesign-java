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
import java.util.Iterator;
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
    Deque<Vertex> traversedVertices = new LinkedList<Vertex>();

    Deque<Vertex> stack = new LinkedList<Vertex>();

    stack.push(vertex);

    graph.visit(vertex);

    while (!stack.isEmpty()) {
      Vertex currentVertex = stack.peek();

      Vertex unvisitedAdjacentVertex = null;

      for (Iterator<Edge> iterator = currentVertex.outgoingEdgesIterator(); iterator.hasNext();) {
        Edge edge = iterator.next();

        unvisitedAdjacentVertex = edge.getHead();

        if (!graph.isVisited(unvisitedAdjacentVertex)) {
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
        graph.visit(unvisitedAdjacentVertex);

        stack.push(unvisitedAdjacentVertex);
      }
    }

    // graph.reset();

    return traversedVertices;
  }

  public static Deque<Vertex> searchRecursive(Graph graph, Vertex vertex) {
    Deque<Vertex> traversedVertices = new LinkedList<Vertex>();

    doSearchRecursive(graph, vertex, traversedVertices);

    // graph.reset();

    return traversedVertices;
  }

  private static void doSearchRecursive(
    Graph graph, Vertex vertex, Deque<Vertex> traversedVertices) {

    graph.visit(vertex);

    for (Iterator<Edge> iterator = vertex.outgoingEdgesIterator(); iterator.hasNext();) {
      Edge edge = iterator.next();

      Vertex head = edge.getHead();

      if (!graph.isVisited(head)) {
        doSearchRecursive(graph, head, traversedVertices);
      }
    }

    traversedVertices.push(vertex);
  }

}
