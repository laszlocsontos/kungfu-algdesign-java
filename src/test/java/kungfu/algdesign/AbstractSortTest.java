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

package kungfu.algdesign;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import kungfu.algdesign.sort.Sort;

/**
 * @author lcsontos
 */
public abstract class AbstractSortTest {

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

    Integer[] arr = Arrays.copyOf(UNSORTED_ARRAY, UNSORTED_ARRAY.length);

    sort.sort(arr);

    Assert.assertArrayEquals(SORTED_ARRAY, arr);
  }

  protected abstract Sort<Integer> getSort();

  private static final Integer[] EMPTY_ARRAY = new Integer[0];

  private static final Integer[] UNSORTED_ARRAY = {
    3, 1, 7, 8, 3, 3, 5, 9, 100, 4, 2, 8, 1, 3, null, 1 ,6 ,7, 1, 6, 2, 345, 44, 333, 4234234, 2342
  };

  private static final Integer[] SORTED_ARRAY = {
    null, 1, 1, 1, 1, 2, 2, 3, 3, 3, 3, 4, 5, 6, 6, 7, 7, 8, 8, 9, 44, 100, 333, 345, 2342, 4234234
  };

}
