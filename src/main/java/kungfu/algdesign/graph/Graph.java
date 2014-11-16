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

import java.util.HashMap;
import java.util.Map;

/**
 * @author lcsontos
 */
public class Graph {

  public Graph() {
    vertices = new HashMap<>();
  }

  public Edge addEdge(String headName, String tailName) {
    Vertex head = addVertex(headName);
    Vertex tail = addVertex(tailName);

    Edge edge = new Edge(head, tail);

    head.addIncomingEdge(edge);
    tail.addOutgoingEdge(edge);

    return edge;
  }

  public Vertex addVertex(String name) {
    Vertex vertex = getVertex(name);

    if (vertex == null) {
      vertex = new Vertex(name);

      vertices.put(name, vertex);
    }

    return vertex;
  }

  public Vertex getVertex(String name) {
    return vertices.get(name);
  }

  public void reset() {
    for (Vertex vertex : vertices.values()) {
      vertex.reset();
    }
  }

  public Graph reverse() {
    Graph reverseGraph = new Graph();

    // TODO reverse

    return reverseGraph;
  }

  private final Map<String, Vertex> vertices;

}
