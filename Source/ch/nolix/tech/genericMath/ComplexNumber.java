//package declaration
package ch.nolix.tech.genericMath;

//Java imports
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.math.Calculator;
import ch.nolix.core.validator.Validator;
import ch.nolix.techAPI.genericMathAPI.IComplexNumber;

//class
public final class ComplexNumber implements IComplexNumber {
	
	//attributes
	private final BigDecimal realComponent;
	private final BigDecimal imaginaryComponent;
	
	//constructor
	public ComplexNumber(final BigDecimal realComponent, final BigDecimal imaginaryComponent) {
		
		Validator
		.suppose(realComponent)
		.thatIsNamed("real component")
		.isNotNull();
		
		Validator
		.suppose(imaginaryComponent)
		.thatIsNamed("imaginary component")
		.isNotNull();
		
		final var bigDecimalScale = Calculator.getMax(realComponent.scale(), imaginaryComponent.scale());
		
		this.realComponent = realComponent.setScale(bigDecimalScale, RoundingMode.HALF_UP);
		this.imaginaryComponent = imaginaryComponent.setScale(bigDecimalScale, RoundingMode.HALF_UP);
	}
	
	//constructor
	public ComplexNumber(
		final BigDecimal realComponent,
		final BigDecimal imaginaryComponent,
		final int bigDecimalScale
	) {
		
		Validator
		.suppose(realComponent)
		.thatIsNamed("real component")
		.isNotNull();
		
		Validator
		.suppose(imaginaryComponent)
		.thatIsNamed("imaginary component")
		.isNotNull();
		
		Validator
		.suppose(bigDecimalScale)
		.thatIsNamed("big decimal scale")
		.isPositive();
		
		this.realComponent = realComponent.setScale(bigDecimalScale, RoundingMode.HALF_UP);
		this.imaginaryComponent = imaginaryComponent.setScale(bigDecimalScale, RoundingMode.HALF_UP);
	}
	
	//constructor
	public ComplexNumber(final double realComponent, final double imaginaryComponent) {
		
		final var realComponentBigDecimal = new BigDecimal(realComponent);
		final var imaginaryComponentBigDecimal = new BigDecimal(imaginaryComponent);
		
		final var bigDecimalScale =
		Calculator.getMax(realComponentBigDecimal.scale(), imaginaryComponentBigDecimal.scale());
		
		this.realComponent = realComponentBigDecimal.setScale(bigDecimalScale);
		this.imaginaryComponent = imaginaryComponentBigDecimal.setScale(bigDecimalScale);
	}
	
	//constructor
	public ComplexNumber(
		final double realComponent,
		final double imaginaryComponent,
		final int bigDecimalScale
	) {
		
		Validator
		.suppose(bigDecimalScale)
		.thatIsNamed("big decimal scale")
		.isPositive();
		
		this.realComponent = new BigDecimal(realComponent).setScale(bigDecimalScale, RoundingMode.HALF_UP);
		this.imaginaryComponent = new BigDecimal(imaginaryComponent).setScale(bigDecimalScale, RoundingMode.HALF_UP);
	}
	
	//method
	@Override
	public boolean equals(final Object object) {
		
		if (!(object instanceof IComplexNumber)) {
			return false;
		}
		
		final var closedIntervall = (IComplexNumber)object;
		
		return (
			realComponent.equals(closedIntervall.getRealComponent())
			&& imaginaryComponent.equals(closedIntervall.getImaginaryComponent())
		);
	}
	
	//method
	@Override
	public int getBigDecimalScale() {
		return realComponent.scale();
	}
	
	//method
	@Override
	public ComplexNumber getConjugate() {
		return new ComplexNumber(realComponent, imaginaryComponent.negate(), getBigDecimalScale());
	}
	
	//method
	@Override
	public BigDecimal getImaginaryComponent() {
		return imaginaryComponent;
	}
	
	//method
	@Override
	public IComplexNumber getInBigDecimalScale(final int bigDecimalScale) {
		return new ComplexNumber(realComponent, imaginaryComponent, bigDecimalScale);
	}
	
	//method
	@Override
	public BigDecimal getMagnitude() {
		return
		realComponent
		.pow(2)
		.add(imaginaryComponent.pow(2))
		.sqrt(MathContext.DECIMAL128)
		.setScale(getScale(), RoundingMode.HALF_UP);
	}
	
	//method
	@Override
	public ComplexNumber getPower(final int exponent) {
		
		Validator
		.suppose(exponent)
		.thatIsNamed(VariableNameCatalogue.EXPONENT)
		.isPositive();
		
		var complexNumber = this;
		
		for (var i = 2; i <= exponent; i++) {
			complexNumber = complexNumber.getProduct(this);
		}
		
		return complexNumber;
	}
	
	//method
	@Override
	public ComplexNumber getProduct(IComplexNumber complexNumber) {
		return
		new ComplexNumber(
			realComponent
			.multiply(complexNumber.getRealComponent())
			.subtract(getImaginaryComponent().multiply(complexNumber.getImaginaryComponent()))
			.setScale(getScale(), RoundingMode.HALF_UP),
			realComponent
			.multiply(complexNumber.getImaginaryComponent())
			.add(getImaginaryComponent().multiply(complexNumber.getRealComponent()))
			.setScale(getScale(), RoundingMode.HALF_UP),
			getBigDecimalScale()
		);
	}
	
	//method
	@Override
	public BigDecimal getRealComponent() {
		return realComponent;
	}
	
	//method
	@Override
	public int getScale() {
		return realComponent.scale();
	}
	
	//method
	@Override
	public ComplexNumber getSquare() {
		return new ComplexNumber(
			realComponent
			.multiply(realComponent)
			.subtract(imaginaryComponent.multiply(imaginaryComponent)),
			new BigDecimal(2.0)
			.multiply(realComponent.multiply(imaginaryComponent)),
			getBigDecimalScale()
		);
	}
	
	//method
	@Override
	public BigDecimal getSquaredMagnitude() {
		return
		realComponent
		.multiply(realComponent)
		.add(imaginaryComponent.multiply(imaginaryComponent))
		.setScale(getScale(), RoundingMode.HALF_UP);
	}
	
	//method
	@Override
	public IComplexNumber getSum(final BigDecimal number) {
		return new ComplexNumber(
			realComponent.add(number),
			imaginaryComponent,
			getBigDecimalScale()
		);
	}
	
	//method
	@Override
	public ComplexNumber getSum(final IComplexNumber complexNumber) {
		return
		new ComplexNumber(
			realComponent
			.add(complexNumber.getRealComponent()),
			imaginaryComponent.add(complexNumber.getImaginaryComponent()),
			getBigDecimalScale()
		);
	}
	
	//method
	@Override
	public IComplexNumber getSum(final double number) {
		return getSum(new BigDecimal(number));
	}
	
	//method
	@Override
	public boolean isPureImaginary() {
		return (realComponent.compareTo(BigDecimal.ZERO) == 0);
	}
	
	//method
	@Override
	public boolean isPureReal() {
		return (imaginaryComponent.compareTo(BigDecimal.ZERO) == 0);
	}
	
	//method
	@Override
	public String toString() {
		
		if (isPureReal()) {
			return realComponent.toString();
		}
		
		if (isPureImaginary()) {
			return imaginaryComponent + "i";
		}
		
		return (realComponent + " + " + imaginaryComponent + "i");
	}
}
