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
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

import kungfu.algdesign.graph.Graph;
import kungfu.algdesign.graph.Vertex;

/**
 * @author lcsontos
 */
public final class Kosaraju {

  private Kosaraju() {
  }

  public static Collection<Graph> findSCCs(Graph graph) {
    Collection<Graph> sccs = new LinkedList<Graph>();

    Collection<Vertex> allVertices = graph.getVertices();

    Deque<Deque<Vertex>> finishedVertices = new LinkedList<Deque<Vertex>>(); 

    for (Vertex vertex : allVertices) {
      if (vertex.isVisited()) {
        continue;
      }

      Deque<Vertex> traversedVertices = DFS.searchIterative(graph, vertex);
      // Deque<Vertex> traversedVertices = DFS.searchRecursive(graph, vertex);

      finishedVertices.push(traversedVertices);
    }

    if (finishedVertices.isEmpty()) {
      return Collections.emptyList();
    }

    graph.reset();

    Deque<Vertex> stack = finishedVertices.pop();

    while (!stack.isEmpty()) {
      Vertex vertex = stack.pop();

      if (stack.isEmpty() && !finishedVertices.isEmpty()) {
        stack = finishedVertices.pop();
      }

      if (vertex.isVisited()) {
        continue;
      }

      Deque<Vertex> traversedVertices = DFS.searchIterative(graph, vertex, true);
      // Deque<Vertex> traversedVertices = DFS.searchRecursive(graph, vertex, true);

      graph.removeVertices(traversedVertices);;

      Graph subGraph = new Graph(traversedVertices);

      sccs.add(subGraph);
    }

    return sccs;
  }

}
