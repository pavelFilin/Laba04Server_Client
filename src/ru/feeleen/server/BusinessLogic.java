package ru.feeleen.server;

public class BusinessLogic {
    public static String reverse(String s) {
        if (s != null) {
            return new StringBuilder(s).reverse().toString() + '\n';
        } else {
            throw new NullPointerException();
        }
    }
}
