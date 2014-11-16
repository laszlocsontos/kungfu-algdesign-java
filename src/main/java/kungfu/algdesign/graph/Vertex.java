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
import java.util.LinkedList;
import java.util.Objects;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;

/**
 * @author lcsontos
 */
public class Vertex {

  public Vertex(String name) {
    if (name == null) {
      throw new IllegalArgumentException("name cannot be null.");
    }

    this.name = name;

    this.incomingEdges = new LinkedList<Edge>();
    this.outgoingEdges = new LinkedList<Edge>();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (obj == this) {
      return true;
    }

    if (!(obj instanceof Vertex)) {
      return false;
    }

    Vertex vertex = (Vertex) obj;

    return Objects.equals(vertex.name, name);
  }

  public void addIncomingEdge(Edge edge) {
    incomingEdges.add(edge);
  }

  public void addOutgoingEdge(Edge edge) {
    outgoingEdges.add(edge);
  }

  public Collection<Edge> getIncomingEdges() {
    return incomingEdges;
  }

  public Collection<Edge> getOutgoingEdges() {
    return outgoingEdges;
  }

  public String getName() {
    return name;
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }

  public boolean isVisited() {
    return visited;
  }

  public void reset() {
    visited = false;
  }

  @Override
  public String toString() {
    ToStringHelper helper = MoreObjects.toStringHelper(this).add("name", name);

    return helper.toString();
  }

  public void visit() {
    visited = true;
  }

  private final String name;

  private final Collection<Edge> incomingEdges;
  private final Collection<Edge> outgoingEdges;

  private boolean visited = false;

}
