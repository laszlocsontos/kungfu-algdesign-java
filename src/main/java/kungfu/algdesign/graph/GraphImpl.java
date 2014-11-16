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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author lcsontos
 */
public class GraphImpl implements Graph {

  public GraphImpl() {
    verticesMap = new HashMap<>();
    visitedVerices = new HashSet<Vertex>();
  }

  @Override
  public Edge addEdge(String headName, String tailName) {
    Vertex head = addVertex(headName);
    Vertex tail = addVertex(tailName);

    Edge edge = new Edge(head, tail);

    head.addIncomingEdge(edge);
    tail.addOutgoingEdge(edge);

    return edge;
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
  public Vertex getVertex(String name) {
    return verticesMap.get(name);
  }

  public boolean isVisited(Vertex vertex) {
    checkVertex(vertex);

    return visitedVerices.contains(vertex);
  }

  @Override
  public void reset() {
    visitedVerices.clear();
  }

  @Override
  public Graph reverse() {
    Graph reverseGraph = new GraphImpl();

    // TODO reverse

    return reverseGraph;
  }

  @Override
  public void visit(Vertex vertex) {
    checkVertex(vertex);

    visitedVerices.add(vertex);
  }

  private void checkVertex(Vertex vertex) {
    if (!verticesMap.containsValue(vertex)) {
      throw new NoSuchVertexException();
    }
  }

  private final Map<String, Vertex> verticesMap;
  private final Set<Vertex> visitedVerices;

}
