package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();
        BufferedReader reader1 = new BufferedReader(new FileReader(fileName1));
        BufferedReader reader2 = new BufferedReader(new FileReader(fileName2));
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        while (reader1.ready()) {
            list1.add(reader1.readLine());
        }
        while (reader2.ready()) {
            list2.add(reader2.readLine());
        }
        reader1.close();
        reader2.close();
        int x = 0;
        int y = 0;
        while (x < list1.size() && y < list2.size()) {
            if (list1.get(x).equals(list2.get(y))) { lines.add(new LineItem(Type.SAME, list1.get(x++))); y++; }
            else if (list1.get(x+1).equals(list2.get(y))) { lines.add(new LineItem(Type.REMOVED, list1.get(x++))); }
            else { lines.add(new LineItem(Type.ADDED, list2.get(y++))); }
        }
        if (x < list1.size()) lines.add(new LineItem(Type.REMOVED, list1.get(x)));
        else if (y < list2.size()) lines.add(new LineItem(Type.ADDED, list2.get(y)));
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
