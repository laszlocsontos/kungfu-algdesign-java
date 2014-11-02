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

  protected abstract int choosePivot(T[] array, int leftIndex, int rightIndex);

  protected int partition(T[] array, int pivotIndex, int leftIndex, int rightIndex) {
    swap(array, leftIndex, pivotIndex);

    T pivot = array[leftIndex];

    int splitIndex = leftIndex + 1;

    for (int currentIndex = splitIndex ; currentIndex <= rightIndex; currentIndex++) {
      T currentElement = array[currentIndex];

      if (isLessThenOrEquals(currentElement, pivot)) {
        swap(array, currentIndex, splitIndex++);
      }
    }

    pivotIndex = splitIndex - 1;

    swap(array, leftIndex, pivotIndex);

    return pivotIndex;
  }

  private void swap(T[] array, int index1, int index2) {
    if (index1 == index2) {
      return;
    }

    T tmp = array[index1];

    array[index1] = array[index2];
    array[index2] = tmp;
  }
}
