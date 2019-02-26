package main;

import java.util.Scanner;

import piece.Move;
import piece.Pawn;
import piece.Piece;
import space.Board;
import space.BoardInterface;
import space.Memory;
import space.Space;
import util.Vector2;

public class Game {

	Player[] players;
	BoardInterface board;

	public Game() {

		// Initialize players and set their orientation (direction their pawns move)
		players = new Player[2];
		for (int i = 0; i < players.length; i++)
			players[i] = new Player(i, new Vector2(0, ((int) Math.pow(-1, i))));
		board = new Board();

		/*
		 * // Add row of pawns for player 1 for(int j = 0; j < 8; j++) { Piece piece =
		 * new Pawn(board, players[0]); board.addPiece(piece, j, 1); }
		 * 
		 * // Add row of pawns for player 2 for(int j = 0; j < 8; j++) { Piece piece =
		 * new Pawn(board, players[1]); board.addPiece(piece, j, 6); }
		 */

		moveTest();

	}

	public void moveTest() {

		Move move;
		Piece piece;
		int x = 0;
		int y;

		Piece blackPawn = board.addPiece(new Pawn(board, players[1]), 6, 2);
		Piece blackPawn2 = board.addPiece(new Pawn(board, players[1]), 4, 2);
		Piece whitePawn = board.addPiece(new Pawn(board, players[0]), 5, 1);

		board.evaluateBoard();

		Scanner in = new Scanner(System.in);

		while (x != -1) {

			System.out.println(board);
			System.out.println("Please select a piece! (x,y)");
			x = in.nextInt();
			y = in.nextInt();

			piece = board.getSpace(x, y).getPiece();

			System.out.println("Okay, so you want " + piece);

			Vector2 from = new Vector2(x, y);
			move = new Move(from);

			System.out.println("\nWhere do you want to move it? (x,y)");
			x = in.nextInt();
			y = in.nextInt();

			Vector2 to = new Vector2(x, y);
			move.setTo(to);

			Memory memory = piece.getMemory(board.getSpace(to));

			// Piece could move to spot and is not blocked somehow
			if (memory != null && piece.isValid(memory))
				board.move(move);
			else
				System.out.println("Invalid, try again!");
		}
		// NO MEMORY LEAKS YA DOOFUS
		in.close();
	}

	public void canMoveTest() {

		board.addPiece(new Pawn(board, players[0]), 4, 4);
		board.addPiece(new Pawn(board, players[1]), 3, 5);
		board.addPiece(new Pawn(board, players[1]), 5, 5);

		Space space = board.getSpace(4, 4);
		Space space2 = board.getSpace(3, 5);
		Space space3 = board.getSpace(5, 5);

		Piece blackPawn = space.getPiece();
		Piece whitePawn1 = space2.getPiece();
		Piece whitePawn2 = space3.getPiece();

		blackPawn.evaluatePosition(space);
		whitePawn1.evaluatePosition(space2);
		whitePawn2.evaluatePosition(space3);

		System.out.println(space + ": Black " + blackPawn + "\n");
		System.out.println(space2 + ": First white " + whitePawn1 + "\n");
		System.out.println(space3 + ": Second white " + whitePawn2 + "\n");
	}

}
