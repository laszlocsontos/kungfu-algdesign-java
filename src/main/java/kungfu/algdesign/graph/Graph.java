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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;

/**
 * @author lcsontos
 */
public class Graph {

  public static Graph load(InputStream inputStream) throws InvalidGraphException {
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    Graph graph = new Graph();

    String line = null;

    int lineNumber = 0;

    try {
      while ((line = bufferedReader.readLine()) != null) {
        lineNumber++;

        String[] vertices = line.split("\\W");

        if (vertices.length != 2) {
          throw new InvalidGraphException("Line \"" + lineNumber + "\" is invalid.");
        }

        graph.addEdge(vertices[1], vertices[0]);
      }
    } catch (IOException ioe) {
      throw new InvalidGraphException("Error reading graph", ioe);
    }

    return graph;
  }

  public Graph() {
    vertices = new LinkedHashMap<>();
  }

  public Graph(Set<Vertex> vertices) {
    this();

    addVertices(vertices);
  }

  public Edge addEdge(Vertex head, Vertex tail) {
    Edge edge = new Edge(head, tail);

    head.addIncomingEdge(edge);
    tail.addOutgoingEdge(edge);

    return edge;
  }

  public Edge addEdge(String headName, String tailName) {
    Vertex head = addVertexIfNotExists(headName);
    Vertex tail = addVertexIfNotExists(tailName);

    return addEdge(head, tail);
  }

  public Vertex addVertex(String name) {
    Vertex vertex = new Vertex(name);

    vertices.put(name, vertex);

    return vertex;
  }

  public void addVertices(Set<Vertex> vertices) {
    for (Vertex vertex : vertices) {
      this.vertices.put(vertex.getName(), vertex);
    }
  }

  public Vertex addVertexIfNotExists(String name) {
    Vertex vertex = getVertex(name);

    if (vertex == null) {
      vertex = addVertex(name);
    }

    return vertex;
  }

  public Vertex getVertex(String name) {
    return vertices.get(name);
  }

  public Collection<Vertex> getVertices() {
    return vertices.values();
  }

  public void removeVertex(String name) {
    Vertex vertex = getVertex(name);

    removeVertex(vertex);
  }

  public void removeVertex(Vertex vertex) {
    Collection<Edge> incomingEdges = vertex.getIncomingEdges();

    for (Edge edge : incomingEdges) {
      Vertex tail = edge.getTail();

      tail.getOutgoingEdges().remove(edge);
    }

    incomingEdges.clear();

    Collection<Edge> outgoingEdges = vertex.getOutgoingEdges();

    for (Edge edge : outgoingEdges) {
      Vertex head = edge.getHead();

      head.getIncomingEdges().remove(edge);
    }

    outgoingEdges.clear();

    vertices.remove(vertex);
  }

  public void removeVertices(Set<Vertex> vertices) {
    for (Vertex vertex : vertices) {
      removeVertex(vertex);
    }
  }

  public void reset() {
    for (Vertex vertex : vertices.values()) {
      vertex.reset();
    }
  }

  public Graph reverse() {
    Graph reverseGraph = new Graph();

    for (Vertex vertex : vertices.values()) {
      Collection<Edge> edges = vertex.getOutgoingEdges();

      for (Edge edge : edges) {
        String headName = edge.getHead().getName();
        String tailName = edge.getTail().getName();

        reverseGraph.addEdge(tailName, headName);
      }
    }

    return reverseGraph;
  }

  public int size() {
    return vertices.size();
  }

  @Override
  public String toString() {
    ToStringHelper helper = MoreObjects.toStringHelper(this).add("vertexCount", vertices.size());

    return helper.toString();
  }

  private final Map<String, Vertex> vertices;

}
