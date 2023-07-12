
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DoraeMon YT
 */
public class TicTacToe {
    static char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };
    
    static char player = 'x';    

    
    static class Move
    {
        int row, col;
    };
    
    // Displays the Tic Tac Toe board
    public void showBoard(char[][] board) {
        System.out.println();
        System.out.println(" " + board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println("----------");
        System.out.println(" " + board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println("----------");
        System.out.println(" " + board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
        System.out.println();
    }
    
    // Makes a move on the board
    public void makeMove(char[][] board, int move, char player) {
        switch (move) {
            case 1 -> board[0][0] = player;
            case 2 -> board[0][1] = player;
            case 3 -> board[0][2] = player;
            case 4 -> board[1][0] = player;
            case 5 -> board[1][1] = player;
            case 6 -> board[1][2] = player;
            case 7 -> board[2][0] = player;
            case 8 -> board[2][1] = player;
            case 9 -> board[2][2] = player;
        }
    }
    
    public int check() {
    for (int i = 0; i < 3; i++) {
        if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && 
                board[i][0] != ' ' && board[i][0] == 'x' || board[0][i] == board[1][i] && 
                board[0][i] == board[2][i] && board[0][i] != ' ' && board[0][i] == 'x') {
            return -1;
        }
        if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && 
                board[i][0] != ' ' && board[i][0] == 'o' || board[0][i] == board[1][i] && 
                board[0][i] == board[2][i] && board[0][i] != ' ' && board[0][i] == 'o') {
            return 1;
        }
    }
    if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && 
            board[0][0] != ' ' && board[0][0] == 'x' || board[0][2] == board[1][1] && 
            board[1][1] == board[2][0] && board[2][0] != ' ' && board[2][0] == 'x') {
        return -1;
    }
    if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && 
            board[0][0] != ' ' && board[0][0] == 'o' || board[0][2] == board[1][1] && 
            board[1][1] == board[2][0] && board[2][0] != ' ' && board[2][0] == 'o') {
        return 1;
    }
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (board[i][j] != 'x' && board[i][j] != 'o') {
                return 5;
            }
        }
    }
    return 0;
}

    int minmax(int d, boolean ismax, int alpha, int beta) {
    int chk = check();
    if (chk != 5) {
        return chk;
    }
    if (ismax) {
        int c = -2, s;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != 'x' && board[i][j] != 'o') {
                    board[i][j] = 'o';
                    s = minmax(d + 1, false, alpha, beta);
                    board[i][j] = ' ';
                    c = Math.max(c, s);
                    alpha = Math.max(alpha, c);
                    if (alpha >= beta) {
                        break;
                    }
                }
            }
        }
        return c;
    } else {
        int c = Integer.MAX_VALUE, s;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != 'x' && board[i][j] != 'o') {
                    board[i][j] = 'x';
                    s = minmax(d + 1, true, alpha, beta);
                    board[i][j] = ' ';
                    c = Math.min(c, s);
                    beta = Math.min(beta, c);
                    if (alpha >= beta) {
                        break;
                    }
                }
            }
        }
        return c;
    }
}
    public int convertTo1D(int x, int y) {
        return x * 3 + y;
    }

    public void bestmove() {
        int c = -2, r = 0, co = 0, s;
        int a = -2, b = 1000;

        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        System.out.println("computer's Turn: ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != 'x' && board[i][j] != 'o') {
                    board[i][j] = 'o';
                    s = minmax(0, false, a, b);
                    //undo move
                    board[i][j] = ' ';
                    if (s > c) {
                        c = s;
                        r = i;
                        co = j;
                    }
                    a = Math.max(a, c);
                    if (a >= b) {
                        break;
                    }
                }
            }
        }
        bestMove.row = r;
        bestMove.col = co;
        int bestMoveX = bestMove.row;         // Compute the best move x-coordinate
        int bestMoveY = bestMove.col;         // Compute the best move y-coordinate
        int bestmove = convertTo1D(bestMoveX, bestMoveY); // Convert to 1D index
        System.out.println("The Move of the Computer is: " + (bestmove+1));
        board[r][co] = 'o';
        player = 'x';
    }

    public static void main(String args[]){
        TicTacToe t = new TicTacToe();
        t.showBoard(board);
        int check = t.check();
        
        while(check ==  5){
            if(player == 'x'){
            System.out.print("Enter a Value: ");
            Scanner scanner = new Scanner(System.in);
            int move = scanner.nextInt();
            t.makeMove(board, move, player);
            t.showBoard(board);
            check = t.check();
            switch (check) {
                case 1 -> System.out.println("Computer  wins");
                case -1 -> System.out.println("You wins");
            }
//            if(tie==true){
//			break;
//		}
            //switching player
            player = 'o';
        }
            if(player == 'o'){
                t.bestmove();
                t.showBoard(board);
                
                check = t.check();
            switch (check) {
                case 1 -> System.out.println("Computer  wins");
                case -1 -> System.out.println("You wins");
            }
//            if(tie==true){
//			break;
//		}
//          switching player
            player = 'x';
            }
     }
        
//      
    }
}

