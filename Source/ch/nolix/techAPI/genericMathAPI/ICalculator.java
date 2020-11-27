//package declaration
package ch.nolix.techAPI.genericMathAPI;

//Java import
import java.math.BigDecimal;

//interface
public interface ICalculator {
	
	//method declaration
	IAmountMediator from(BigDecimal amount);
	
	//method declaration
	BigDecimal getBinomialCoefficient(BigDecimal n, BigDecimal k);
}
