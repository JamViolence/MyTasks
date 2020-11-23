package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        while (reader.ready()) {
            String[] words = reader.readLine().split(" ");
            String name = "";
            for (int i = 0; i < words.length - 3; i++) {
                name += words[i];
                if (i < words.length - 4) name += " ";
            }
            String dateString = words[words.length-3] + " " + words[words.length-2] + " " + words[words.length-1];
            SimpleDateFormat formatter = new SimpleDateFormat("dd M y");
            Date date = formatter.parse(dateString);
            PEOPLE.add(new Person(name, date));
        }
        reader.close();
        PEOPLE.forEach(a -> {
            System.out.println(a.getName() + " " + a.getBirthDate());
        });
    }
}
