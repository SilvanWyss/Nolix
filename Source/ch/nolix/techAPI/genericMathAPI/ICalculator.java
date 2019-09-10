//package declaration
package ch.nolix.techAPI.genericMathAPI;

//Java import
import java.math.BigDecimal;

//interface
public interface ICalculator {
	
	//abstract method
	public abstract IAmountMediator from(BigDecimal amount);
	
	//abstract method
	public abstract BigDecimal getBinomialCoefficient(BigDecimal n, BigDecimal k);
}
