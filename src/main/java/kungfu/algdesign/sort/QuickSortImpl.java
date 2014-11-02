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

public class QuickSortImpl<T extends Comparable<T>> extends AbstractQuickSort<T> {

  /**
   * Sorts the given array in ascendent order.
   * 
   * @param array Input array
   * @return returns the sum of the length of the recursively processed sub-arrays.
   */
  @Override
  public long sort(T[] array) {
    if (array == null) {
      throw new NullPointerException("array cannot be null.");
    }

    int length = array.length;

    if (length < 2) {
      return 0;
    }

    long[] compCount = new long[1];

    quickSort(array, 0, length - 1, compCount);

    return compCount[0];
  }

  protected void checkBounds(String name, int index, int min, int max) {
    if (index >= min && index <= max) {
      return;
    }

    throw new ArrayIndexOutOfBoundsException(
      name + "=" + index + " exceeds the array's bounds: min=" + min + ", max=" + max);
  }

  @Override
  protected int choosePivot(T[] array, int leftIndex, int rightIndex) {
    // Naive implementation
    return leftIndex;
  }

  protected void quickSort(T[] array, int leftIndex, int rightIndex, long[] compCount) {
    int length = array.length;

    checkBounds("leftIndex", leftIndex, 0, length - 1);
    checkBounds("rightIndex", rightIndex, 0, length - 1);

    int subLength = rightIndex - leftIndex + 1;

    if (subLength == 1) {
      return;
    }

    int pivotIndex = choosePivot(array, leftIndex, rightIndex);

    checkBounds("privotIndex", pivotIndex, leftIndex, rightIndex);

    int pivotFinalIndex = partition(array, pivotIndex, leftIndex, rightIndex);

    if (pivotFinalIndex > leftIndex) {
      quickSort(array, leftIndex, pivotFinalIndex - 1, compCount);

      compCount[0] += pivotFinalIndex - leftIndex - 1;
    }

    if (pivotFinalIndex < rightIndex) {
      quickSort(array, pivotFinalIndex + 1, rightIndex, compCount);

      compCount[0] += rightIndex - pivotFinalIndex - 1;
    }
  }

}
