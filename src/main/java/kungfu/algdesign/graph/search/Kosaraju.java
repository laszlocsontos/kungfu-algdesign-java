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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

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

    Deque<Vertex> finishedVertices = new LinkedList<Vertex>(); 
    Set<Vertex> visitedVertices = new HashSet<Vertex>();

    for (Vertex vertex : allVertices) {
      if (visitedVertices.contains(vertex)) {
        continue;
      }

      Deque<Vertex> traversedVertices = DFS.searchIterative(graph, vertex);

      finishedVertices.addAll(traversedVertices);
      visitedVertices.addAll(traversedVertices);
    }

    graph.reset();

    while (!finishedVertices.isEmpty()) {
      Vertex vertex = finishedVertices.pop();

      Deque<Vertex> traversedVertices = DFS.searchIterative(graph, vertex, true);

      visitedVertices = new HashSet<Vertex>(traversedVertices);

      graph.removeVertices(visitedVertices);;
      finishedVertices.removeAll(visitedVertices);

      Graph subGraph = new Graph(visitedVertices);

      sccs.add(subGraph);
    }

    return sccs;
  }

}
