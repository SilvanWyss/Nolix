//package declaration
package ch.nolix.core.incrementuniversalapi;

//interface
public interface Incrementing<V> {
	
	//method declaration
	V getIncrementOrMax();
	
	//method declaration
	V getValue();
}
