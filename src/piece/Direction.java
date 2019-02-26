package piece;

import java.util.ArrayList;
import java.util.Iterator;

import space.Memory;
import space.Space;
import util.ReturnsNull;
import util.Vector2;

public class Direction {
	final Vector2 dirVec;
	ArrayList<Memory> memories;
	
	public Direction(Vector2 direction) {
		this.dirVec = direction;
		memories = new ArrayList<Memory>();
	}
	
	public Iterator<Memory> iterator() {
		return memories.iterator();
	}
	
	boolean contains(Memory memory) {
		return memories.contains(memory);
	}
	
	boolean contains(Space space) {
		return memories.contains(space);
	}
	
	boolean add(Memory memory) {
		return memories.add(memory);
	}
	
	@ReturnsNull
	Memory get(Memory memory) {
		Iterator<Memory> itr = iterator();
		
		while(itr.hasNext()) {
			Memory memoryToCheck = itr.next();
			if(memory.equals(memoryToCheck))
				return memoryToCheck;
		}
		
		return null;
	}
	
	@ReturnsNull
	Memory get(Space space) {
		Iterator<Memory> itr = iterator();
		
		while(itr.hasNext()) {
			Memory memoryToCheck = itr.next();
			if(memoryToCheck.refersTo(space))
				return memoryToCheck;
		}
		
		return null;
	}
	
	void clear() {
		memories.clear();
	}
}
