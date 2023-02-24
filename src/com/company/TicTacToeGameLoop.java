package com.company;

import com.company.TicTacToe.GesamtWins;
import com.company.TicTacToe.Gui;
import com.company.TicTacToe.Player;

import java.io.IOException;
import java.util.Arrays;

public class TicTacToeGameLoop {
    public static char[] board = new char[9];
    public static int turn = 0;
    public static int turnField = -1;
    public static int[] gewinne = new int[3];

    public TicTacToeGameLoop() throws IOException, InterruptedException {
        Gui gui = new Gui();
        GesamtWins.addWinns(gewinne);
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
            GesamtWins.addWinns(gewinne);
            gui.sendWinnerMessage(winner);
        }
    }

    public static char checkWin(char[] board) {
        if (board[0] == board[1] && board[0] == board[2] && board[0] != ' ') { //1. Reihe
            return board[0];
        } else if (board[3] == board[4] && board[3] == board[5] && board[3] != ' ') { //2. Reihe
            return board[3];
        } else if (board[6] == board[7] && board[6] == board[8] && board[6] != ' ') { //3. Reihe
            return board[6];
        } else if (board[0] == board[4] && board[0] == board[8] && board[0] != ' ') { //diagonale l oben r unten
            return board[0];
        } else if (board[2] == board[4] && board[2] == board[6] && board[2] != ' ') { //diagonale r oben l unten
            return board[2];
        } else if (board[0] == board[3] && board[0] == board[6] && board[0] != ' ') { //1. Spalte
            return board[0];
        } else if (board[1] == board[4] && board[1] == board[7] && board[1] != ' ') { //2. Spalte
            return board[1];
        } else if (board[2] == board[5] && board[2] == board[8] && board[2] != ' ') { //3. Spalte
            return board[2];
        }
        return ' ';
    }
}
