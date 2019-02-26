package util;

public class Vector2 {
	
	private int[] vector;
	public final int length; 
	
	public Vector2(int[] v) {
		length = v.length;
		this.vector = new int[length];
		for(int i = 0; i < length; i++)
			this.vector[i] = v[i];
	}
	
	public Vector2(int x, int y) {
		this.vector = new int[] {x,y};
		length = vector.length;
	}
	
	public Vector2(Vector2 vec) {
		length = vec.length;
		vector = new int[length];
		for(int i = 0; i < length; i++)
			vector[i] = vec.get(i);
	}
	
	public Vector2 add(Vector2 vec) {
		int[] sum = new int[length];
		
		for(int i = 0; i < length; i++)
			sum[i] = vector[i] + vec.get(i);
		
		return new Vector2(sum);
	}
	
	public Vector2 mult(int m) {
		Vector2 result = new Vector2(vector);
		
		for(int i = 0; i < length; i++)
			result.set(i, result.get(i) * m);
		
		return result;
	}
	
	public int get(int i) {
		return vector[i];
	}
	
	public void set(int i, int m) {
		vector[i] = m;
	}
	
	public Vector2 copy() {
		return new Vector2(this);
	}
	
	public boolean isValid() {
		for(int m : vector)
			if(m < 0 || m > 7)
				return false;
		return true;
	}
	
	@Override
	public String toString() {
		String str = "(";
		str += vector[0];
		
		for(int i = 1; i < length; i++)
			str += ", " + vector[i];
		
		str += ")";
		return str;
	}
	
	@Override
	public boolean equals(Object obj) {
		Vector2 vec = (Vector2)obj;
		
		if(length != vec.length)
			return false;
		
		for(int i = 0; i < length; i++)
			if(vector[i] != vec.get(i))
				return false;
		
		return true;
	}
}
