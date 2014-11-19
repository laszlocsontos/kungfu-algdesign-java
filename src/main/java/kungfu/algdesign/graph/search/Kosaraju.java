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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import kungfu.algdesign.graph.Graph;
import kungfu.algdesign.graph.GraphImpl;
import kungfu.algdesign.graph.Vertex;

/**
 * @author lcsontos
 */
public final class Kosaraju {

  private Kosaraju() {
  }

  public static Collection<Graph> findSCCs(Graph graph) {
    Collection<Graph> sccs = new LinkedList<Graph>();

    Deque<Vertex> finishedVertices = new LinkedList<Vertex>(); 

    Iterator<Vertex> verticesIterator = graph.verticesIterator();

    while (verticesIterator.hasNext()) {
      Vertex vertex = (Vertex) verticesIterator.next();

      if (graph.isVisited(vertex)) {
        continue;
      }

      Deque<Vertex> traversedVertices = DFS.searchIterative(graph, vertex);

      finishedVertices.addAll(traversedVertices);
    }

    graph.reset();

    Set<Vertex> removedVertices = new HashSet<Vertex>();

    while (!finishedVertices.isEmpty()) {
      Vertex vertex = finishedVertices.pop();

      if (removedVertices.contains(vertex)) {
        continue;
      }

      Deque<Vertex> traversedVertices = DFS.searchIterative(graph, vertex, true);

      graph.removeVertices(traversedVertices);;
      removedVertices.addAll(traversedVertices);

      Graph subGraph = new GraphImpl(traversedVertices);

      sccs.add(subGraph);
    }

    return sccs;
  }

}
