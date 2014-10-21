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

  @Override
  public void sort(T[] array) {
    if (array == null) {
      throw new NullPointerException("array cannot be null.");
    }

    splitAndMerge(array);
  }

  @SuppressWarnings("unchecked")
  protected void splitAndMerge(T[] array) {
    if (array.length < 2) {
      return;
    }

    Object[] arrays = split(array);

    T[] left = (T[]) arrays[0];
    T[] right = (T[]) arrays[1];

    splitAndMerge(left);
    splitAndMerge(right);

    merge(left, right, array);
  }


}
