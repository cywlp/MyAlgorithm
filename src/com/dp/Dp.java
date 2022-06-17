package com.dp;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author :珠代
 * @description :动态规划类
 * @create :2022-03-24 14:46:00
 */
public class Dp {

    /**
     * 斐波那契数列的第n位 0 1 1 2 3 5......
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n == 0) return 0;
        int a = 0, b = 1;
        for (int i = 1; i < n; i++) {
            int temp = b;
            b = (a + b) % 1000000007;
            a = temp;
        }
        return b;
    }

    /**
     * 青蛙跳台阶问题
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法
     *
     * @param n
     * @return
     */
    public int numWays(int n) {
        // if(n==0) return 1;
        //初始化初值
        int a = 1;//n=0，有一种跳法
        int b = 1;//n=1，有一种跳法
        //从n=2开始遍历
        for (int i = 2; i <= n; i++) {
            int temp = b;
            b = (a + b) % 1000000007;
            a = temp;
        }
        return b;
    }

    /**
     * 连续子数组的最大和输入一个整型数组
     * 数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
     * 要求时间复杂度为O(n)。
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        /**
         * 转移方程
         * dp[i]=dp[i−1]+nums[i],dp[i−1]>0
         * dp[i]=nums[i],dp[i−1]≤0
         */
        if (nums.length == 0) return 0;
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] += Math.max(nums[i - 1], 0);
            max = Math.max(max, nums[i]);
        }
        return max;
    }

    /**
     * 正则表达式匹配
     * '.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {

        int m = s.length() + 1, n = p.length() + 1;

        boolean[][] dp = new boolean[m][n];
        //初始化
        dp[0][0] = true;//代表两个空字符串能够匹配。
        //初始化第一行，只有偶数位为*，才能匹配上
        for (int i = 2; i < n; i += 2) {
            dp[0][i] = dp[0][i - 2] && p.charAt(i - 1) == '*';
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
//                if (p.charAt(j - 1) == '*') {
//                    if (dp[i][j - 2]) dp[i][j] = true;
//                    if (dp[i - 1][j] && s.charAt(i - 1) == p.charAt(j - 2)) dp[i][j] = true;
//                    if (dp[i - 1][j] && p.charAt(j - 2) == '.') dp[i][j] = true;
//                } else {
//                    if (dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1)) dp[i][j] = true;
//                    if (dp[i - 1][j - 1] && p.charAt(j - 1) == '.') dp[i][j] = true;
//                }
                dp[i][j] = p.charAt(j - 1) == '*' ? dp[i][j - 2]
                        || dp[i - 1][j] && s.charAt(i - 1) == p.charAt(j - 2)
                        || dp[i - 1][j] && p.charAt(j - 2) == '.'
                        : dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1)
                        || dp[i - 1][j - 1] && p.charAt(j - 1) == '.';

            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 礼物的最大价值
     * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，
     * 并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物
     * @param grid
     * @return
     */
    public int maxValue(int[][] grid) {
        int m=grid.length;//行
        int n=grid[0].length;//列
        for (int i=1;i<m;i++){ //初始化第一列
            grid[i][0]+=grid[i-1][0];
        }
        for (int i=1;i<n;i++){//初始化第一行
            grid[0][i]+=grid[0][i-1];
        }
        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                grid[i][j]=Math.max(grid[i-1][j],grid[i][j-1])+grid[i][j];
            }
        }
        return grid[m-1][n-1];
    }

    /**
     * 最长不含重复字符的子字符串
     *请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
     * @param s 字符串
     * @return 该最长子字符串的长度
     */
    public int lengthOfLongestSubstring(String s) {
        //双指针+哈希表
        int res =0,i=-1;
        HashMap<Character, Integer> hs = new HashMap<>();
        for (int j=0;j<s.length();j++){
            if(hs.containsKey(s.charAt(j))){
//                    i=hs.get(s.charAt(j)); ×
                i=Math.max(i,hs.get(s.charAt(j)));
            }
            hs.put(s.charAt(j),j);
            res=Math.max(res,j-i);
        }
        return res;
        //动态规划+哈希表
//        int res = 0, temp = 0;
//        HashMap<Character, Integer> hs = new HashMap<>();
//        for (int j = 0; j < s.length(); j++) {
//            int i = hs.getOrDefault(s.charAt(j), -1);
//            hs.put(s.charAt(j), j);
//            //dp[j]=dp[j−1]+1,dp[j−1]<j−i
//            //dp[j]=j−i,dp[j−1]≥j−i
//            temp = temp < j - i ? temp + 1 : j - i;
//            res = Math.max(res, temp);
//        }
//        return res;
    }

    /**
     *丑数
     * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。1也算丑数
     * @param n 第 n 个丑数
     * @return 第 n 个丑数
     */
    public int nthUglyNumber(int n) {
        int a = 0, b = 0, c = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
            dp[i] = Math.min(Math.min(n2, n3), n5);
            if (dp[i] == n2) a++;
            if (dp[i] == n3) b++;
            if (dp[i] == n5) c++;
        }
        return dp[n - 1];
    }

    /**
     * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。
     * @param n 输入n
     * @return 打印出s的所有可能的值出现的概率
     */
    public double[] dicesProbability(int n) {
        double[] dp = new double[6];
        Arrays.fill(dp, 1.0 / 6.0);//初始化初值
        for (int i = 2; i <= n; i++) {
            double[] temp = new double[5 * i + 1];//n个骰子 范围为[n,6n]共6n-n+1 = 5n+1
            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < 6; k++) {
                    temp[j + k] += dp[j] / 6.0;
                }
            }
            dp = temp;
        }
        return dp;
    }

    /**
     * 股票的最大利润
     * @param prices 某股票的价格按照时间先后顺序存储在数组中
     * @return 可能获得的最大利润
     */
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
//        int[][] dp = new int[prices.length][2];
//        dp[0][0] = 0;//表示第一天不持股
//        dp[0][1] = -prices[0];//表示第一天持股
//        for (int i = 1; i < prices.length; i++) {
//            dp[i][0] = Math.max(dp[i - 1][1] + prices[i],dp[i-1][0]);
//            dp[i][1] = Math.max(dp[i-1][1],-prices[i]);
//        }
//        return dp[prices.length - 1][0];
        int a, b;
        a = 0;//表示第一天不持股
        b = -prices[0];//表示第一天持股
        for (int i = 1; i < prices.length; i++) {
            a = Math.max(b + prices[i], a);
            b = Math.max(b, -prices[i]);
        }
        return a;
    }
}
