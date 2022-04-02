//package declaration
package ch.nolix.businessapi.dynamicmathapi;

//Java imports
import java.math.BigDecimal;

//interface
public interface ICalculator {
	
	//method declaration
	BigDecimal getBinomialCoefficient(BigDecimal n, BigDecimal k);
}
