package com.company;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class SaveSettings {
    static File file = new File("TicTacToeWins.txt");

    public static void saveSettings(Color[] TTTColors, Color[] VGColors) {

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