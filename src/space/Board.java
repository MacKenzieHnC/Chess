package space;

import piece.Move;
import piece.Piece;
import util.Vector2;

public class Board implements BoardInterface {
	Space[][] board;

	public Board() {
		board = new Space[8][8];
		for (int x = 0; x < 8; x++)
			for (int y = 0; y < 8; y++)
				board[x][y] = new Space(new Vector2(x, y));
	}

	@Override
	public void evaluateBoard() {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				Piece piece = board[x][y].getPiece();
				
				if(piece != null)
					piece.evaluatePosition(board[x][y]);
			}
		}
	}

	@Override
	public Space getSpace(int x, int y) {
		return board[x][y];
	}

	@Override
	public Piece addPiece(Piece piece, int x, int y) {
		board[x][y].setPiece(piece);
		return piece;
	}

	@Override
	public Space getSpaceByPiece(Piece piece) {
		for (Space[] column : board)
			for (Space space : column)
				if (space.getPiece().equals(piece))
					return space;

		// Should never happen
		return null;
	}

	@Override
	public void move(Move move) {
		Piece piece = getSpace(move.from()).getPiece();

		Space to = getSpace(move.to());
		to.setPiece(piece);
		getSpace(move.from()).setPiece(null);
		
		piece.evaluatePosition(to);

	}

	@Override
	public String toString() {
		String str = "BOARD";
		for (Space[] column : board)
			for (Space space : column)
				if (space.getPiece() != null)
					str += "\n" + space + ":\t" + space.getPiece() + "\n";

		return str;

	}
}
