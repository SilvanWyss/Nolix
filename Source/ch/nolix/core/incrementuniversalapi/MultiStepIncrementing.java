//package declaration
package ch.nolix.core.incrementuniversalapi;

//own imports
import ch.nolix.core.container.LinkedList;

//interface
public interface MultiStepIncrementing<V> extends Incrementing<V> {
	
	//method declaration
	V getIncrementOrMax(int incrementCount);
	
	//method declaration
	LinkedList<V> getIncrementsWithAndTo(V targetValue);
	
	//method
	default LinkedList<V> getValueAndIncrementsWithAndTo(final V targetValue) {
		
		final var increments = getIncrementsWithAndTo(targetValue);
		increments.addAtBegin(getValue());
		
		return increments;
	}
}
