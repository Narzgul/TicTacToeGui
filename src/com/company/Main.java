package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Hauptmenu hauptmenu = new Hauptmenu();
    }

    public static boolean checkNoTie(char[] board) {
        for (char c : board) {
            if (c == ' ') {
                return true;
            }
        }
        return false;
    }
}