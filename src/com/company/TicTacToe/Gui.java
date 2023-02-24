package com.company.TicTacToe;

import com.company.Listener;
import com.company.TicTacToeGameLoop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Gui {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel(new GridLayout(3, 3));
    JButton[] fields = new JButton[9];
    Color[] buttonFarben = {Color.blue, Color.red, Color.white};

    public Gui() {
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Programm stoppt beim schließen
        frame.setVisible(true);
        frame.add(panel);
        frame.setLocationRelativeTo(null); //In die Mitte des Bildschirms
        frame.setTitle("Tic Tac Toe von Titou");

        setupMenubar();

        int z = 0;
        for (int i = 0; i < Math.sqrt(fields.length); i++) {
            for (int j = 0; j < Math.sqrt(fields.length); j++) {
                this.fields[z] = new JButton();
                this.panel.add(fields[z]);
                Listener.setListener(fields, z, true);
                z++;
            }
        }
    }

    public static Color getContrastColor(Color color) {
        double y = (299 * color.getRed() + 587 * color.getGreen() + 114 * color.getBlue()) / 1000.0;
        return y >= 128 ? Color.black : Color.white;
    }

    public void drawBoard(char[] board) {
        for (int i = 0; i < 9; i++) {
            fields[i].setText(Character.toString(board[i]));
            if (board[i] == 'X') {
                fields[i].setBackground(buttonFarben[0]);
                fields[i].setForeground(getContrastColor(buttonFarben[0]));
            } else if (board[i] == 'O') {
                fields[i].setBackground(buttonFarben[1]);
                fields[i].setForeground(getContrastColor(buttonFarben[1]));
            } else {
                fields[i].setBackground(buttonFarben[2]);
                fields[i].setForeground(getContrastColor(buttonFarben[2]));
            }
        }
    }

    public void sendWinnerMessage(char winner) {
        if (winner != ' ') {
            JOptionPane.showMessageDialog(panel, "Spieler " + winner + " hat gewonnern!",
                    "Gewonnen!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(panel, "Unentschieden!", "Unentschieden!",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        int input = JOptionPane.showConfirmDialog(panel, "Noch einmal Spielen?",
                "Again?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (input == 1) {
            showQuitDialog();
        }
    }

    private void setupMenubar() {
        JMenuBar menu = new JMenuBar();
        JMenu datei = new JMenu("Datei");
        JMenu farben = new JMenu("Farben");
        JMenuItem buttonFarbe = new JMenuItem("Farbe der Knöpfe");
        JMenuItem menuFarbe = new JMenuItem("Farbe des Menus");
        JMenuItem exit = new JMenuItem("Schließen");
        JMenuItem stats = new JMenuItem("Statistiken");

        frame.setJMenuBar(menu);
        menu.add(datei);
        datei.add(farben);
        datei.add(stats);
        datei.addSeparator();
        datei.add(exit);
        farben.add(buttonFarbe);
        farben.add(menuFarbe);

        buttonFarbe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonFarben[0] = JColorChooser.showDialog(panel, "Suche dir eine Farbe für X aus", Color.blue);
                buttonFarben[1] = JColorChooser.showDialog(panel, "Suche dir eine Farbe für O aus", Color.red);
                buttonFarben[2] = JColorChooser.showDialog(panel, "Suche dir eine Farbe für freie Felder aus", Color.white);
            }
        });
        menuFarbe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color c = JColorChooser.showDialog(panel, "Suche dir eine für das Menu Farbe aus", Color.white);
                menu.setBackground(c);
                menu.setForeground(getContrastColor(c));
            }
        });
        stats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] gesamteWins = new int[3];
                try {
                    gesamteWins = GesamtWins.getGesamteWins();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                JOptionPane.showMessageDialog(panel, "X hat " + TicTacToeGameLoop.gewinne[0] + " mal gewonnen\nO hat " +
                        TicTacToeGameLoop.gewinne[1] + " mal gewonnen\nUnd es gab " + TicTacToeGameLoop.gewinne[2] + " Unentschieden\n\n" +
                        "All Time Statistiken:\nX hat " + gesamteWins[0] + " mal gewonnen\nO hat " +
                        gesamteWins[1] + " mal gewonnen\nUnd es gab " +
                        gesamteWins[2] + " Unentschieden", "Statistiken", JOptionPane.INFORMATION_MESSAGE);

            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showQuitDialog();
            }
        });


    }

    public void showQuitDialog() {
        int input = JOptionPane.showConfirmDialog(panel,
                "Bist du dir sicher, dass du Tic Tac Toe beenden möchtest?",
                "Sicher?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (input == 0) {
            JOptionPane.showMessageDialog(panel, "X hat " + TicTacToeGameLoop.gewinne[0] + " mal gewonnen\nO hat " +
                            TicTacToeGameLoop.gewinne[1] + " mal gewonnen\nUnd es gab " + TicTacToeGameLoop.gewinne[2] + " Unentschieden",
                    "Chiao!", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
        }
    }
}