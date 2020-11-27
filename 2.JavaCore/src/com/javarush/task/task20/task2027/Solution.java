package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Кроссворд
*/

public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        System.out.println(detectAllWords(crossword, "home", "same"));

        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> wordList = new ArrayList<>();
        for (String w: words) {
            char c1 = w.charAt(0);
            for (int y = 0; y < crossword.length; y++) {
                for (int x = 0; x < crossword[y].length; x++) {
                    if (c1 == crossword[y][x]) {
                        if (w.length() == 1) {
                            Word wd = new Word(w);
                            wd.setStartPoint(x, y);
                            wd.setEndPoint(x, y);
                            wordList.add(wd);
                        } else {
                            ArrayList<Integer[]> vectors = findVectors(crossword, x, y, w);
                            for (Integer[] vector: vectors) {
                                int[] endPoint = search(crossword, x, y, w, vector, 1);
                                if (endPoint[0] >= 0) {
                                    Word wd = new Word(w);
                                    wd.setStartPoint(x, y);
                                    wd.setEndPoint(endPoint[0], endPoint[1]);
                                    wordList.add(wd);
                                }
                            }
                        }
                    }
                }
            }
        }
        return wordList;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }



    }

    public static ArrayList<Integer[]> findVectors(int[][] cross, int x, int y, String word) {
        ArrayList<Integer[]> vectors = new ArrayList<>();
        char c2 = word.charAt(1);
        if (x < cross[0].length - 1 && cross[y][x+1] == c2) vectors.add(new Integer[] {1, 0});
        if (x < cross[0].length - 1 && y < cross.length - 1 && cross[y+1][x+1] == c2) vectors.add(new Integer[] {1, 1});
        if (y < cross.length - 1 && cross[y+1][x] == c2) vectors.add(new Integer[] {0, 1});
        if (x > 0 && y < cross.length - 1 && cross[y+1][x-1] == c2) vectors.add(new Integer[] {-1, 1});
        if (x > 0 && cross[y][x-1] == c2) vectors.add(new Integer[] {-1, 0});
        if (x > 0 && y > 0 && cross[y-1][x-1] == c2) vectors.add(new Integer[] {-1, -1});
        if (y > 0 && cross[y-1][x] == c2) vectors.add(new Integer[] {0, -1});
        if (x < cross[0].length - 1 && y > 0 && cross[y-1][x+1] == c2) vectors.add(new Integer[] {1, -1});
        return vectors;
    }

    public static int[] search(int[][] cross, int x, int y, String word, Integer[] vector, int currentChar) {
        int[] endPoint = {-1, -1};
        int x1 = x + vector[0];
        int y1 = y + vector[1];
        char c = word.charAt(currentChar);
        if (cross[y1][x1] == c) {
            if (word.length() == currentChar + 1) {
                endPoint[0] = x1;
                endPoint[1] = y1;
            } else {
                if (x1 + vector[0] < cross[0].length && y1 + vector[1] < cross.length && x1 + vector[0] >= 0 && y1 + vector[1] >= 0) {
                    endPoint = search(cross, x1, y1, word, vector, ++currentChar);
                }
            }
        }
        return endPoint;
    }
}
