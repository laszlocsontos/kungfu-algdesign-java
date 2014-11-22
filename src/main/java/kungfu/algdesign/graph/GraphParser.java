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
import java.util.Collection;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lcsontos
 */
public final class GraphParser {

  public Graph read(BufferedReader bufferedReader) throws InvalidGraphException {
    Graph graph = new GraphImpl();

    String line = null;

    try {
      while ((line = bufferedReader.readLine()) != null) {
        lineNumber++;

        Collection<Edge> edges = parse(line);

        for (Edge edge : edges) {
          graph.addEdge(edge);
        }
      }
    } catch (IOException ioe) {
      throw new InvalidGraphException("Error reading graph", ioe);
    }

    return graph;
  }

  public Collection<Edge> parse(String line) throws InvalidGraphException {
    Matcher matcher = SINGLE_EDGE_FORMAT_PATTERN.matcher(line);

    if (!matcher.find()) {
      StringBuilder sb = new StringBuilder();

      sb.append("Format of line #");
      sb.append(lineNumber);
      sb.append(" is invalid: \"");
      sb.append(line);
      sb.append("\".");

      throw new InvalidGraphException(sb.toString());
    }

    // First edge

    String tailName = matcher.group(1);
    String headName = matcher.group(2);

    Vertex tail = new Vertex(tailName);
    Vertex head = new Vertex(headName);

    Integer weight = getWeight(matcher, 3);

    Collection<Edge> edges = new LinkedList<>();

    edges.add(new Edge(tail, head, weight));

    // Subsequent edges

    int startPosition = matcher.end();

    matcher = ADJACENT_VERTICES_FORMAT_PATTERN.matcher(line);

    while (matcher.find(startPosition)) {
      headName = matcher.group(1);

      head = new Vertex(headName);

      weight = getWeight(matcher, 2);

      edges.add(new Edge(tail, head, weight));

      startPosition = matcher.end();
    }

    return edges;
  }

  private Integer getWeight(Matcher matcher, int groupIndex) throws InvalidGraphException {
    String groupValue = matcher.group(groupIndex);

    if (groupValue == null) {
      return null;
    }

    try {
      return Integer.valueOf(groupValue);
    } catch (NumberFormatException nfe) {
      throw new InvalidGraphException("Invalid weight in line #" + lineNumber);
    }
  }

  private static final Pattern SINGLE_EDGE_FORMAT_PATTERN = Pattern.compile(
    "^(\\w+)(?:\\s+)(\\w+)(?:\\,(\\d+))?");

  private static final Pattern ADJACENT_VERTICES_FORMAT_PATTERN = Pattern.compile(
    "(?:\\s+)(\\w+)(?:\\,(\\d+))?");

  private int lineNumber;

}
