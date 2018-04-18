package sorting;

import org.junit.Test;

/**
 * Created by SSX on 2017/8/30.
 */
public class InsertSort implements ISort {

    @Override
    public void sort(Comparable[] arr) {
        SortUtils.checkArr(arr);
        for (int i = 1; i < arr.length; i++) {
            Comparable key = arr[i];
            int keyIndex = i;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j].compareTo(key) > 0) {
//                    SortUtils.swap(arr, j, j + 1);
                    arr[j + 1] = arr[j];
                    keyIndex = j;
                } else {
                    break;
                }
            }
            arr[keyIndex] = key;
        }
    }

    @Test
    public void test() {
        SortUtils.sortTest(new InsertSort(), true);
    }
}
