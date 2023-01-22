//package declaration
package ch.nolix.businessapi.mathapi.bigdecimalmathapi;

//Java imports
import java.math.BigDecimal;

//interface
public interface IComplexNumberFactory {
	
	//method declaration
	IComplexNumber createComplexNumber(BigDecimal realComponent, BigDecimal imaginaryComponent);
	
	//method declaration
	IComplexNumber createComplexNumber(
		BigDecimal realComponent,
		BigDecimal imaginaryComponent,
		int bigDecimalScale
	);
	
	//method declaration
	IComplexNumber createComplexNumber(double realComponent, double imaginaryComponent);
	
	//method declaration
	IComplexNumber createComplexNumber(double realComponent, double imaginaryComponent, int bigDecimalScale);
}
