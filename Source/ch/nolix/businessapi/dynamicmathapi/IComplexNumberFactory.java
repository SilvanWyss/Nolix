//package declaration
package ch.nolix.businessapi.dynamicmathapi;

//Java imports
import java.math.BigDecimal;

//interface
public interface IComplexNumberFactory {
	
	//method declaration
	IComplexNumber create(BigDecimal realComponent, BigDecimal imaginaryComponent);
	
	//method declaration
	IComplexNumber create(
		BigDecimal realComponent,
		BigDecimal imaginaryComponent,
		int bigDecimalScale
	);
	
	//method declaration
	IComplexNumber create(double realComponent, double imaginaryComponent);
	
	//method declaration
	IComplexNumber create(double realComponent, double imaginaryComponent, int bigDecimalScale);
}
