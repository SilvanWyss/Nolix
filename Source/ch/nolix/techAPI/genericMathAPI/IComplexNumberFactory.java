//package declaration
package ch.nolix.techAPI.genericMathAPI;

//Java import
import java.math.BigDecimal;

//interface
public interface IComplexNumberFactory {
	
	//abstract method
	public abstract IComplexNumber create(BigDecimal realComponent, BigDecimal imaginaryComponent);
	
	//abstract method
	public abstract IComplexNumber create(
		BigDecimal realComponent,
		BigDecimal imaginaryComponent,
		int bigDecimalScale
	);
	
	//abstract method
	public abstract IComplexNumber create(double realComponent, double imaginaryComponent);
	
	//abstract method
	public abstract IComplexNumber create(double realComponent, double imaginaryComponent, int bigDecimalScale);
}
