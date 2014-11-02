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

  public QuickSortImpl() {
    this(PivotSelectionStrategy.FIRST);
  }

  public QuickSortImpl(PivotSelectionStrategy pivotSelectionStrategy) {
    this.pivotSelectionStrategy = pivotSelectionStrategy;
  }

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
    switch (pivotSelectionStrategy) {
      case FIRST:
        return leftIndex;
      case MEDIAN_OF_THREE:
        int length = rightIndex - leftIndex + 1;

        int middleIndex = (length - 1) / 2;

        T leftElement = array[leftIndex];
        T middleElement = array[middleIndex];
        T rightElement = array[rightIndex];

        if ((isLessThenOrEquals(middleElement, leftElement)
            && isLessThenOrEquals(leftElement, rightElement))
            || (isLessThenOrEquals(rightElement, leftElement)
            && isLessThenOrEquals(leftElement, middleElement))) {

          return leftIndex;
        }

        if ((isLessThenOrEquals(leftElement, middleElement)
            && isLessThenOrEquals(middleElement, rightElement))
            || (isLessThenOrEquals(rightElement, middleElement)
            && isLessThenOrEquals(middleElement, leftElement))) {

          return middleIndex;
        }

        if ((isLessThenOrEquals(leftElement, rightElement)
            && isLessThenOrEquals(rightElement, middleElement))
            || (isLessThenOrEquals(middleElement, rightElement)
            && isLessThenOrEquals(rightElement, leftElement))) {

          return rightIndex;
        }

      case LAST:
        return rightIndex;
      default:
        throw new UnsupportedOperationException(
          "Unimplemented strategy: " + pivotSelectionStrategy);
    }
  }

  protected void quickSort(T[] array, int leftIndex, int rightIndex, long[] compCount) {
    int length = array.length;

    checkBounds("leftIndex", leftIndex, 0, length - 1);
    checkBounds("rightIndex", rightIndex, 0, length - 1);

    int subLength = rightIndex - leftIndex + 1;

    if (subLength == 1) {
      return;
    }

    compCount[0] += subLength - 1;

    int pivotIndex = choosePivot(array, leftIndex, rightIndex);

    checkBounds("privotIndex", pivotIndex, leftIndex, rightIndex);

    int pivotFinalIndex = partition(array, pivotIndex, leftIndex, rightIndex);

    if (pivotFinalIndex > leftIndex) {
      quickSort(array, leftIndex, pivotFinalIndex - 1, compCount);
    }

    if (pivotFinalIndex < rightIndex) {
      quickSort(array, pivotFinalIndex + 1, rightIndex, compCount);
    }
  }

  private final PivotSelectionStrategy pivotSelectionStrategy;

}
