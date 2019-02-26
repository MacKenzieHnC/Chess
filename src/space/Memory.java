package space;

public class Memory {

	Space space;
	private boolean couldMoveTo;
	private boolean isBlocked;
	private Memory isBlockedBy;
	private final boolean couldAttack;

	public Memory(Space space, boolean couldAttack) {
		this.space = space;
		this.couldAttack = couldAttack;
		this.couldMoveTo = false;
		this.isBlocked = false;
		this.isBlockedBy = null;
	}

	public Memory(Space space, boolean couldAttack, boolean couldMoveTo, boolean isBlocked, Memory isBlockedBy) {
		this.space = space;
		this.couldAttack = couldAttack;
		this.couldMoveTo = couldMoveTo;
		this.isBlocked = isBlocked;
		this.isBlockedBy = isBlockedBy;
	}

	//////////////////////////////////////////////////
	//
	// Methods
	//
	//////////////////////////////////////////////////
	public boolean checkStillBlocked() {
		if (isBlocked()) {
			Memory blockingMemory = getIsBlockedBy();

			// if blocking space now empty, check if it's blocked too
			if (blockingMemory.getSpace().getPiece() == null) {
				setBlocked(blockingMemory.checkStillBlocked());
				setIsBlockedBy(blockingMemory.getIsBlockedBy());
			}
		}

		return isBlocked();
	}
	
	public boolean refersTo(Space space) {
		return getSpace().equals(space);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Memory)
			return ((Memory) obj).getSpace().equals(space);
		return false;
	}

	//////////////////////////////////////////////////
	//
	// GETTERS AND SETTERS
	//
	//////////////////////////////////////////////////
	public boolean couldAttack() {
		return couldAttack;
	}
	
	public boolean couldMoveTo() {
		return couldMoveTo;
	}

	public void setCouldMoveTo(boolean couldMoveTo) {
		this.couldMoveTo = couldMoveTo;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public Memory getIsBlockedBy() {
		return isBlockedBy;
	}

	public void setIsBlockedBy(Memory isBlockedBy) {
		this.isBlockedBy = isBlockedBy;
	}

	public Space getSpace() {
		return space;
	}

}
