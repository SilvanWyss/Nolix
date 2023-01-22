//package declaration
package ch.nolix.business.bigdecimalmath;

//Java imports
import java.math.BigDecimal;

import ch.nolix.businessapi.mathapi.bigdecimalmathapi.IComplexNumberFactory;

//class
public final class ComplexNumberFactory implements IComplexNumberFactory {
	
	//method
	@Override
	public ComplexNumber createComplexNumber(final BigDecimal realComponent, final BigDecimal imaginaryComponent) {
		return new ComplexNumber(realComponent, imaginaryComponent);
	}
	
	//method
	@Override
	public ComplexNumber createComplexNumber(
		final BigDecimal realComponent,
		final BigDecimal imaginaryComponent,
		final int bigDecimalScale
	) {
		return new ComplexNumber(realComponent, imaginaryComponent, bigDecimalScale);
	}
	
	//method
	@Override
	public ComplexNumber createComplexNumber(final double realComponent, final double imaginaryComponent) {
		return new ComplexNumber(realComponent, imaginaryComponent);
	}
	
	//method
	@Override
	public ComplexNumber createComplexNumber(
		final double realComponent,
		final double imaginaryComponent,
		final int bigDecimalScale
	) {
		return new ComplexNumber(realComponent, imaginaryComponent, bigDecimalScale);
	}
}
