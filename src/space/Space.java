package space;

import piece.Piece;
import util.ReturnsNull;
import util.Vector2;

public class Space {

	//////////////////////////////////////////////
	// Fields
	//////////////////////////////////////////////
	final Vector2 pos;
	Piece piece;

	//////////////////////////////////////////////
	//
	// CONSTRUCTOR
	//
	//////////////////////////////////////////////
	public Space(Vector2 pos) {
		this.pos = pos;
		this.piece = null;
	}

	//////////////////////////////////////////////
	//
	// GETTERS/SETTERS
	//
	//////////////////////////////////////////////

	public Vector2 getPos() {
		return pos;
	}
	
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	@ReturnsNull
	public Piece getPiece() {
		return piece;
	}
	
	@Override
	public String toString() {
		return "Space " + pos;
	}
}
