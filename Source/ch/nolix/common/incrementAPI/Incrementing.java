//package declaration
package ch.nolix.common.incrementAPI;

//interface
public interface Incrementing<V> {
	
	//method declaration
	public abstract V getIncrementOrMax();
	
	//method declaration
	public abstract V getValue();
}
