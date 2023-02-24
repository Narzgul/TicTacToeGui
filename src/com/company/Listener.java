package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener {
    public static void setListener(JButton[] fields, int fieldToSet, boolean TTT) {
        if (TTT) {
            fields[fieldToSet].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TicTacToeGameLoop.turnField = fieldToSet;
                }
            });
        }
        else {
            fields[fieldToSet].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    VierGewinntGameLoop.eingabe(fieldToSet);
                }
            });
        }

    }
}
