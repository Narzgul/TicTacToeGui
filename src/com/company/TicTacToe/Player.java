package com.company.TicTacToe;

public class Player {
    public char zeichen;

    public Player(char zeichen) {
        this.zeichen = zeichen;
    }

    public boolean validateTurn(char[] board, int turn) {
        if (turn >= 0 && turn < board.length) {
            return board[turn] == ' ';
        }
        return false;
    }
}
