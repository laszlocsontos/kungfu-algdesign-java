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
import java.util.PriorityQueue;
import java.util.Queue;

import kungfu.algdesign.graph.Edge;
import kungfu.algdesign.graph.Graph;
import kungfu.algdesign.graph.Vertex;

/**
 * @author lcsontos
 */
public class Dijkstra {

  public static Deque<Vertex> findShortestPath(Graph graph, String sourceName) {
    Vertex source = graph.getVertex(sourceName);

    source.setDistance(0);

    Queue<Vertex> queue = new PriorityQueue<>();

    queue.add(source);

    Deque<Vertex> path = new LinkedList<>();

    while (!queue.isEmpty()) {
      Vertex vertex = queue.poll();

      for (Iterator<Edge> iterator = vertex.outgoingEdgesIterator(); iterator.hasNext();) {
        Edge edge = iterator.next();

        Vertex next = edge.getHead();

        Integer weight = edge.getWeight();

        int newDistance = Integer.MAX_VALUE;

        if (weight != null) {
          newDistance = vertex.getDistance() + weight;
        }

        if (newDistance < next.getDistance()) {
          queue.remove(next);

          next.setDistance(newDistance);

          queue.add(next);

          path.addFirst(vertex);
        }
      }
    }

    return path;
  }

}
