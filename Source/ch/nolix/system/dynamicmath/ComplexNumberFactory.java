//package declaration
package ch.nolix.system.dynamicmath;

//Java imports
import java.math.BigDecimal;

//own imports
import ch.nolix.businessapi.dynamicmathapi.IComplexNumberFactory;

//class
public final class ComplexNumberFactory implements IComplexNumberFactory {
	
	//method
	@Override
	public ComplexNumber create(final BigDecimal realComponent, final BigDecimal imaginaryComponent) {
		return new ComplexNumber(realComponent, imaginaryComponent);
	}
	
	//method
	@Override
	public ComplexNumber create(
		final BigDecimal realComponent,
		final BigDecimal imaginaryComponent,
		final int bigDecimalScale
	) {
		return new ComplexNumber(realComponent, imaginaryComponent, bigDecimalScale);
	}
	
	//method
	@Override
	public ComplexNumber create(final double realComponent, final double imaginaryComponent) {
		return new ComplexNumber(realComponent, imaginaryComponent);
	}
	
	//method
	@Override
	public ComplexNumber create(
		final double realComponent,
		final double imaginaryComponent,
		final int bigDecimalScale
	) {
		return new ComplexNumber(realComponent, imaginaryComponent, bigDecimalScale);
	}
}
