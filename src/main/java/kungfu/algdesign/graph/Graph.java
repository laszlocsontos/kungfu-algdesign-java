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
import java.util.Iterator;

/**
 * @author lcsontos
 */
public interface Graph {

  Edge addEdge(Edge edge);

  Edge addEdge(String headName, String tailName);

  Edge addEdge(String tailName, String headName, Integer weight);

  Vertex addVertex(String name);

  void addVertices(Collection<Vertex> vertices);

  Vertex getVertex(String name);

  boolean isVisited(Vertex vertex);

  void removeVertex(String name);

  void removeVertex(Vertex vertex);

  void removeVertices(Collection<Vertex> vertices);

  void reset();

  Graph reverse();

  int size();

  Iterator<Vertex> verticesIterator();

  void visit(Vertex vertex);

}
