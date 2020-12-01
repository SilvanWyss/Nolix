//package declaration
package ch.nolix.techapi.dynamicmathapi;

//Java import
import java.math.BigDecimal;

//interface
public interface ICalculator {
	
	//method declaration
	IAmountMediator from(BigDecimal amount);
	
	//method declaration
	BigDecimal getBinomialCoefficient(BigDecimal n, BigDecimal k);
}
