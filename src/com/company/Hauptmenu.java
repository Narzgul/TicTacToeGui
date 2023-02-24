package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hauptmenu {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel(new GridLayout(3, 1));

    public Hauptmenu() {
        frame.setSize(250, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Programm stoppt beim schließen
        frame.add(panel);
        frame.setLocationRelativeTo(null); //In die Mitte des Bildschirms
        frame.setTitle("Hauptmenu");

        JLabel lMenu = new JLabel("Welches Spiel möchten Sie spielen?");
        panel.add(lMenu);

        String[] games = {"Tic Tac Toe", "Vier Gewinnt"};
        JButton[] buttons = new JButton[2];
        buttons[0] = new JButton(games[0]);
        buttons[1] = new JButton(games[1]);
        buttons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SwingWorker() {
                    @Override
                    protected Object doInBackground() throws Exception {
                        TicTacToeGameLoop ticTacToeGameLoop = new TicTacToeGameLoop();
                        return null;
                    }
                }.execute();
            }
        });
        buttons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SwingWorker() {
                    @Override
                    protected Object doInBackground() throws Exception {
                        VierGewinntGameLoop vierGewinntGameLoop = new VierGewinntGameLoop();
                        return null;
                    }
                }.execute();
            }
        });
        panel.add(buttons[0]);
        panel.add(buttons[1]);
        frame.setVisible(true);
    }
}
