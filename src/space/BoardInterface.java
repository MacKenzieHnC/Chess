package space;

import piece.Move;
import piece.Piece;
import util.Vector2;

public interface BoardInterface {

	public Space getSpace(int x, int y);
	
	public default Space getSpace(Vector2 vec) {
		return getSpace(vec.get(0), vec.get(1));
	}
	
	public Space getSpaceByPiece(Piece piece);
	public Piece addPiece(Piece piece, int x, int y);
	public void move(Move move);
	public void evaluateBoard();
}
