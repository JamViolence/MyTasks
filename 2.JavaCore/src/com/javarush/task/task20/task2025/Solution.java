package com.javarush.task.task20.task2025;

import java.util.*;

/* 
Алгоритмы-числа
*/

public class Solution {
    public static long[][] matrix;
    public static TreeSet<Long> result;
    public static int n1;
    public static int[] numSeq;
    public static long l1;

    public static long[] getNumbers(long N) {
        if (N < 1) return null;
        l1 = N;
        n1 = (int) Math.log10(N) + 1;
        result = new TreeSet<>();
        setMatrix();
        search();
        result.removeIf(a -> a>=l1);
        long[] longResult = new long[result.size()];
        int i = 0;
        for (Long l: result) {
            longResult[i] = l;
            i++;
        }
        return longResult;
    }

    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(1000)));
        long b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);

        a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(Long.MAX_VALUE)));
        b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);
    }

    public static void setMatrix() {
        matrix = new long[11][n1+1];
        for (int x = 0; x < matrix.length; x++) matrix[x][0] = 1;
        for (int x = 1; x < matrix[1].length; x++) matrix[1][x] = 1;
        for (int x = 2; x < matrix.length ; x++) {
            for (int y = 1; y < matrix[x].length; y++) {
                long num = x;
                for (int z = 1; z < y; z++) num *= x;
                matrix[x][y] = num;
            }
        }
    }

    public static void search() {
        numSeq = new int[n1];
        Arrays.fill(numSeq, 9);
        while (numSeq[numSeq.length-1] > 0) {
            check(numSeq, n1);
            decreaseNum(0);
        }
    }


    public static void check(int[] numSeq2, int n2) {
        long sum = 0;
        for (int i = 0; i < numSeq2.length; i++) sum += matrix[numSeq2[i]][n2];
        if (sum < 0) return;
        int n = (int) Math.log10(sum) + 1;
        int[] sortedSum = new int[n];
        long f = matrix[10][n-1];
        long newSum = sum;
        for (int i = 0; i < sortedSum.length; i++) {
            int k = (int) (newSum / f);
            sortedSum[i] = k;
            newSum -= f*k;
            f /= 10;
        }
        Arrays.sort(sortedSum);
        boolean found = true;
        if (sortedSum.length == numSeq2.length) {
            for (int i = 0; i < sortedSum.length; i++) {
                if (!(sortedSum[i] == numSeq2[i])) found = false;
            }
            if (found) {
                result.add(sum);
                return;
            }
        }
        if (numSeq2[0] == 0) {
            int[] newSeq = new int[numSeq2.length-1];
            for (int i = 0; i < newSeq.length; i++) newSeq[i] = numSeq2[i+1];
            n2--;
            check(newSeq, n2);
        }
    }

    public static void decreaseNum(int i) {
        int num = --numSeq[i];
        if (num < 0 && numSeq[i+1] > 0) {
            int d = --numSeq[i+1];
            for ( ; i >= 0 ; i--) {
                numSeq[i] = d;
            }
        } else if (num < 0 && numSeq[i+1] == 0) {
            decreaseNum(++i);
        }
    }

}
