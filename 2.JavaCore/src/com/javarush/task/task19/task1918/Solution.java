package com.javarush.task.task19.task1918;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Знакомство с тегами
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        BufferedReader reader1 = new BufferedReader(new FileReader(fileName));
        String html = "";
        while (reader1.ready()) {
            html += reader1.readLine();
        }
        reader1.close();
        //знаю, что можно было сделать через Jsoup но решил потренироваться в RegEx
        Pattern inPattern = Pattern.compile("<"+args[0]);
        Pattern outPattern = Pattern.compile("</"+args[0]+">");
        Matcher inMatcher = inPattern.matcher(html);
        Matcher outMatcher = outPattern.matcher(html);
        int tagCount = 0;
        ArrayList<String> matches = new ArrayList<>();
        while (inMatcher.find()) {
            tagCount++;
            int startIndex = inMatcher.start();
            int endIndex = 0;
            int currentIndex = startIndex;
            while (tagCount != 0) {
                boolean found = inMatcher.find(currentIndex+1);
                if (found) {
                    int nextOpen = inMatcher.start();
                    outMatcher.find(currentIndex+1);
                    int nextClosed = outMatcher.start();
                    if (nextOpen < nextClosed) {
                        tagCount++;
                        currentIndex = nextOpen;
                    } else {
                        tagCount--;
                        currentIndex = nextClosed;
                    }
                } else {
                    outMatcher.find(currentIndex+1);
                    int nextClosed = outMatcher.start();
                    tagCount--;
                    currentIndex = nextClosed;
                }
            }
            outMatcher.find(currentIndex);
            endIndex = outMatcher.end();
            matches.add(html.substring(startIndex,endIndex));
            inMatcher.find(startIndex);
        }
        matches.forEach(System.out::println);
    }
}
