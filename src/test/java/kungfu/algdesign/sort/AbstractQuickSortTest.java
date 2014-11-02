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

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author lcsontos
 *
 */
public abstract class AbstractQuickSortTest extends AbstractSortTest {

  @Test
  public void testPartition() {
    AbstractQuickSort<Integer> sort = (AbstractQuickSort<Integer>)getSort();

    Integer[] actuals = new Integer[] { 3, 8, 2, 5, 1, 4, 7, 6 };
    Integer[] expecteds = new Integer[] { 1, 2, 3, 5, 8, 4, 7, 6 };

    sort.partition(actuals, 0, 0, actuals.length - 1);

    Assert.assertArrayEquals(expecteds, actuals);
  }

}
