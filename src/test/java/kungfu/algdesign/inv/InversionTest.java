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

package kungfu.algdesign.inv;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import kungfu.algdesign.util.Utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author lcsontos
 */
public class InversionTest {

  @Test(expected = NullPointerException.class)
  public void testCountInversionsNull() {
    inversion.countInversions(null);
  }

  @Test
  public void testCountInversionsSmallInput() {
    Assert.assertEquals(1, inversion.countInversions(new int[] { 2, 1 }));
    Assert.assertEquals(3, inversion.countInversions(new int[] { 3, 2, 1 }));
    Assert.assertEquals(0, inversion.countInversions(new int[] { 1, 2, 3, 4, 5, 6 }));
    Assert.assertEquals(3, inversion.countInversions(new int[] { 1, 3, 5, 2, 4, 6 }));
    Assert.assertEquals(15, inversion.countInversions(new int[] { 6, 5, 4, 3, 2, 1 }));
    Assert.assertEquals(9, inversion.countInversions(new int[] { 4, 5, 6, 1, 2, 3 }));
    Assert.assertEquals(0, inversion.countInversions(new int[] {}));
  }

  @Test
  public void testCountInversionsLargelInput() throws IOException {
    InputStream inputStream = new GZIPInputStream(
      Utils.getInputStreamForResource(UNSORTED_INPUT_NAME));

    int[] arr = Utils.getIntArray(inputStream);

    long invCount = inversion.countInversions(arr);

    Assert.assertTrue(invCount >= 0);
    Assert.assertEquals(2407905288L, invCount);
  }

  private static final String UNSORTED_INPUT_NAME = "IntegerArray.gz";

  private final Inversion inversion = new InversionImpl();
}
