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

package kungfu.algdesign.sort;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;

import org.junit.Assert;
import org.junit.Test;

import kungfu.algdesign.sort.Sort;
import kungfu.algdesign.util.Utils;

/**
 * @author lcsontos
 */
public abstract class AbstractSortTest {

  @Test
  public void testIsLessThenOrEquals() {
    AbstractSort<Integer> sort = (AbstractSort<Integer>)getSort();

    Assert.assertFalse(sort.isLessThenOrEquals(1, null));
    Assert.assertFalse(sort.isLessThenOrEquals(2, 1));

    Assert.assertTrue(sort.isLessThenOrEquals(1, 1));
    Assert.assertTrue(sort.isLessThenOrEquals(1, 2));
    Assert.assertTrue(sort.isLessThenOrEquals(null, 1));
    Assert.assertTrue(sort.isLessThenOrEquals(null, 2));
  }

  @Test(expected = NullPointerException.class)
  public void testSortNull() {
    getSort().sort(null);
  }

  @Test
  public void testSortEmpty() {
    Sort<Integer> sort = getSort();

    sort.sort(EMPTY_ARRAY);
  }

  @Test
  public void testSortSmallInput() {
    Sort<Integer> sort = getSort();

    Integer[] arr = Arrays.copyOf(UNSORTED_SMALL_ARRAY, UNSORTED_SMALL_ARRAY.length);

    sort.sort(arr);

    Assert.assertArrayEquals(SORTED_SMALL_ARRAY, arr);
  }

  @Test
  public void testSortLargeInput() throws IOException {
    Sort<Integer> sort = getSort();

    InputStream inputStream = new GZIPInputStream(
      Utils.getInputStreamForResource(UNSORTED_INPUT_NAME));

    Integer[] arr = Utils.getIntegerArray(inputStream);

    sort.sort(arr);

    Assert.assertArrayEquals(SORTED_LARGE_ARRAY, arr);
  }

  protected abstract Sort<Integer> getSort();

  private static final Integer[] EMPTY_ARRAY = new Integer[0];

  private static final String UNSORTED_INPUT_NAME = "IntegerArray.txt.gz";

  private static final Integer[] UNSORTED_SMALL_ARRAY = {
    3, 1, 7, 8, 3, 3, 5, 9, 100, 4, 2, 8, 1, 3, null, 1 ,6 ,7, 1, 6, 2, 345, 44, 333, 4234234, 2342
  };

  private static final Integer[] SORTED_LARGE_ARRAY = new Integer[100000];

  private static final Integer[] SORTED_SMALL_ARRAY = {
    null, 1, 1, 1, 1, 2, 2, 3, 3, 3, 3, 4, 5, 6, 6, 7, 7, 8, 8, 9, 44, 100, 333, 345, 2342, 4234234
  };

  static {
    for (int index = 0; index < SORTED_LARGE_ARRAY.length; index++) {
      SORTED_LARGE_ARRAY[index] = index + 1;
    }
  }

}
