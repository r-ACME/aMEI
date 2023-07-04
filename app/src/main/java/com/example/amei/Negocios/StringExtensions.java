package com.example.amei.Negocios;

import java.util.regex.Pattern;

public class StringExtensions{
    public static String substituirQuebrasDeLinha(String texto) {
        return texto.replace("\n", "(paragrafo)");
    }

    public static String restaurarQuebrasDeLinha(String texto) {
        return texto.replace("(paragrafo)", "\n");
    }

    public static boolean isEmail(String text) {
        String regEx = "[A-Z0-9a-z._%+-]+@[A-Z0-9a-z.-]+\\.[A-Za-z]{2,10}";

        return Pattern.matches(regEx, text);
    }

    public static String removeNonNumericCharacters(String input) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                builder.append(c);
            }
        }

        return builder.toString();
    }
}
