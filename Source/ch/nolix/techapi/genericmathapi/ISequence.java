//package declaration
package ch.nolix.techapi.genericmathapi;

//Java import
import java.math.BigDecimal;

//own imports
import ch.nolix.common.functionAPI.IElementTakerElementGetter;

//interface
public interface ISequence<N> {
	
	//method declaration
	int getConvergenceGrade(BigDecimal maxMagnitude, int maxIndex);
	
	//method declaration
	BigDecimal getSquaredMagnitude(int index);
	
	//method declaration
	IElementTakerElementGetter<N, BigDecimal> getSquaredMagnitudeFunction();
	
	//method declaration
	int getStartIndex();
	
	//method declaration
	N getValue(int index);
}
