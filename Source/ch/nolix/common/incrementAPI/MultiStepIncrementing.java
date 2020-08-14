//package declaration
package ch.nolix.common.incrementAPI;

//own import
import ch.nolix.common.container.LinkedList;

//interface
public interface MultiStepIncrementing<V> extends Incrementing<V> {
	
	//method declaration
	public abstract V getIncrementOrMax(int incrementCount);
	
	//method declaration
	public abstract LinkedList<V> getIncrementsWithAndTo(V targetValue);
	
	//method
	public default LinkedList<V> getValueAndIncrementsWithAndTo(final V targetValue) {
		
		final var increments = getIncrementsWithAndTo(targetValue);
		increments.addAtBegin(getValue());
		
		return increments;
	}
}
