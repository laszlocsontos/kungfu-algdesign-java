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

import java.util.Arrays;

/**
 * @author lcsontos
 *
 * @param <T> Type of elements
 */
public abstract class AbstractMergeSort<T extends Comparable<T>> extends AbstractSort<T> {

  protected int merge(T[] left, T[] right, T[] array) {
    int invCount = 0;
    int leftIndex = 0;
    int rightIndex = 0;

    while (leftIndex < left.length || rightIndex < right.length) {
      int index = leftIndex + rightIndex;

      if (leftIndex < left.length && rightIndex < right.length) {
        if (isLessThenOrEquals(left[leftIndex], right[rightIndex])) {
          array[index] = left[leftIndex++];
        } else {
          array[index] = right[rightIndex++];

          invCount += (left.length - leftIndex);
        }
      } else if (leftIndex < left.length) {
        array[index] = left[leftIndex++];
      } else {
        array[index] = right[rightIndex++];
      }
    }

    return invCount;
  }

  protected Object[] split(T[] array) {
    int length = array.length;

    T[] left = Arrays.copyOfRange(array, 0, length / 2);
    T[] right = Arrays.copyOfRange(array, length / 2, length);

    return new Object[] { left, right };
  }

}
