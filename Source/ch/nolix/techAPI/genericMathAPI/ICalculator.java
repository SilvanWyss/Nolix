//package declaration
package ch.nolix.techAPI.genericMathAPI;

//Java import
import java.math.BigDecimal;

//interface
public interface ICalculator {
	
	//method declaration
	public abstract IAmountMediator from(BigDecimal amount);
	
	//method declaration
	public abstract BigDecimal getBinomialCoefficient(BigDecimal n, BigDecimal k);
}
