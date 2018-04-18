package sorting;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by SSX on 2017/8/31.
 */
public class MergeSort implements ISort {

    @Override
    public void sort(Comparable[] arr) {
        SortUtils.checkArr(arr);
        sort(arr, 0, arr.length - 1);
    }

    private void sort(Comparable[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int center = (left + right) >>> 1;
        sort(arr, left, center);
        sort(arr, center + 1, right);
        merge(arr, left, right, center);
    }

    private void merge(Comparable[] arr, int left, int right, int center) {
        //--------work-----------
        Comparable[] leftArr = Arrays.copyOfRange(arr, left, center + 1);
        Comparable[] rightArr = Arrays.copyOfRange(arr, center + 1, right + 1);
        int leftIndex = 0, rightIndex = 0;
        int arrIndex = left;
        while (leftIndex <= (center - left) && rightIndex <= (right - center - 1)) {
            if (leftArr[leftIndex].compareTo(rightArr[rightIndex]) < 0) {
                arr[arrIndex++] = leftArr[leftIndex++];
            } else {
                arr[arrIndex++] = rightArr[rightIndex++];
            }
        }
        while (leftIndex <= (center - left)) {
            arr[arrIndex++] = leftArr[leftIndex++];
        }
        while (rightIndex <= (right - center - 1)) {
            arr[arrIndex++] = rightArr[rightIndex++];
        }
    }

    @Test
    public void test() {
        SortUtils.sortTest(new MergeSort(), true);
    }

}
