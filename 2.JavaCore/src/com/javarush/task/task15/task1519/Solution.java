package com.javarush.task.task15.task1519;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Разные методы для разных типов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = "";
        label:
        while (!s.equals("exit")) {
            s = reader.readLine();
            if (s.contains(".")) try {
                print(Double.parseDouble(s));
                continue;
            } catch (Exception e) {}
            try {
                int i = Integer.parseInt(s);
                if (i <= 0 || i >= 128) { print(i);
                }
                else { print((short) i);
                }
                continue;
            } catch (Exception e) {}
            if (!s.equals("exit")) print(s);

        }
    }

    public static void print(Double value) {
        System.out.println("Это тип Double, значение " + value);
    }

    public static void print(String value) {
        System.out.println("Это тип String, значение " + value);
    }

    public static void print(short value) {
        System.out.println("Это тип short, значение " + value);
    }

    public static void print(Integer value) {
        System.out.println("Это тип Integer, значение " + value);
    }
}
