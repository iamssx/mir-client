package sorting;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by SSX on 2017/8/30.
 */
public class SortUtils {

    public static void checkArr(Object[] arr) {
        if (arr == null || arr.length <= 1) {
            throw new IllegalArgumentException();
        }
    }

    public static boolean isSorted(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(arr[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }

    public static void swap(Object[] arr, int pos, int pos2) {
        if (pos < 0 || pos2 < 0 || pos >= arr.length || pos2 >= arr.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (pos == pos2) {
            return;
        }
        Object tmp = arr[pos];
        arr[pos] = arr[pos2];
        arr[pos2] = tmp;
    }

    public void swap(byte[] arr, int pos, int pos2) {
        if (pos < 0 || pos2 < 0 || pos >= arr.length || pos2 >= arr.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        byte tmp = arr[pos];
        arr[pos] = arr[pos2];
        arr[pos2] = tmp;
    }

    public static void swap(short[] arr, int pos, int pos2) {
        if (pos < 0 || pos2 < 0 || pos >= arr.length || pos2 >= arr.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        short tmp = arr[pos];
        arr[pos] = arr[pos2];
        arr[pos2] = tmp;
    }

    public static void swap(int[] arr, int pos, int pos2) {
        if (pos < 0 || pos2 < 0 || pos >= arr.length || pos2 >= arr.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int tmp = arr[pos];
        arr[pos] = arr[pos2];
        arr[pos2] = tmp;
    }

    public static void swap(long[] arr, int pos, int pos2) {
        if (pos < 0 || pos2 < 0 || pos >= arr.length || pos2 >= arr.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        long tmp = arr[pos];
        arr[pos] = arr[pos2];
        arr[pos2] = tmp;
    }

    public static void sortTest(ISort sort, boolean print) {
        Random random = new Random();
        Integer[] arr = new Integer[10];
        long endTime = System.currentTimeMillis() + 10 * 1000;
        while (System.currentTimeMillis() <= endTime) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = random.nextInt(200);
            }
            Integer[] oldArr = Arrays.copyOf(arr, arr.length);
            sort.sort(arr);
            if (print) {
                System.out.println("old: " + Arrays.toString(oldArr));
                System.out.println(Arrays.toString(arr));
            }
            if (!SortUtils.isSorted(arr)) {
                System.err.println(Arrays.toString(arr));
                sort.sort(oldArr);
                throw new RuntimeException();
            }
        }
    }
}
