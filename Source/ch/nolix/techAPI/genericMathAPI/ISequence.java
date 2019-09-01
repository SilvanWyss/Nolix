//package declaration
package ch.nolix.techAPI.genericMathAPI;

//Java import
import java.math.BigDecimal;

import ch.nolix.common.functionAPI.IElementTakerElementGetter;

//interface
public interface ISequence<N> {
	
	//abstract method
	public abstract int getConvergenceGrade(BigDecimal maxMagnitude, int maxIndex);
	
	//abstract method
	public abstract BigDecimal getSquaredMagnitude(int index);
	
	//abstract method
	public abstract IElementTakerElementGetter<N, BigDecimal> getSquaredMagnitudeFunction();
	
	//abstract method
	public abstract int getStartIndex();
	
	//abstract method
	public abstract N getValue(int index);
}
