package main;

import java.util.ArrayList;

import piece.Piece;
import util.Vector2;

public class Player {
	private ArrayList<Piece> pieces;
	private Vector2 pawnDirection;
	private int ID;

	public Player(int ID, Vector2 pawnDirection) {
		this.ID = ID;
		this.pieces = new ArrayList<Piece>();
		this.pawnDirection = pawnDirection;
	}

	public Player(ArrayList<Piece> pieces) {
		this.pieces = new ArrayList<Piece>();
		this.pieces.addAll(pieces);
	}

	public Vector2 getPawnDirection() {
		return pawnDirection.copy();
	}

	public ArrayList<Piece> getPieces() {
		return pieces;
	}

	public int getID() {
		return ID;
	}

	@Override
	public String toString() {
		String str = ":::::::::::::PLAYER:::::::::::::::::\n";

		for (Piece p : pieces)
			str += "\n\n" + p;

		return str;
	}
}
