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

  public static Deque<Vertex> search(Graph graph, Vertex vertex) {
    Deque<Vertex> traversedVertices = new LinkedList<Vertex>();

    Deque<Vertex> stack = new LinkedList<Vertex>();

    stack.add(vertex);

    while (!stack.isEmpty()) {
      vertex = stack.pop();

      if (!vertex.isVisited()) {
        vertex.visit();

        for (Edge edge : vertex.getOutgoingEdges()) {
          stack.push(edge.getHead());
        }

        traversedVertices.add(vertex);
      }
    }

    return traversedVertices;
  }

}
