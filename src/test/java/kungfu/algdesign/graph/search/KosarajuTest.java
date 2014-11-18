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

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

import kungfu.algdesign.graph.Graph;
import kungfu.algdesign.graph.MockGraph;
import kungfu.algdesign.graph.Vertex;

/**
 * @author lcsontos
 */
public class KosarajuTest {

  @Test
  public void testFindSCCs() {
    Graph graph = new MockGraph();

    Collection<Graph> actualSCCs = Kosaraju.findSCCs(graph);

    Collection<?>[] expectedSCCs = new Collection[] {
      Lists.newArrayList(new Vertex("e"), new Vertex("b"), new Vertex("a")),
      Lists.newArrayList(new Vertex("d"), new Vertex("c"), new Vertex("h")),
      Lists.newArrayList(new Vertex("f"), new Vertex("g"))
    };

    int index = 0;

    for (Graph scc : actualSCCs) {
      Collection<Vertex> vertices = scc.getVertices();

      Collection<?> expectedSCC = expectedSCCs[index++];

      Assert.assertEquals("Size mismatch for SCC #" + index, expectedSCC.size(), scc.size());
      Assert.assertTrue("Check failed for SCC #" + index, vertices.containsAll(expectedSCC));
    }
  }

}
