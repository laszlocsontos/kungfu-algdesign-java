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

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import org.junit.Test;

import kungfu.algdesign.util.Utils;

/**
 * @author lcsontos
 */
public class GraphFactoryTest {

  public static Graph loadGraph(String inputName) throws InvalidGraphException, IOException {
    InputStream inputStream = new GZIPInputStream(Utils.getInputStreamForResource(inputName));

    return GraphFactory.loadGraph(inputStream);
  }

  @Test
  public void testLoad() throws Exception {
    loadGraph(LARGE_GRAPH_INPUT_NAME);
    loadGraph(MEDIUM_GRAPH_INPUT_NAME);
    loadGraph(SMALL_GRAPH_INPUT_NAME);
  }

  public static final String LARGE_GRAPH_INPUT_NAME = "SCC_large.txt.gz";

  // Taken from http://algs4.cs.princeton.edu/42directed/mediumDG.txt
  public static final String MEDIUM_GRAPH_INPUT_NAME = "SCC_medium.txt.gz";

  public static final String SMALL_GRAPH_INPUT_NAME = "SCC_small.txt.gz";

}
