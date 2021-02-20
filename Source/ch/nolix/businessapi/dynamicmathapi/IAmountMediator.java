//package declaration
package ch.nolix.businessapi.dynamicmathapi;

//Java import
import java.math.BigDecimal;

//interface
public interface IAmountMediator {
	
	//method declaration
	BigDecimal getCombinationCount(BigDecimal subAmount);
	
	//method declaration
	BigDecimal getCombinationCountWithoutPermutation(BigDecimal subAmount);
	
	//method declaration
	BigDecimal getCombinationCountWithoutPermutationOrRepetation(BigDecimal subAmount);
	
	//method declaration
	BigDecimal getCombinationCountWithoutRepetation(BigDecimal subAmount);
}
