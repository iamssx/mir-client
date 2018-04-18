package sorting;

import org.junit.Test;

import java.util.Random;

/**
 * Created by SSX on 2017/8/30.
 */
public class SelectionSort implements ISort {

    @Override
    public void sort(Comparable[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            Comparable min = arr[i];
            int pos = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(min) < 0) {
                    min = arr[j];
                    pos = j;
                }
            }
            SortUtils.swap(arr, i, pos);
        }
    }

    @Test
    public void sortTest() {
        Random random = new Random();
        Integer[] arr = new Integer[10];
        while (true) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = random.nextInt(100);
            }
            sort(arr);
            if (!SortUtils.isSorted(arr)) {
                throw new RuntimeException();
            }
        }
    }
}
