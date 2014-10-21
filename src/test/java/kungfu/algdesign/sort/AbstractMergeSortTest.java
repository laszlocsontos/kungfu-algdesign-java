package kungfu.algdesign.sort;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author lcsontos
 */
public abstract class AbstractMergeSortTest extends AbstractSortTest {

  @Test
  public void testMerge() {
    AbstractMergeSort<Integer> sort = (AbstractMergeSort<Integer>)getSort();

    Integer[] array = new Integer[6];

    sort.merge(new Integer[] { 1, 3, 5 }, new Integer[] { 2, 4, 6 }, array);

    Assert.assertArrayEquals(ARRAY, array);

    sort.merge(new Integer[] { 1, 3 }, new Integer[] { 2, 4, 5, 6 }, array);

    Assert.assertArrayEquals(ARRAY, array);

    sort.merge(new Integer[] { 1, 3, 4, 5 }, new Integer[] { 2, 6 }, array);

    Assert.assertArrayEquals(ARRAY, array);
}

  @Test
  public void testSplit() {
    AbstractMergeSort<Integer> sort = (AbstractMergeSort<Integer>)getSort();

    Object[] arrays = sort.split(ARRAY);

    Assert.assertArrayEquals(new Integer[] { 1, 2, 3 }, (Integer[]) arrays[0]);
    Assert.assertArrayEquals(new Integer[] { 4, 5, 6 }, (Integer[]) arrays[1]);

    arrays = sort.split(new Integer[] { 1, 2, 3 });

    Assert.assertArrayEquals(new Integer[] { 1 }, (Integer[]) arrays[0]);
    Assert.assertArrayEquals(new Integer[] { 2, 3 }, (Integer[]) arrays[1]);
  }

  private static final Integer[] ARRAY = { 1, 2, 3, 4, 5, 6 };

}
