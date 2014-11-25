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
import java.util.Arrays;
import java.util.zip.GZIPInputStream;

import org.junit.Assert;
import org.junit.Test;

import kungfu.algdesign.util.Utils;

/**
 * @author lcsontos
 */
public class TwoSumTest {

  @Test
  public void testLargeInputFind() throws Exception {
    InputStream inputStream = new GZIPInputStream(
      Utils.getInputStreamForResource(INPUT_NAME));

    long[] arr = Utils.getLongArray(inputStream);

    Arrays.sort(arr);

    int count = 0;

    for (long target = -10000; target <= 10000; target++) {
      long[] indices = TwoSum.find(target, arr);

      if (indices[0] != -1 && indices[1] != -1) {
        count++;
      }
    }

    Assert.assertEquals(427, count);
  }

  @Test
  public void testSmallInputFind() {
    long[] arr = new long[] { 4, 6, 1, 4, 9, 1, 3, 4, 8, 2, 5 };

    Arrays.sort(arr);

    // arr = [1, 1, 2, 3, 4, 4, 4, 5, 6, 8, 9]

    Assert.assertArrayEquals(new long[] { 0, 10 }, TwoSum.find(10, arr));
    Assert.assertArrayEquals(new long[] { 2, 8 }, TwoSum.find(8, arr));
    Assert.assertArrayEquals(new long[] { -1, -1 }, TwoSum.find(100, arr));
  }

  private static final String INPUT_NAME = "2sum.txt.gz";

}
