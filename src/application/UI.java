
package application;

import java.util.Scanner;
import java.util.InputMismatchException;

import chess.ChessPiece;
import chess.ChessPosition;

public class UI {

    public static void printBoard(ChessPiece[][] pieces) {

        final String ANSI_RESET = "\u001B[0m";

        final String ANSI_BLACK = "\u001B[30m";

        final String ANSI_WHITE = "\u001B[37m";

        final String ANSI_BLACK_BACKGROUND = "\u001B[40m";

        final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

        for (int i = 0; i < pieces.length; i++) {

            System.out.print((8 - i) + " ");

            for (int j = 0; j < pieces[i].length; j++) {

                String background;
                String foreground;

                if ((i + j) % 2 == 0) {
                    background = ANSI_WHITE_BACKGROUND;
                    foreground = ANSI_BLACK;
                } else {
                    background = ANSI_BLACK_BACKGROUND;
                    foreground = ANSI_WHITE;
                }

                printPiece(pieces[i][j], background, foreground, ANSI_RESET);
            }

            System.out.println();
        }

        System.out.println("  a b c d e f g h");
    }
    
    public static void clearScreen() {
        try {
            new ProcessBuilder("clear")
                .inheritIO()
                .start()
                .waitFor();
        } catch (Exception e) {
            System.out.println("Não foi possível limpar a tela.");
        }
    }

    private static void printPiece(
            ChessPiece piece,
            String background,
            String foreground,
            String reset) {

        if (piece == null) {
            System.out.print(background + "   " + reset);
        } else {
            System.out.print(background + foreground + " " + piece + " " + reset);
        }
    }

    public static ChessPosition readChessPosition(Scanner sc) {

        try {

            String s = sc.nextLine();

            char column = s.charAt(0);

            int row = Integer.parseInt(s.substring(1));

            return new ChessPosition(column, row);

        } catch (RuntimeException e) {

            throw new InputMismatchException(
                "Error reading chess position. Valid values are from a1 to h8"
            );
        }
    }
}
