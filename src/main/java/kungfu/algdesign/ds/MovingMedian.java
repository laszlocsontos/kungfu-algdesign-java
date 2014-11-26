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

package kungfu.algdesign.ds;

import java.util.Queue;

import com.google.common.collect.MinMaxPriorityQueue;

/**
 * @author lcsontos
 */
public class MovingMedian {

  public static void calculate(Queue<Integer> data, Queue<Integer> medians) {
    MinMaxPriorityQueue<Integer> minHeap = MinMaxPriorityQueue.create();
    MinMaxPriorityQueue<Integer> maxHeap = MinMaxPriorityQueue.create();

    minHeap.add(Integer.MIN_VALUE);
    maxHeap.add(Integer.MAX_VALUE);

    Integer item = null;
    Integer median = null;

    while ((item = data.poll()) != null) {
      if (median == null) {
        maxHeap.add(item);
      } else if (item >= median) {
        maxHeap.add(item);
      } else {
        minHeap.add(item);
      }

      if (maxHeap.size() - minHeap.size() == 2) {
        minHeap.add(maxHeap.pollFirst());
      } else if (minHeap.size() - maxHeap.size() == 2) {
        maxHeap.add(minHeap.pollLast());
      }

      if (minHeap.size() == maxHeap.size() || minHeap.size() > maxHeap.size()) {
        median = minHeap.peekLast();
      } else {
        median = maxHeap.peekFirst();
      }

      medians.add(median);
    }
  }

}
