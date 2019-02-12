//package declaration
package ch.nolix.core.mathematics;

//class
public final class ComplexNumber {
	
	//attributes
	private final double realComponent;
	private final double imaginaryComponent;
	
	//constructor
	public ComplexNumber(final double realComponent, final double imaginaryComponent) {
		this.realComponent = realComponent;
		this.imaginaryComponent = imaginaryComponent;
	}
	
	//method
	public boolean equals(final Object object) {
		
		if (!(object instanceof ComplexNumber)) {
			return false;
		}
		
		final var complexNumber = (ComplexNumber)object;
		
		return (
			realComponent == complexNumber.realComponent
			&& imaginaryComponent == complexNumber.imaginaryComponent
		); 
	}
	
	//method
	public ComplexNumber getConjugate() {
		return new ComplexNumber(realComponent, -imaginaryComponent);
	}
	
	//method
	public ComplexNumber getDifference(final ComplexNumber complexNumber) {
		return 
		new ComplexNumber(
			realComponent - complexNumber.realComponent,
			imaginaryComponent - complexNumber.imaginaryComponent
		);
	}
	
	//method
	public ComplexNumber getDifference(final double number) {
		return new ComplexNumber(realComponent - number, imaginaryComponent);
	}
	
	//method
	public double getImaginaryComponent() {
		return imaginaryComponent;
	}
	
	//method
	public double getMagnitude() {
		return
		Math.sqrt(
			realComponent * realComponent
			+ imaginaryComponent * imaginaryComponent
		);
	}
	
	//method
	public ComplexNumber getProduct(final ComplexNumber complexNumber) {
		return
		new ComplexNumber(
			realComponent * complexNumber.realComponent
			- imaginaryComponent * complexNumber.imaginaryComponent,
			realComponent * complexNumber.imaginaryComponent
			+ imaginaryComponent * complexNumber.realComponent
		);
	}
	
	//method
	public ComplexNumber getProduct(final double number) {
		return new ComplexNumber(number * realComponent, number * imaginaryComponent);
	}
	
	//method
	public double getRealComponent() {
		return realComponent;
	}
	
	//method
	public ComplexNumber getSum(final ComplexNumber complexNumber) {
		return
		new ComplexNumber(
			realComponent + complexNumber.realComponent,
			imaginaryComponent + complexNumber.imaginaryComponent
		);
	}
	
	//method
	public ComplexNumber getSum(final double number) {
		return new ComplexNumber(realComponent + number, imaginaryComponent);
	}
	
	//method
	public boolean isPureComplex() {
		return (realComponent == 0.0);
	}
	
	//method
	public boolean isPureReal() {
		return (imaginaryComponent == 0.0);
	}
	
	//method
	public String toString() {
		return realComponent + " " + imaginaryComponent + 0x2148;
	}
}
