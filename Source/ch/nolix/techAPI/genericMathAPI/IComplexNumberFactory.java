//package declaration
package ch.nolix.techAPI.genericMathAPI;

//Java import
import java.math.BigDecimal;

//interface
public interface IComplexNumberFactory {
	
	//method declaration
	public abstract IComplexNumber create(BigDecimal realComponent, BigDecimal imaginaryComponent);
	
	//method declaration
	public abstract IComplexNumber create(
		BigDecimal realComponent,
		BigDecimal imaginaryComponent,
		int bigDecimalScale
	);
	
	//method declaration
	public abstract IComplexNumber create(double realComponent, double imaginaryComponent);
	
	//method declaration
	public abstract IComplexNumber create(double realComponent, double imaginaryComponent, int bigDecimalScale);
}
