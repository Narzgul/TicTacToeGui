package com.company.VierGewinnt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class VGGesamtWins {

    static File file = new File("VierGewinntWins.txt");

    public static void addWinns(int[] neueWins) throws IOException {
        int[] gesamteWins = new int[3];
        openReadFile(gesamteWins);
        for (int i = 0; i < gesamteWins.length; i++) {
            gesamteWins[i] = gesamteWins[i] + neueWins[i];
        }
        writeFile(gesamteWins);
    }

    public static int[] getGesamteWins() throws IOException {
        int[] gesamteWins = new int[3];
        openReadFile(gesamteWins);
        return gesamteWins;
    }

    private static void openReadFile(int[] gesamteWins) throws IOException {
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (IOException e) {
            file.createNewFile();
            scanner = new Scanner(file);
        }
        int i = 0;
        while (scanner.hasNext()) {
            gesamteWins[i] = scanner.nextInt();
            i++;
        }
    }

    private static void writeFile(int[] gesamteWins) throws IOException {
        OutputStream stream = new FileOutputStream(file);
        String output = gesamteWins[0] + " " + gesamteWins[1] + " " + gesamteWins[2];
        stream.write(output.getBytes());
        stream.close();
    }
}