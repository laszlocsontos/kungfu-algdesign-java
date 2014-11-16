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

import java.util.Objects;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;

/**
 * @author lcsontos
 */
public class Edge {

  public Edge(Vertex head, Vertex tail) {
    if (head == null) {
      throw new IllegalArgumentException("head cannot be null.");
    }

    if (tail == null) {
      throw new IllegalArgumentException("tail cannot be null.");
    }

    this.head = head;
    this.tail = tail;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (obj == this) {
      return true;
    }

    if (!(obj instanceof Edge)) {
      return false;
    }

    Edge edge = (Edge) obj;

    return (Objects.equals(edge.head, head) && Objects.equals(edge.tail, tail));
  }

  public Vertex getHead() {
    return head;
  }

  public Vertex getTail() {
    return tail;
  }

  @Override
  public int hashCode() {
    return Objects.hash(head, tail);
  }

  @Override
  public String toString() {
    ToStringHelper helper = MoreObjects.toStringHelper(this).add("head", head).add("tail", tail);

    return helper.toString();
  }

  private final Vertex head;
  private final Vertex tail;

}
