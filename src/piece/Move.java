package piece;

import util.Vector2;

public class Move {
	private Vector2 from;
	private Vector2 to;
	
	public Move(Vector2 from, Vector2 to) {
		this.from = from;
		this.to = to;
	}
	
	public Move(Vector2 from) {
		this.from = from;
	}
	
	public void setTo(Vector2 to) {
		this.to = to;
	}
	
	public Vector2 to() {
		return to;
	}
	
	public Vector2 from() {
		return from;
	}
}
