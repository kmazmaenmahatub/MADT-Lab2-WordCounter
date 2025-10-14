package com.kmazmaenmahatub.wordcounterapp;

public class TextCounter {

    // Count sentences using Regular Expression
    public static int countSentences(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }
        // Split by sentence terminators (., !, ?) and any following whitespace
        String[] sentences = text.split("[.!?]+\\s*");
        int nonEmptyCount = 0;
        for (String sentence : sentences) {
            if (!sentence.trim().isEmpty()) {
                nonEmptyCount++;
            }
        }
        return nonEmptyCount;
    }

    // Count words using Regular Expression
    public static int countWords(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }
        // Split by spaces, commas, or dots
        String[] words = text.split("[\\s,.]+");
        int count = 0;
        for (String word : words) {
            if (!word.trim().isEmpty()) {
                count++;
            }
        }
        return count;
    }

    // Count all characters (including spaces and punctuation)
    public static int countChars(String text) {
        if (text == null) {
            return 0;
        }
        return text.length();
    }

    // Count numbers (sequences of digits)
    public static int countNumbers(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }
        int count = 0;
        boolean inNumber = false;

        for (char c : text.toCharArray()) {
            if (Character.isDigit(c)) {
                if (!inNumber) {
                    count++;
                    inNumber = true;
                }
            } else {
                inNumber = false;
            }
        }
        return count;
    }
}