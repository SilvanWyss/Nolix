//package declaration
package ch.nolix.techAPI.genericMathAPI;

//Java import
import java.math.BigDecimal;

//interface
public interface IAmountMediator {
	
	//abstract method
	public abstract BigDecimal getCombinationCount(BigDecimal subAmount);
	
	//abstract method
	public abstract BigDecimal getCombinationCountWithoutPermutation(BigDecimal subAmount);
	
	//abstract method
	public abstract BigDecimal getCombinationCountWithoutPermutationOrRepetation(BigDecimal subAmount);
	
	//abstract method
	public abstract BigDecimal getCombinationCountWithoutRepetation(BigDecimal subAmount);
}
