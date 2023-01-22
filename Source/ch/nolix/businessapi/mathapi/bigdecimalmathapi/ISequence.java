//package declaration
package ch.nolix.businessapi.mathapi.bigdecimalmathapi;

//Java imports
import java.math.BigDecimal;

//interface
public interface ISequence<V> {
	
	//method declaration
	int getIterationCountUntilValueSquaredMagnitudeExceedsLimitOrMinusOne(BigDecimal limit, int maxIndex);
	
	//method declaration
	BigDecimal getSquaredMagnitudeOfValueAt(int index);
	
	//method declaration
	V getValueAtIndex(int index);
}
