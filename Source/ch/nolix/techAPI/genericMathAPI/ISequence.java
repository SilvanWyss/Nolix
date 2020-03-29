//package declaration
package ch.nolix.techAPI.genericMathAPI;

//Java import
import java.math.BigDecimal;

//own imports
import ch.nolix.common.functionAPI.IElementTakerElementGetter;

//interface
public interface ISequence<N> {
	
	//method declaration
	public abstract int getConvergenceGrade(BigDecimal maxMagnitude, int maxIndex);
	
	//method declaration
	public abstract BigDecimal getSquaredMagnitude(int index);
	
	//method declaration
	public abstract IElementTakerElementGetter<N, BigDecimal> getSquaredMagnitudeFunction();
	
	//method declaration
	public abstract int getStartIndex();
	
	//method declaration
	public abstract N getValue(int index);
}
