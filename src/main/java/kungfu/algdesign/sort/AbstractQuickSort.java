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
 * @author lcsontos
 *
 * @param <T> Type of elements
 */
public abstract class AbstractQuickSort<T extends Comparable<T>> extends AbstractSort<T> {

  protected void partition(T[] array, int pivotIndex, int leftIndex, int rightIndex) {
    int length = array.length;

    checkBounds("leftIndex", leftIndex, 0, length - 1);
    checkBounds("rightIndex", rightIndex, 0, length - 1);
    checkBounds("privotIndex", pivotIndex, leftIndex, rightIndex);

    swap(array, leftIndex, pivotIndex);

    T pivot = array[leftIndex];

    int splitIndex = leftIndex + 1;

    for (int currentIndex = splitIndex ; currentIndex <= rightIndex; currentIndex++) {
      if (isLessThenOrEquals(pivot, array[currentIndex])) {
        continue;
      }

      swap(array, currentIndex, splitIndex++);
    }

    swap(array, leftIndex, splitIndex - 1);
  }

  private void checkBounds(String name, int index, int min, int max) {
    if (index >= min && index <= max) {
      return;
    }

    throw new ArrayIndexOutOfBoundsException(
      "name=" + index + "exceeds the array's bounds: min=" + min + ", max=" + max);
  }

  private void swap(T[] array, int index1, int index2) {
    T tmp = array[index1];

    array[index1] = array[index2];
    array[index2] = tmp;
  }
}
