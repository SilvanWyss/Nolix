//package declaration
package ch.nolix.common.math;

//Java import
import java.util.Arrays;

import ch.nolix.common.commonTypeHelper.DoubleHelper;
import ch.nolix.common.constants.MultiVariableNameCatalogue;
import ch.nolix.common.validator.Validator;

//class
/**
 * A polynom stores its coefficients internally in an array from the highest to the lowest coefficient.
 * -degree:	n
 * -array:	[a_n,... ,a_0]
 * 
 * @author Silvan Wyss
 * @month 2016-02
 * @lines 410
 */
public class Polynom {

	//attribute
	private double[] coefficients;
	
	//constructor
	/**
	 * Creates a new polynom with the given degree.
	 * The highest coefficient of the polynom will be 1.0, the resting coefficients will be 0.0.
	 * 
	 * @param degree
	 * @throws NegaviteArgumentException if the given degree is negative.
	 */
	public Polynom(final int degree) {
		
		//Asserts that the given degree is not negative.
		Validator.assertThat(degree).thatIsNamed("degree").isNotNegative();
		
		//Allocates the coefficients of this polynom.
		coefficients = new double[degree + 1];
		
		//Sets the highest coefficient of this polynom.
		coefficients[0] = 1.0;
	}
	
	//method
	/**
	 * Derives this polynom.
	 * 
	 * @return this polynom.
	 */
	public Polynom derive() {
		return derive(1);
	}
	
	//method
	/**
	 * Derives this polynom as many times the given derive count says.
	 * 
	 * @param deriveCount
	 * @return this polynom,
	 * @throws NegativeArgumentException if the given derive count is negative.
	 */
	public Polynom derive(final int deriveCount) {
		
		//Asserts that the given dervice count is not negative.
		Validator.assertThat(deriveCount).thatIsNamed("derive count").isNotNegative();
		
		for (int i = 1; i <= deriveCount; i++) {
			
			for (int j = coefficients.length - 1; j >= i; j--) {
				coefficients[j] = (coefficients.length - j) * coefficients[j - 1];
			}
			
			coefficients[i - 1] = 0.0;
		}
		
		removeLeadingZeroCoefficients();
		
		return this;
	}
	
	//method
	/**
	 * @param object
	 * @return true if this polynom equals the given object
	 */
	@Override
	public boolean equals(final Object object) {
		
		if (!(object instanceof Polynom)) {
			return false;
		}
		
		final Polynom polynom = (Polynom)object;
		
		if (polynom.getDegree() != getDegree()) {
			return false;
		}
		
		for (int i = 0; i < coefficients.length; i++) {
			if (polynom.coefficients[i] != coefficients[i]) {
				return false;
			}
		}
		
		return true;
	}
	
	//method
	/**
	 * @return a copy of this polynom.
	 */
	public Polynom getCopy() {
		Polynom polynom = new Polynom(getDegree());
		polynom.coefficients = coefficients.clone();
		return polynom;
	}
	
	//method
	/**
	 * @return the degree of this polynom.
	 */
	public int getDegree() {
		return (coefficients.length - 1);
	}
	
	//method
	/**
	 * @param x
	 * @return the slope of this polynom at the given x.
	 */
	public double getSlopeAt(final double x) {
		return getCopy().derive().getValueAt(x);
	}
	
	//method
	/**
	 * This method uses the Horner scheme to calculate the value.
	 * 
	 * @param x
	 * @return the value of this polynom at the given x.
	 */
	public double getValueAt(final double x) {
		
		//Handles the case that this polynom is a zero polynom.
		if (isZeroPolynom()) {
			return 0;
		}
		
		double value = coefficients[0];
		
		double base = 1;
		for (int i = 1; i < coefficients.length; i++) {
			base *= x;
			value += coefficients[i] * base;
		}
		
		return value;
	}
	
	//method
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	//method
	/**
	 * Integrates this polynom.
	 * 
	 * @return this polynom.
	 */
	public Polynom integrate() {
		return integrate(1);
	}
	
	//method
	/**
	 * Integrates this polynom as many times as the given integration count says.
	 * 
	 * @param integrationCount
	 * @return this polynom.
	 * @throws NegativeArgumentException if the given integration count is negative.
	 */
	public Polynom integrate(int integrationCount) {
		
		//Asserts that the given integration count is not negative.
		Validator.assertThat(integrationCount).isNotNegative();
		
		//Allocates new coefficients.
		final double[] coefficients = new double[getDegree() + 1 + integrationCount];
		
		for (int i = integrationCount; i < coefficients.length; i++) {
			coefficients[i] = this.coefficients[i - integrationCount];
		}
		
		for (int i = 1; i <= integrationCount; i++) {
			for (int j = i - 1; j < coefficients.length - 1; j++) {
				coefficients[j] = coefficients[j + 1] / (coefficients.length - j - 1);
			}
		}
		
		this.coefficients = coefficients;
		
		return this;
	}
	
	//method
	/**
	 * A polynom is a zero polynom it does not have a coefficients.
	 * 
	 * @return true if this polynom is a zero polynom.
	 */
	public boolean isZeroPolynom() {
		return (coefficients.length == 0);
	}
	
	//method
	/**
	 * Multiplies the coefficients of this polynom with the given factor.
	 * 
	 * @param factor
	 * @return this polynom.
	 * @throws ArgumentIsZeroException if the given factor is 0.0.
	 */
	public Polynom multiplyCoefficientsWith(final double factor) {
		
		//Asserts that the given factor is not 0.0.
		Validator.assertThat(factor).thatIsNamed("factor").isNotZero();
		
		for (int i = 0; i < coefficients.length; i++) {
			coefficients[i] *= factor;
		}
		
		return this;
	}
	
	//method
	/**
	 * Resets this polynom.
	 * 
	 * The degree of this polynom does not change.
	 * The highest coefficient of the polynom will be 1.0, the resting coefficients will be 0.0.
	 */
	public void reset() {
		
		//Allocates the coefficients of this polynom.
		coefficients = new double[getDegree() + 1];
		
		//Sets the highest coefficient of this polynom.
		coefficients[0] = 1.0;
	}
	
	//method
	/**
	 * Set the coefficients of this polynom to the given value.
	 * 
	 * @param value
	 * @return this polynom.
	 * @throws ArgumentIsZeroException if the given value is 0.0.
	 */
	public Polynom setAllCoefficients(double value) {
		
		//Asserts that the given value is not 0.0.
		Validator.assertThat(value).isNotZero();
		
		for (int i = 0; i < coefficients.length; i++) {
			coefficients[i] = value;
		}
		
		return this;
	}
	
	//method
	/**
	 * Sets the coefficients of the current {@link Polynom}.
	 * 
	 * If less coefficients are given than the incremented degree of the current {@link Polynom},
	 * the current {@link Polynom} will be decreased.
	 * 
	 * If more coefficients are given than the incremented degree of the current {@link Polynom},
	 * the current {@link Polynom} will be increased.
	 * 
	 * @param coefficients
	 * @return the current {@link Polynom}.
	 * @throws NullArgumentException if the given coefficients is null.
	 * @throws EmptyArgumentException if the given coefficients is empty.
	 * @throws ArgumentIsZeroException if the given highest coefficient is 0.0.
	 */
	public Polynom setCoefficients(double... coefficients) {
		
		//Asserts that the given coefficients is not null or empty.
		Validator.assertThat(coefficients).thatIsNamed(MultiVariableNameCatalogue.COEFFICIENTS).isNotEmpty();
		
		//Asserts that the given highest coefficient is not 0.0.
		Validator.assertThat(coefficients[0]).thatIsNamed("highest coefficient").isNotZero();
		
		//Sets the coefficients of the current Polynom.
		this.coefficients = Arrays.copyOf(coefficients, coefficients.length);
		
		return this;
	}
	
	//method
	/**
	 * @return a new array with the coefficients of this polynom.
	 */
	public double[] toArray() {
		return coefficients.clone();
	}
	
	//method
	/**
	 * @return a string representation of this polynom.
	 */
	@Override
	public String toString() {
		
		final var stringBuilder = new StringBuilder();
		
		stringBuilder.append("x->");
		
		boolean begin = true;
		
		//Extracts the coefficients of all x^n of this polynom for n>1.
		for (int i = 0; i < getDegree() - 1; i++) {
			
			int coefficientDegree = getDegree() - i;

			if (coefficients[i] != 0) {
				
				if (!begin && coefficients[i] > 0) {
					stringBuilder.append('+');
				}
				
				begin = false;
				
				if (coefficients[i] != 1) {
					stringBuilder.append(DoubleHelper.toString(coefficients[i]) + "x^" + coefficientDegree);
				}
				else {
					stringBuilder.append("x^" + coefficientDegree);
				}
			}
		}
		
		//Extracts the coefficient of x of this polynom.
		if (coefficients.length > 1 && coefficients[coefficients.length - 2] != 0) {
			
			if (!begin && coefficients[coefficients.length - 2] > 0) {
				stringBuilder.append('+');
			}
			
			begin = false;
			stringBuilder.append(DoubleHelper.toString(coefficients[coefficients.length - 2]) + "x");
		}
		
		//Extract of the constant of this polynom.
		if (coefficients.length > 0 && coefficients[coefficients.length - 1] != 0) {
			
			if (!begin && coefficients[coefficients.length - 1] > 0) {
				stringBuilder.append('+');
			}
			
			begin = false;
			stringBuilder.append(DoubleHelper.toString(coefficients[coefficients.length - 1]));
		}
		
		if (begin) {
			stringBuilder.append('0');
		}
		
		return stringBuilder.toString();
	}
	
	//method
	/**
	 * @return a new vector with the coefficients of this polynom.
	 */
	public Vector toVector() {
		return Vector.createVector(coefficients);
	}
	
	//method
	/**
	 * Removes all leading zero coefficients to restore this polynom.
	 * This method must be called internally when necessary!
	 */
	private void removeLeadingZeroCoefficients() {
		
		//Extracts the index of the highest coefficient that is not 0.0.
		int firstNonZeroCoefficientIndex = 0;
		for (int i = 0; i < coefficients.length; i++) {
			if (coefficients[i] != 0) {
				firstNonZeroCoefficientIndex = i;
				break;
			}
		}
		
		//Removes all leading coefficients that are 0.0.
		if (firstNonZeroCoefficientIndex > 0) {
			final double[] oldCoefficients = coefficients;
			coefficients = new double[oldCoefficients.length - firstNonZeroCoefficientIndex];
			for (int i = 0; i < coefficients.length; i++) {
				coefficients[i] = oldCoefficients[i + firstNonZeroCoefficientIndex];
			}
		}
	}
}
