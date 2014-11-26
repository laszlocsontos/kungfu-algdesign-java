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

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author lcsontos
 */
public class MovingMedianTest {

  @Test
  public void testSmallInputCalculate() {
    Queue<Integer> data = new LinkedList<>();

    data.add(5);
    data.add(15);
    data.add(1);
    data.add(3);
    data.add(null);

    Queue<Integer> medians = new LinkedList<>();

    MovingMedian.calculate(data, medians);

    Queue<Integer> expected = new LinkedList<>();

    expected.add(5);
    expected.add(5);
    expected.add(5);
    expected.add(3);

    Assert.assertEquals(expected, medians);
  }

}
