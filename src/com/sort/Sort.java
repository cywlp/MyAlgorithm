package com.sort;

import java.util.Arrays;

/**
 * @author :珠代
 * @description :排序算法类
 * @create :2022-03-24 14:38:00
 */
public class Sort {
    /**
     * 冒泡排序
     * O(n^2)
     *
     * @param arr
     */
    public void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = arr.length; i > 0; i--) {
            boolean flag = false; // 初始化标志位
            for (int j = 0; j < i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    flag = true;
                }
            }
            if (!flag) break;     // 内循环未交换任何元素，则跳出
        }
    }

    /**
     * 冒泡优化版
     *
     * @param arr
     */
    public void bubbleSort_v2(int[] arr) {
        int len = arr.length - 1;
        while (true) {
            int last = 0;//表示最后一次交换索引位置
            for (int i = 0; i < len; i++) {
                System.out.println("比较次数" + i);
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                    last = i;
                }
            }
            len = last;
            if (len == 0) {
                break;
            }
        }
    }

    /**
     * 插入排序
     * O(n^2)
     *
     * @param arr
     */
    public void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                }
            }
        }
    }

    /**
     * 插排优化
     *
     * @param arr
     */
    public void insertSort_v2(int[] arr) {
        //i待插入的索引
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];//代表待插入的值
            int j = i - 1;//代表已排序区域的元素索引
            while (j >= 0) {
                if (temp < arr[j]) {
                    arr[j + 1] = arr[j];
                } else {
                    break;//退出循环，减少比较次数
                }
                j--;
                arr[j + 1] = temp;
            }
        }
    }

    /**
     * 选择排序
     * O(n^2)
     *
     * @param arr
     */
    public void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            //i代表每轮选择最小元素要交换到的目标索引
            int min = i;//代表最小元素索引
            for (int j = i + 1; j < len; j++) {
                min = arr[j] < arr[min] ? j : min;
            }
            if (min != i) {
                swap(arr, min, i);
            }
        }
    }

    /**
     * 快速排序双边循环
     * O(nlogn)
     * @param arr 数组
     * @param l 左边界
     * @param r 右边界
     */
    public void quickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int i = l, j = r;
        while (i < j) {
            //j从右找小的
            while (i < j && arr[j] > arr[l]) {//选择最左边arr[l]为基准点
                j--;
            }
            //i从左找大的
            while (i < j && arr[i] <= arr[l]) {
                i++;
            }
            swap(arr, i, j);
        }
        swap(arr, i, l);
        quickSort(arr, 0, i - 1);
        quickSort(arr, i + 1, r);
    }

    /**
     * 快速排序单边循环
     *
     * @param arr
     * @param l
     * @param r
     */
    public void quickSort_v2(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int i = l;
        for (int j = l; j < r; j++) {
            if (arr[j] < arr[r]) { //选取最右侧arr[r]为基准点元素
                if (i != j) {
                    swap(arr, i, j);
                }
                i++;

            }
        }
        if (i != r) {
            swap(arr, i, r);//基准点所在的正确索引，用于确定下一轮分区边界
        }
        quickSort_v2(arr, 0, i - 1);
        quickSort_v2(arr, i + 1, r);
    }

    /**
     * 归并排序
     * O（nlogn）
     *
     * @param arr
     * @param l
     * @param r
     */
    public void mergeSort(int[] arr, int l, int r) {
        //终止条件
        if (l >= r) return;
//        int m=(l+r)/2; (l+r)/2=r/2+l/2=r-r/2+l/2=r+(l-r)/2
        //递归划分
        //int m = r-(r-l)/2;× 5-int（2.5）=3会得出错误结果，实际上应该为int（5-2.5）=2
        // 防止内存溢出
        int m = l + (r - l) / 2;
        mergeSort(arr, l, m);
        mergeSort(arr, m + 1, r);
        //合并数组
        int[] temp = new int[r - l + 1];
        int i = 0, j = m - l + 1;// 两指针分别指向左/右子数组的首个元素

        for (int k = l; k <= r; k++)
            temp[k - l] = arr[k];

        for (int k = l; k <= r; k++) {
            if (i == m - l + 1) {
                arr[k] = temp[j++];
            } else if (j == r - l + 1 || temp[i] <= temp[j])
                arr[k] = temp[i++];
            else {
                arr[k] = temp[j++];
            }
        }

    }

    /**
     * 堆排序
     *
     * @param arr
     */
    public void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    //某数处于index位置，往上继续移动
    public void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    //某个数在index位置能否往下移动
    public void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;//左孩子的下标
        while (left < heapSize) {//下方还有孩子时
            //两个孩子中，谁值大，把下标给largest
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            //父和较大节点之间，谁值大，把下标给largest
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    //数组中两数交换
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}

