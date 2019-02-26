package piece;

import java.util.ArrayList;
import java.util.Iterator;

import main.Player;
import space.BoardInterface;
import space.Memory;
import space.Space;
import util.ReturnsNull;

public abstract class Piece {

	final protected BoardInterface board;
	final protected Player player;
	protected Direction[] moveList;
	protected ArrayList<Memory> specialMoveList;
	protected boolean hasMoved;
	
	public Piece(BoardInterface board2, Player player) {
		this.board = board2;
		this.player = player;
		hasMoved = false;
		specialMoveList = new ArrayList<Memory>();
	}
	
	protected abstract void evaluateDirection(Space space, Direction dir);
	protected abstract void evaluateSpecialMoves(Space space);
	protected abstract void updateMemory(Memory memory);
	
	public void evaluatePosition(Space space) {
		for(Direction dir : moveList) {
			dir.clear();
			evaluateDirection(space, dir);
		}
		specialMoveList.clear();
		evaluateSpecialMoves(space);
	}

	// if ((enemy and canattack) || (empty and canmove)) && !isBlocked => good to go!
	public boolean isValid(Memory memory) {
		Piece piece = memory.getSpace().getPiece();

		if (!memory.isBlocked()) {
			if (piece == null) {
				if (memory.couldMoveTo())
					return true;
			} else if (isEnemy(piece)) {
				if(memory.couldAttack())
					return true;
			}
		}
		
		return false;

	}
	
	public boolean isEnemy(Piece piece) {
		if(player.equals(piece.player))
			return false;
		return true;
	}
	
	public ArrayList<Space> getValidSpaces() {
		ArrayList<Space> validSpaces = new ArrayList<Space>();
		
		// Get valid normal moves
		for(Direction dir : moveList) {
			Iterator<Memory> itr = dir.iterator();
			while(itr.hasNext()) {
				Memory memory = itr.next();
				if(isValid(memory))
					validSpaces.add(memory.getSpace());
			}
		}
		
		// Get valid special moves
		for(Memory memory : specialMoveList) {
			if(isValid(memory))
				validSpaces.add(memory.getSpace());
		}
		
		return validSpaces;
	}
	
	@ReturnsNull
	public Memory getMemory(Space space) {
		
		for(Memory m : specialMoveList) {
			if(m.refersTo(space))
				return m;
		}
		
		Memory memory = null;
		for(Direction dir : moveList) {
			memory = dir.get(space);
			if(memory != null)
				return memory;
		}
		
		return null;
	}
	
	
}
