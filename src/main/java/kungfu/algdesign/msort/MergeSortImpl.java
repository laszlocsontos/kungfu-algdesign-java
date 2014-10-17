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

package kungfu.algdesign.msort;

import java.util.Arrays;

/**
 * @author lcsontos
 */
public class MergeSortImpl implements MergeSort {

  @Override
  public <T extends Comparable<T>> void sort(T[] array) {
    if (array == null) {
      throw new NullPointerException("array cannot be null.");
    }

    mergeSort(array);
  }

  protected <T extends Comparable<T>> void mergeSort(T[] array) {
    if (array.length < 2) {
      return;
    }

    int n = array.length;

    T[] left = Arrays.copyOfRange(array, 0, n / 2);
    T[] right = Arrays.copyOfRange(array, n / 2, n);

    mergeSort(left);
    mergeSort(right);

    merge(left, right, array);
  }

  protected <T extends Comparable<T>> void merge(T[] left, T[] right, T[] array) {
    int i = 0, j = 0;

    while (i < left.length || j < right.length) {
      int k = i + j;

      if (i < left.length && j < right.length) {
        if (isLessThenOrEquals(left[i], right[j])) {
          array[k] = left[i++];
        }
        else {
          array[k] = right[j++];
        }
      }
      else if (i < left.length) {
        array[k] = left[i++];
      }
      else {
        array[k] = right[j++];
      }
    }
  }

  protected <T extends Comparable<T>> boolean isLessThenOrEquals(T left, T right) {
    if (left == null) {
      return true;
    }

    if (right == null) {
      return false;
    }

    return left.compareTo(right) <= 0;
  }

}
