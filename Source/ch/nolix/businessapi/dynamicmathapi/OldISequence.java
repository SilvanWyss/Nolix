//package declaration
package ch.nolix.businessapi.dynamicmathapi;

//Java imports
import java.math.BigDecimal;

//own imports
import ch.nolix.core.functionapi.IElementTakerElementGetter;

//interface
public interface OldISequence<N> {
	
	//method declaration
	int getIterationCountUntilValueMagnitudeExceedsMaxMagnitudeOrMinusOne(BigDecimal maxMagnitude, int maxIndex);
	
	//method declaration
	BigDecimal getSquaredMagnitude(int index);
	
	//method declaration
	IElementTakerElementGetter<N, BigDecimal> getSquaredMagnitudeFunction();
	
	//method declaration
	int getStartIndex();
	
	//method declaration
	N getValue(int index);
}
