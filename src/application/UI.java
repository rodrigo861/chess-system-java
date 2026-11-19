package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

    // Cores ANSI
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_WHITE = "\u001B[97m";
    private static final String ANSI_YELLOW = "\u001B[93m";

    public static void printBoard(ChessPiece[][] pieces) {

        for (int i = 0; i < pieces.length; i++) {

            System.out.print((8 - i) + " ");

            for (int j = 0; j < pieces[i].length; j++) {

                printPiece(pieces[i][j]);
            }

            System.out.println();
        }

        printColumns();
    }

    public static void printBoard(
            ChessPiece[][] pieces,
            boolean[][] possibleMoves) {

        for (int i = 0; i < pieces.length; i++) {

            System.out.print((8 - i) + " ");

            for (int j = 0; j < pieces[i].length; j++) {

                if (possibleMoves[i][j]) {
                    System.out.print("* ");
                } else {
                    printPiece(pieces[i][j]);
                }
            }

            System.out.println();
        }

        printColumns();
    }

    private static void printPiece(ChessPiece piece) {

        if (piece == null) {
            System.out.print("- ");
        } else {

            if (piece.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET + " ");
            } else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET + " ");
            }
        }
    }

    private static void printColumns() {

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