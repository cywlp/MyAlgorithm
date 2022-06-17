package com.sundry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author :珠代
 * @description :一些零碎的算法
 * @create :2022-04-18 21:51:00
 */
public class Sundry {

    /**
     * 从a~b随机到c~d随机
     */
    //给定random函数返回1~5，利用此函数返回0~7
    private int random() {
        return (int) (Math.random() * 5 + 1);
    }

    //中间函数，等概率返回0、1
    //1 2 3 4 5
    // 调用random函数 1、2返回0, 4、5返回1
    // 如果得到3则重新随机
    private int ep01() {
        int res = 0;
        do {
            res = random();
        } while (res == 3);
        return res < 3 ? 0 : 1;
    }

    //返回0~7随机数 既000~111
    public int myRandom() {
        return (equal() << 2) + (equal() << 1) + (equal() << 0);
    }

    //给定notEqual函数不等概率返回0,1，利用此函数返回等概率0,1
    private int notEqual() {
        return Math.random() < 0.86 ? 0 : 1;
    }

    //等概率返回0,1
    private int equal() {
        int res = 0;
        do {
            res = notEqual();//00 11 不要 因为不等概率，01 10 等概率，他们都是1的概率*0的概率
        } while (res == notEqual());
        return res;
    }

    /**
     * n皇后
     *
     * @param n n
     * @return 返回有多少种解法
     */
    public List<List<String>> solveNQueens(int n) {
        if (n < 1) {
            return null;
        }
        int[] record = new int[n];//记录每行皇后的列数
        List<List<String>> lists = new ArrayList<>();
        process(0,record,n,lists);
        return lists;
    }

    //从i行开始
    static int process(int i, int[] record, int n, List<List<String>> lists) {
        if (i == n) {
            List<String> list = new ArrayList<>();
            for (int m = 0; m < n; m++) {
                char[] row = new char[n];//第m行的字符
                Arrays.fill(row, '.');//先全部填为'.'
                row[record[m]] = 'Q';
                list.add(new String(row));
            }
            lists.add(list);
            return 1;//终止行
        }
        int res = 0;
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                res += process(i + 1, record, n, lists);
            }
        }
        return res;
    }

    static boolean isValid(int[] record, int i, int j) {
        //前i个皇后坐标,i,record[i] 当前行坐标 i,j
        for (int k = 0; k < i; k++) {
            if (record[k] == j || Math.abs(k - i) == Math.abs(record[k] - j)) {
                return false;
            }
        }
        return true;
    }
}
