//package declaration
package ch.nolix.core.mathematics;

//own imports
import ch.nolix.core.helper.DoubleHelper;
import ch.nolix.core.validator2.Validator;

//class
/**
 * A polynom stores its coefficients internally in an array from the highest to the lowest coefficient.
 * -degree:	n
 * -array:	[a_n,... ,a_0]
 * 
 * @author Silvan Wyss
 * @month 2016-02
 * @lines 400
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
		
		//Checks if the given degree is not negative.
		Validator.suppose(degree).thatIsNamed("degree").isNotNegative();
		
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
		
		//Checks if the given dervice count is not negative.
		Validator.suppose(deriveCount).thatIsNamed("derive count").isNotNegative();
		
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
		
		//Checks if the given integration count is not negative.
		Validator.suppose(integrationCount).isNotNegative();
		
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
	 * @throws ZeroArgumentException if the given factor is 0.0.
	 */
	public Polynom multiplyCoefficientsWith(final double factor) {
		
		//Checks if the given factor is not 0.0.
		Validator.suppose(factor).thatIsNamed("factor").isNotZero();
		
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
	 * @throws ZeroArgumentException if the given value is 0.0.
	 */
	public Polynom setAllCoefficients(double value) {
		
		//Checks if the given value is not 0.0.
		Validator.suppose(value).isNotZero();
		
		for (int i = 0; i < coefficients.length; i++) {
			coefficients[i] = value;
		}
		
		return this;
	}
	
	//method
	/**
	 * Sets the coefficients of this polynom.
	 * 
	 * If less coefficients are given than the incremented degree of this polynom,
	 * the given coefficients are set as the highest coefficients of this polynom.
	 * 
	 * If more coefficients are given than the incremented degree of this polynom,
	 * this polynom is extended.
	 * 
	 * @param coefficients
	 * @return this polynom.
	 * @throws ZeroArgumentException if the given highest coefficient is 0.0.
	 */
	public Polynom setCoefficients(double... coefficients) {
		
		//Checks if the given highest coefficient is not 0.0.
		if (coefficients.length > 0) {
			Validator.suppose(coefficients[0]).thatIsNamed("highest coefficient").isNotZero();
		}
		
		if (coefficients.length <= getDegree() + 1) {
			reset();
		}
		else {
			this.coefficients = new double[coefficients.length];
		}
		
		for (int i = 0; i < coefficients.length; i++) {
			this.coefficients[i] = coefficients[i];
		}
		
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
		
		String string = "x->";
		
		boolean begin = true;
		
		//Extracts the coefficients of all x^n of this polynom for n>1.
		for (int i = 0; i < getDegree() - 1; i++) {
			
			int coefficientDegree = getDegree() - i;

			if (coefficients[i] != 0) {
				
				if (!begin && coefficients[i] > 0) {
					string += "+";
				}
				
				begin = false;
				
				if (coefficients[i] != 1) {
					string += DoubleHelper.toString(coefficients[i]) + "x^" + coefficientDegree;
				}
				else {
					string += "x^" + coefficientDegree;
				}
			}
		}
		
		//Extracts the coefficient of x of this polynom.
		if (coefficients.length > 1 && coefficients[coefficients.length - 2] != 0) {
			
			if (!begin && coefficients[coefficients.length - 2] > 0) {
				string += "+";
			}
			
			begin = false;
			string += DoubleHelper.toString(coefficients[coefficients.length - 2]) + "x";
		}
		
		//Extract of the constant of this polynom.
		if (coefficients.length > 0 && coefficients[coefficients.length - 1] != 0) {
			
			if (!begin && coefficients[coefficients.length - 1] > 0) {
				string += "+";
			}
			
			begin = false;
			string += DoubleHelper.toString(coefficients[coefficients.length - 1]);
		}
		
		if (begin) {
			string += "0";
		}
		
		return string;
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
