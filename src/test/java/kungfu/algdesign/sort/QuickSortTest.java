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

import kungfu.algdesign.util.Utils;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author lcsontos
 *
 */
public class QuickSortTest extends AbstractQuickSortTest {

  @BeforeClass
  public static void setUpClass() throws IOException {
    InputStream inputStream = new GZIPInputStream(
      Utils.getInputStreamForResource(UNSORTED_INPUT_NAME));

    testArray = Utils.getIntegerArray(inputStream);
  }

  @Test
  public void testWithSelectFirstAsPivot() {
    long compCount = calculateCompCount(PivotSelectionStrategy.FIRST);

    Assert.assertEquals(162085, compCount);
  }

  @Test
  public void testWithSelectLastAsPivot() {
    long compCount = calculateCompCount(PivotSelectionStrategy.LAST);

    Assert.assertEquals(164123, compCount);
  }

  protected long calculateCompCount(PivotSelectionStrategy pivotSelectionStrategy) {
    Sort<Integer> sort = new QuickSortImpl<Integer>(pivotSelectionStrategy);

    Integer[] arr = Arrays.copyOf(testArray, testArray.length);

    return sort.sort(arr);
  }

  @Override
  protected Sort<Integer> getSort() {
    return new QuickSortImpl<Integer>();
  }

  private static final String UNSORTED_INPUT_NAME = "QuickSort.txt.gz";

  private static Integer[] testArray;

}
