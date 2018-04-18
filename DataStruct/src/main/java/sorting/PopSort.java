package sorting;

import org.junit.Test;

/**
 * Created by SSX on 2017/8/30.
 */
public class PopSort implements ISort {

    @Override
    public void sort(Comparable[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j].compareTo(arr[j - 1]) < 0) {
                    SortUtils.swap(arr, j, j - 1);
                }
            }
        }
    }

    @Test
    public void test() {
        SortUtils.sortTest(new PopSort(), true);
    }
}
