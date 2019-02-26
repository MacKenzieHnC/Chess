package piece;

import main.Player;
import space.BoardInterface;
import space.Memory;
import space.Space;
import util.Vector2;

public class Pawn extends Piece {

	public Pawn(BoardInterface board, Player player) {
		super(board, player);
		moveList = new Direction[1];
		moveList[0] = new Direction(player.getPawnDirection());
	}

	@Override
	protected void evaluateDirection(Space space, Direction dir) {

		Vector2 direction = dir.dirVec;
		Vector2 position = space.getPos().add(direction);

		Space spaceToCheck = board.getSpace(position);
		Memory memoryToAdd = new Memory(spaceToCheck, false, true, false, null);
		dir.add(memoryToAdd);

		// Check double space
		// If it hasn't moved, and the space before it is free, it's valid
		if (!hasMoved) {
			position = space.getPos().add(direction.mult(2));
			if (position.isValid()) {
				spaceToCheck = board.getSpace(position);
				Memory forwardMove = moveList[0].iterator().next();
				memoryToAdd = new Memory(spaceToCheck, false, true, !isValid(forwardMove), forwardMove);
				dir.add(memoryToAdd);
			}
		}

	}

	@Override
	protected void updateMemory(Memory memory) {

		// if listed as blocked, check still blocked
		memory.checkStillBlocked();

	}

	@Override
	protected void evaluateSpecialMoves(Space space) {
		// AVAST: THERE BE HACKY CODE HERE

		Vector2 direction = moveList[0].dirVec;
		Vector2 position;
		Space spaceToCheck;
		Memory memoryToAdd;

		// Check left attack
		position = space.getPos().add(direction).add(new Vector2(-1,0));
		if(position.isValid()) {
		spaceToCheck = board.getSpace(position);
		memoryToAdd = new Memory(spaceToCheck, true, false, false, null);
		specialMoveList.add(memoryToAdd);
		}
		// Check right attack
		position = space.getPos().add(direction).add(new Vector2(1,0));
		if(position.isValid()) {
		spaceToCheck = board.getSpace(position);
		memoryToAdd = new Memory(spaceToCheck, true, false, false, null);
		specialMoveList.add(memoryToAdd);
		}
	}

	@Override
	public String toString() {
		String str = "";
		if (player.getID() == 0)
			str += "White ";
		else
			str += "Black ";
		str += "Pawn";
		str += "\nValid Moves: ";
		for (Space space : getValidSpaces()) {
			str += " / " + space;
		}

		return str;
	}
}
