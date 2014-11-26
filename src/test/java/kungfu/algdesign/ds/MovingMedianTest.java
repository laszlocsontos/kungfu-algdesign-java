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

package kungfu.algdesign.ds;

import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.zip.GZIPInputStream;

import kungfu.algdesign.util.Utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author lcsontos
 */
public class MovingMedianTest {

  @SuppressWarnings("unchecked")
  @Test
  public void testLargeInputCalculate() throws Exception {
    InputStream inputStream = new GZIPInputStream(
      Utils.getInputStreamForResource(MEDIAN_INPUT_NAME));

    List<Integer> data = Utils.getIntegerList(inputStream);

    Queue<Integer> medians = doTest((Queue<Integer>) data, null);

    long sum = 0;

    for (Iterator<Integer> iterator = medians.iterator(); iterator.hasNext();) {
      Integer median = (Integer) iterator.next();

      sum += median.intValue();
    }

    Assert.assertEquals(1213, sum);
  }

  @Test
  public void testSmallInputCalculate() {
    Queue<Integer> data = new LinkedList<>();

    data.add(5);
    data.add(15);
    data.add(1);
    data.add(3);

    Queue<Integer> expected = new LinkedList<>();

    expected.add(5);
    expected.add(5);
    expected.add(5);
    expected.add(3);

    doTest(data, expected);
  }

  private Queue<Integer> doTest(Queue<Integer> data, Queue<Integer> expected) {
    int dataSize = data.size();

    Queue<Integer> medians = new LinkedList<>();

    MovingMedian.calculate(data, medians);

    Assert.assertEquals(0, data.size());
    Assert.assertEquals(dataSize, medians.size());

    if (expected != null) {
      Assert.assertEquals(expected, medians);
    }

    return medians;
  }

  private static final String MEDIAN_INPUT_NAME = "Median.txt.gz";


}
