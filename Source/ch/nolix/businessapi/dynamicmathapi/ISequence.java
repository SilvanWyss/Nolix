//package declaration
package ch.nolix.businessapi.dynamicmathapi;

//Java imports
import java.math.BigDecimal;

//interface
public interface ISequence<V> {
	
	//method declaration
	int getIterationCountUntilValueSquaredMagnitudeExceedsSquaredMaxMagnitudeOrMinusOne(
		BigDecimal maxMagnitude,
		int maxIndex
	);
	
	//method declaration
	BigDecimal getSquaredMagnitudeOfValueAt(int index);
	
	//method declaration
	V getValueAtIndex(int index);
}
