//package declaration
package ch.nolix.techAPI.genericMathAPI;

//Java import
import java.math.BigDecimal;

//interface
public interface IAmountMediator {
	
	//method declaration
	public abstract BigDecimal getCombinationCount(BigDecimal subAmount);
	
	//method declaration
	public abstract BigDecimal getCombinationCountWithoutPermutation(BigDecimal subAmount);
	
	//method declaration
	public abstract BigDecimal getCombinationCountWithoutPermutationOrRepetation(BigDecimal subAmount);
	
	//method declaration
	public abstract BigDecimal getCombinationCountWithoutRepetation(BigDecimal subAmount);
}
