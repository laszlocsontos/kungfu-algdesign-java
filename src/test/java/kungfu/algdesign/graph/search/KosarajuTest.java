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
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

import kungfu.algdesign.graph.Graph;
import kungfu.algdesign.graph.GraphFactoryTest;
import kungfu.algdesign.graph.MockGraph;
import kungfu.algdesign.graph.Vertex;

/**
 * @author lcsontos
 */
public class KosarajuTest {

  @Test
  public void testFindSCCsLargeInput() throws Exception {
    Graph graph = GraphFactoryTest.loadGraph(GraphFactoryTest.LARGE_GRAPH_INPUT_NAME);

    Collection<Graph> sccs = doTest(graph, null);

    Assert.assertEquals(371762, sccs.size());
  }

  @Test
  public void testFindSCCsMediumInput() throws Exception {
    Graph graph = GraphFactoryTest.loadGraph(GraphFactoryTest.MEDIUM_GRAPH_INPUT_NAME);

    Collection<Graph> sccs = doTest(graph, new int[] { 35, 7, 1, 1, 1, 1, 1, 1, 1, 1 });

    Assert.assertEquals(10, sccs.size());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testFindSCCsSmallInput() throws Exception {
    Graph graph = new MockGraph();

    Collection<Graph> actualSCCs = doTest(graph, new int[] { 3, 3, 2 });

    Collection<?>[] expectedSCCs = new Collection[] {
      Lists.newArrayList(new Vertex("e"), new Vertex("b"), new Vertex("a")),
      Lists.newArrayList(new Vertex("d"), new Vertex("c"), new Vertex("h")),
      Lists.newArrayList(new Vertex("f"), new Vertex("g"))
    };

    int index = 0;

    for (Graph actualSCC : actualSCCs) {
      Collection<Vertex> expectedSCC = (Collection<Vertex>) expectedSCCs[index++];

      Assert.assertEquals("Size mismatch for SCC #" + index, expectedSCC.size(), actualSCC.size());

      for (Vertex expectedVertex : expectedSCC) {
        String expectedVertexName = expectedVertex.getName();

        Assert.assertNotNull(
          "Check failed for SCC #" + index, actualSCC.getVertex(expectedVertexName));
      }
    }

    graph = GraphFactoryTest.loadGraph(GraphFactoryTest.SMALL_GRAPH_INPUT_NAME);

    actualSCCs = doTest(graph, new int[] { 3, 2, 2, 2, 1, 1 });
  }

  private Collection<Graph> doTest(Graph graph, int[] expectedSCCsizes) {
    int expectedSize = graph.size();

    List<Graph> sccs = (List<Graph>) Kosaraju.findSCCs(graph);

    Assert.assertNotNull(sccs);
    Assert.assertFalse("No SCCs returned.", sccs.isEmpty());

    int vertexCount = 0;

    for (Graph scc : sccs) {
      vertexCount += scc.size();
    }

    Assert.assertEquals("Vertex count mismatch.", expectedSize, vertexCount);

    Collections.sort(sccs, new Comparator<Graph>() {

      @Override
      public int compare(Graph g1, Graph g2) {
        int s1 = g1.size();
        int s2 = g2.size();

        if (s1 == s2) {
          return 0;
        } else if (s1 < s2) {
          return 1;
        } else {
          return -1;
        }
      }

    });

    int[] actualSCCsizes = new int[sccs.size()];

    int index = 0;

    Iterator<Graph> iterator = sccs.iterator();

    while (iterator.hasNext()) {
      Graph scc = iterator.next();

      if (index < 5) {
        System.out.println(scc.size());
      }

      actualSCCsizes[index++] = scc.size();
    }

    if (expectedSCCsizes != null) {
      Assert.assertArrayEquals("Expected SCC sizes mismatch.", expectedSCCsizes, actualSCCsizes);
    }

    return sccs;
  }

}
