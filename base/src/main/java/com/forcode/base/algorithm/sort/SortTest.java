package com.forcode.base.algorithm.sort;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.util.RandomUtil;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 *
 * @author: TJ
 * @date:  2021-08-05
 **/
public class SortTest {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 6, 4, 12, 54, 65, 21, 30, 16, 8, 24, 12, 50};
        System.out.println("原始数组: " + Arrays.toString(arr));
        bubbleSort(arr.clone());
        selectionSort(arr.clone());
        insertionSort(arr.clone());
        shellSort(arr.clone());
        mergeSort(arr.clone());
        quickSort(arr.clone());
        quickSortV2(arr.clone());

        // 效率比较
//        int[] test = new int[800000];
//        for (int i = 0; i < 800000; i++) {
//            test[i] = RandomUtil.randomInt(500000);
//        }
//        int[] clone1 = test.clone();
//        int[] clone2 = test.clone();
//        int[] clone3 = test.clone();
//        int[] clone4 = test.clone();
//        int[] clone5 = test.clone();
//        int[] clone6 = test.clone();
//        int[] clone7 = test.clone();
//        StopWatch stopWatch = StopWatch.create("排序");
//        stopWatch.start("冒泡");
//        bubbleSort(clone1);
//        stopWatch.stop();
//
//        stopWatch.start("选择");
//        selectionSort(clone2);
//        stopWatch.stop();
//
//        stopWatch.start("插入");
//        insertionSort(clone3);
//        stopWatch.stop();

//        stopWatch.start("希尔");
//        shellSort(clone4);
//        stopWatch.stop();
//
//        stopWatch.start("归并");
//        mergeSort(clone5);
//        stopWatch.stop();
//
//        stopWatch.start("快速");
//        quickSort(clone6);
//        stopWatch.stop();
//
//        stopWatch.start("快速V2");
//        quickSortV2(clone7);
//        stopWatch.stop();
//        System.out.println(stopWatch.prettyPrint());

    }

    /**
     * 冒泡排序
     */
    private static void bubbleSort(int[] arr) {
        int length = arr.length;
        // 外层循环表示需要比较的轮次
        for (int i = 0; i < length - 1; i++) {
            // 内层循环比较两两元素
            for (int j = 0; j < length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("冒泡排序: " + Arrays.toString(arr));
    }


    /**
     * 选择排序
     * 在未排序数组中找出最小, 和未排序数组的第一个元素交换
     */
    private static void selectionSort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {

            // 最小数索引
            int minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 将最小数交换到前面来
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        System.out.println("选择排序: " + Arrays.toString(arr));
    }

    /**
     * 插入排序
     * 对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入
     */
    private static void insertionSort(int[] arr) {
        int length = arr.length;
        // 外层遍历待排序数据, 第一个数当做基准
        for (int i = 1; i < length; i++) {

            int current = arr[i];
            // 内层遍历已排序数据, 从后往前比较
            for (int j = i -1; j >= 0 ; j--) {
                if (arr[j] > current) {
                    arr[j + 1] = arr[j];
                } else {
                    arr[j + 1] = current;
                    break;
                }
            }
        }
        System.out.println("插入排序: " + Arrays.toString(arr));
    }

    /**
     * 希尔排序
     */
    private static void shellSort(int[] arr) {
        int length = arr.length;
        int temp;
        // 外层循环确定分组
        for (int step = length / 2; step >= 1; step /= 2) {
            for (int i = step; i < length; i++) {
                temp = arr[i];
                int j = i - step;
                while (j >= 0 && arr[j] > temp) {
                    arr[j + step] = arr[j];
                    j -= step;
                }
                arr[j + step] = temp;
            }
        }
        System.out.println("希尔排序: " + Arrays.toString(arr));
    }

    /**
     * 归并排序
     */
    private static void mergeSort(int[] arr) {
        System.out.println("归并排序: " + Arrays.toString(spilt(arr)));
    }

    // 分解
    private static int[] spilt(int[] arr) {
        int length = arr.length;
        if (length == 1) {
            return arr;
        }
        int middle = length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, length);
        return merge(spilt(left), spilt(right));
    }

    // 归并 - 将两段排序好的数组结合成一个排序数组, 双指针法
    private static int[] merge(int[] left, int[] right) {
        int[] res = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0, l = res.length; index < l; index++) {
            // 用i指代left的指针, j指代right的指针
            if (i >= left.length) {
                res[index] = right[j++];
            } else if (j >= right.length) {
                res[index] = left[i++];
            } else if (left[i] <= right[j]) {
                res[index] = left[i++];
            } else {
                res[index] = right[j++];
            }
        }
        return res;
    }

    /**
     * 快速排序
     */
    private static void quickSort(int[] arr) {
        process(arr, 0, arr.length -1);
        System.out.println("快速排序: " + Arrays.toString(arr));
    }

    private static void quickSortV2(int[] arr) {
        processV2(arr, 0, arr.length - 1);
        System.out.println("快速排序V2: " + Arrays.toString(arr));
    }


    private static void process(int[] arr, int left, int right) {

        if (left >= right) {
            return;
        }
        int partition = partition(arr, left, right);
        // 对两边的子序列继续分割排序, partition索引位置值已处于中间, 不用再参与排序计算
        process(arr, left, partition - 1);
        process(arr, partition + 1, right);
    }

    // 根据基准值分割, 左边比基准小, 右边比基准大
    private static int partition(int[] arr, int left, int right) {
        // 基准值
        int pivot = arr[left];
        while (true) {

            // 从右找比基准值小的数
            while (left < right) {
                if (arr[right] < pivot) {
                    arr[left] = arr[right];
                    left++;
                    break;
                }
                right--;
            }
            // 从左找比基准值大的数
            while (left < right) {
                if (arr[left] > pivot) {
                    arr[right] = arr[left];
                    right--;
                    break;
                }
                left++;
            }
            // 当left >= right时, 表示当前交换完毕
            if (left >= right) {
                arr[left] = pivot;
                break;
            }
        }
        return left;
    }

    private static void processV2(int[] arr, int left, int right) {

        if (left >= right) {
            return;
        }
        int[] res = partitionV2(arr, left, right);
        processV2(arr, left, res[0] - 1);
        processV2(arr, res[1] + 1, right);
    }

    /**
     *  利用荷兰国旗思想优化
     *  1. arr[i] < pivot, arr[i]和小于区的右边第一个元素交换, 同时小于区向右+1, i++
     *  2. arr[i] = pivot, i++
     *  3. arr[i] > pivot, arr[i]和大于区的左边第一个元素交换, 同时大于区向左+1, i不变
     *
     * @param arr 目标数组
     * @param left 左起始值
     * @param right 右起始值
     * @return 返回相等区域的左右边界索引
     */
    private static int[] partitionV2(int[] arr, int left, int right) {

        if (left > right) {
            return new int[]{-1, -1};
        }
        if (left == right) {
            return new int[]{left, right};
        }
        int pivot = arr[(left + right) / 2];
        int current = left;
        int L = left - 1;
        int R = right;
        while (current <= R) {
            if (arr[current] < pivot) {
                swap(arr, current++, ++L);
            } else if (arr[current] == pivot){
                current++;
            } else {
                swap(arr, current, R--);
            }
        }
        return new int[]{L + 1, R};
    }

    private static void swap(int[] arr, int start, int end) {
        int temp = arr[end];
        arr[end] = arr[start];
        arr[start] = temp;
    }

}
