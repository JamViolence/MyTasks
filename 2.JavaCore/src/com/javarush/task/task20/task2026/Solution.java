package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");


    }

    public static int getRectangleCount(byte[][] a) {
        int count = 0;
        while (containsRectangle(a)) {
            count++;
            int[] coords = getRectangleCoords(a);
            a = deleteRectangle(a, coords);
        }
        return count;
    }

    public static boolean containsRectangle(byte[][] a) {
        for (int y = 0; y < a.length; y++) {
            for (int x = 0; x < a[y].length; x++) {
                if (a[y][x] == 1) return true;
            }
        }
        return false;
    }

    public static int[] getRectangleCoords(byte[][] a) {
        int[] coords = {0,0,0,0};
        label:
        for (int y = 0; y < a.length; y++) {
            for (int x = 0; x < a[y].length; x++) {
                if (a[y][x] == 1) {
                    coords[0] = x;
                    coords[1] = y;
                    break label;
                }
            }
        }
        int[] coords2 = getSecondRectangleCoords(a, coords);
        coords[2] = coords2[0];
        coords[3] = coords2[1];
        return coords;
    }

    public static int[] getSecondRectangleCoords(byte[][] a, int[] coords) {
        int[] coords2 = {coords[0],coords[1]};
        for (int y = coords2[1]; y < a.length; y++) {
            if (a[y][coords[0]] == 0) break;
            coords2[1] = y;
        }
        for (int x = coords2[0]; x < a[coords[1]].length; x++) {
            if (a[coords[1]][x] == 0) break;
            coords2[0] = x;
        }
        return coords2;
    }

    public static byte[][] deleteRectangle(byte[][] a, int[] coords) {
        for (int y = coords[1]; y <= coords[3]; y++) {
            for (int x = coords[0]; x <= coords[2]; x++) {
                a[y][x] = 0;
            }
        }
        return a;
    }

    public static void print(byte[][] a) {
        for (int y = 0; y < a.length; y++) {
            System.out.print("[");
            for (int x = 0; x < a[y].length; x++) {
                System.out.print(a[y][x] + " ");
            }
            System.out.println("]");
        }
    }
}
