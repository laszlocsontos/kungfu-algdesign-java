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

/**
 * 
 * @author lcsontos
 *
 * @param <T> Type of elements
 */
public class MergeSortImpl<T extends Comparable<T>> extends AbstractMergeSort<T> {

  /**
   * Sorts the given array in ascendent order.
   * 
   * @param array Input array
   * @return returns the inversion count of the given array, that is, the number of element pairs
   *         which were in wrong order and had to be swapped.
   */
  @Override
  public long sort(T[] array) {
    if (array == null) {
      throw new NullPointerException("array cannot be null.");
    }

    return splitAndMerge(array);
  }

  @SuppressWarnings("unchecked")
  protected long splitAndMerge(T[] array) {
    if (array.length < 2) {
      return 0;
    }

    Object[] arrays = split(array);

    T[] left = (T[]) arrays[0];
    T[] right = (T[]) arrays[1];

    long leftInvCount = splitAndMerge(left);
    long rightInvCount = splitAndMerge(right);

    long splitInvCount = merge(left, right, array);

    // System.out.println(leftInvCount + " " + rightInvCount + " " + splitInvCount);

    return (leftInvCount + rightInvCount + splitInvCount);
  }


}
