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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;

/**
 * @author lcsontos
 */
public class GraphImpl implements Graph {

  public GraphImpl() {
    verticesMap = new HashMap<>();
    visitedVerices = new HashSet<Vertex>();
  }

  public GraphImpl(Collection<Vertex> vertices) {
    this();

    addVertices(vertices);
  }

  @Override
  public Edge addEdge(String tailName, String headName) {
    Vertex head = addVertex(headName);
    Vertex tail = addVertex(tailName);
  
    return addEdge(tail, head);
  }

  @Override
  public Vertex addVertex(String name) {
    Vertex vertex = getVertex(name);

    if (vertex == null) {
      vertex = new Vertex(name);

      verticesMap.put(name, vertex);
    }

    return vertex;
  }

  @Override
  public void addVertices(Collection<Vertex> vertices) {
    for (Vertex vertex : vertices) {
      if (verticesMap.containsValue(vertex)) {
        continue;
      }

      verticesMap.put(vertex.getName(), vertex);
    }
  }

  @Override
  public Vertex getVertex(String name) {
    return verticesMap.get(name);
  }

  @Override
  public Iterator<Vertex> verticesIterator() {
    Collection<Vertex> vertices = verticesMap.values();

    return vertices.iterator();
  }

  @Override
  public boolean isVisited(Vertex vertex) {
    checkVertex(vertex);

    return visitedVerices.contains(vertex);
  }

  @Override
  public void removeVertex(String name) {
    Vertex vertex = getVertex(name);

    removeVertex(vertex);
  }

  @Override
  public void removeVertex(Vertex vertex) {
    checkVertex(vertex);

    for (Iterator<Edge> iterator = vertex.incomingEdgesIterator(); iterator.hasNext();) {
      Edge edge = (Edge) iterator.next();

      Vertex tail = edge.getTail();

      tail.removeOutgoingEdge(edge);
    }

    vertex.clearIncomingEdges();

    for (Iterator<Edge> iterator = vertex.outgoingEdgesIterator(); iterator.hasNext();) {
      Edge edge = (Edge) iterator.next();

      Vertex head = edge.getHead();

      head.removeIncomingEdge(edge);
    }

    vertex.clearOutgoingEdges();

    verticesMap.remove(vertex);
    visitedVerices.remove(vertex);
  }

  @Override
  public void removeVertices(Collection<Vertex> vertices) {
    for (Vertex vertex : vertices) {
      removeVertex(vertex);
    }
  }

  @Override
  public void reset() {
    visitedVerices.clear();
  }

  @Override
  public Graph reverse() {
    Graph reverseGraph = new GraphImpl();

    for (Vertex vertex : verticesMap.values()) {
      for (Iterator<Edge> iterator = vertex.outgoingEdgesIterator(); iterator.hasNext();) {
        Edge edge = iterator.next();

        String headName = edge.getHead().getName();
        String tailName = edge.getTail().getName();

        reverseGraph.addEdge(headName, tailName);
      }
    }

    return reverseGraph;
  }

  @Override
  public int size() {
    return verticesMap.size();
  }

  @Override
  public String toString() {
    ToStringHelper helper = MoreObjects.toStringHelper(this).add("vertexCount", verticesMap.size());

    return helper.toString();
  }

  @Override
  public void visit(Vertex vertex) {
    checkVertex(vertex);

    visitedVerices.add(vertex);
  }

  private Edge addEdge(Vertex tail, Vertex head) {
    Edge edge = new Edge(tail, head);

    head.addIncomingEdge(edge);
    tail.addOutgoingEdge(edge);

    return edge;
  }

  private void checkVertex(Vertex vertex) {
    if (!verticesMap.containsValue(vertex)) {
      throw new NoSuchVertexException();
    }
  }

  private final Map<String, Vertex> verticesMap;
  private final Set<Vertex> visitedVerices;

}
