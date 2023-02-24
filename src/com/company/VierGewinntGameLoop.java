package com.company;

import com.company.TicTacToe.Player;
import com.company.VierGewinnt.VGGesamtWins;
import com.company.VierGewinnt.VierGewinntGui;

import java.io.IOException;
import java.util.Arrays;

public class VierGewinntGameLoop {
    public static char[] board = new char[42]; //7*6 Felder
    public static int turn = 0;
    public static int turnField = -1;
    public static int[] gewinne = new int[3];

    public VierGewinntGameLoop() throws IOException, InterruptedException {
        VierGewinntGui gui = new VierGewinntGui();
        VGGesamtWins.addWinns(gewinne);
        while (true) {
            char winner;
            turnField = -1;

            Player[] players = new Player[2];
            players[0] = new Player('X');
            players[1] = new Player('O');

            Arrays.fill(board, ' '); //Initialisiert das ganze Array mit einem Leerzeichen

            gui.drawBoard(board);

            do {
                if (players[turn].validateTurn(board, turnField)) {
                    board[turnField] = players[turn].zeichen;
                    turn = (turn + 1) % 2;
                }

                Thread.sleep(150);

                gui.drawBoard(board);
                winner = checkWin(board);
            } while (winner == ' ' && Main.checkNoTie(board));
            if (winner == 'X') gewinne[0]++;
            else if (winner == 'O') gewinne[1]++;
            else gewinne[2]++;
            VGGesamtWins.addWinns(gewinne);
            gui.sendWinnerMessage(winner);
        }
    }

    public static char checkWin(char[] board) {
        char winner = ' ';
        for (int i = 0; i < 42; i += 7) { //+7 für nächste Zeile
            for (int j = 0; j < 4; j++) { //+1 für nächste Spalte
                if (board[i+j] == board[i+j+1] && board[i+j] == board[i+j+2] && board[i+j] == board[i+j+3] && board[i+j] != ' ') { //Horizontal
                    winner = board[i+j];
                }
            }
        }
        for (int i = 0; i < 7; i++) { //+1 für nächste Spalte
            for (int j = 0; j < 21; j += 7) { //+7 für nächste Zeile
                if (board[i+j] == board[i+j+7] && board[i+j] == board[i+j+14] && board[i+j] == board[i+j+21] && board[i+j] != ' ') { //Vertikal
                    winner = board[i+j];
                }
            }
        }
        for (int i = 0; i < 4; i++) { //+1 für nächste Spalte
            for (int j = 0; j < 21; j += 7) { //+7 = 1 nach unten
                if (board[i+j] == board[i+j+8] && board[i+j] == board[i+j+16] && board[i+j] == board[i+j+24] && board[i+j] != ' ') { //Diagonal von l.o. nach r.u.
                    winner = board[i+j];
                }
            }
        }
        for (int i = 0; i < 4; i++) { //+1 für nächste Spalte
            for (int j = 35; j > 20; j -= 7) { //-7 = 1 nach oben
                if (board[i+j] == board[i+j-6] && board[i+j] == board[i+j-12] && board[i+j] == board[i+j-18] && board[i+j] != ' ') { //Diagonal von l.o. nach r.u.
                    winner = board[i+j];
                }
            }
        }
        return winner;
    }

    public static void eingabe(int fieldToSet) {
        while (fieldToSet + 7 < board.length && board[fieldToSet + 7] == ' ') {
            fieldToSet = fieldToSet + 7;
        }
        turnField = fieldToSet;
    }
}
