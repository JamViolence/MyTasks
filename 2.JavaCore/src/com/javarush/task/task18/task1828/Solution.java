package com.javarush.task.task18.task1828;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* 
Прайсы 2
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) return;
        else {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();
            reader.close();
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            ArrayList<String> items = new ArrayList<>();
            fileReader.lines().forEach(items::add);
            fileReader.close();
            //индекс целевого объекта
            AtomicInteger num = new AtomicInteger(0);
            //id целевого объекта
            AtomicInteger targetNum = new AtomicInteger(Integer.parseInt(args[1]));
            items.forEach(a -> {
                int value = Integer.parseInt(a.substring(0, 8).trim());
                if (targetNum.get() == value) num.set(items.indexOf(a));
            });
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            if (args[0].equals("-u")) {
                if (args[1].length() > 8) args[1] = args[1].substring(0, 8);
                if (args[2].length() > 30) args[2] = args[2].substring(0, 30);
                if (args[3].length() > 8) args[2] = args[3].substring(0, 8);
                if (args[4].length() > 4) args[4] = args[4].substring(0, 4);
                String result = String.format("%-8s%-30s%-8s%-4s", args[1], args[2], args[3], args[4]);
                items.set(num.get(), result);
            } else if (args[0].equals("-d")) {
                items.remove(num.get());
            }
            items.forEach(a -> {
                try {
                    writer.write(a);
                    writer.newLine();
                } catch (IOException e) {}
            });
            writer.close();
        }
    }
}
