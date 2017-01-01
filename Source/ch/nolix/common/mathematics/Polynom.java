/*
 * file:	Polynom.java
 * author:	Silvan Wyss
 * month:	2016-02
 * lines:	300
 */

//package declaration
package ch.nolix.common.mathematics;

import ch.nolix.common.helper.DoubleHelper;
//own import
import ch.nolix.common.util.Validator;

//class
public final class Polynom {

	//attribute
	private double[] coefficients;
	
	//constructor
	/**
	 * Creates new polynom with the given degree.
	 *  
	 * @param degree
	 * @throws Exception if the given degree is negativee
	 */
	public Polynom(int degree) {
		
		Validator.throwExceptionIfValueIsNegative("degree", degree);
		
		coefficients = new double[degree + 1];
		setAllCoefficients(1);
	}
	
	//method
	/**
	 * Derives this polynom.
	 * 
	 * @return this polynom
	 */
	public final Polynom derive() {
		return derive(1);
	}
	
	//method
	/**
	 * Derives this polynom as many times the given derives count says.
	 * 
	 * @param derivesCount
	 * @return this polynom
	 * @throws Exception if the given derives count is negative.
	 */
	public final Polynom derive(int derivesCount) {
		
		Validator.throwExceptionIfValueIsNegative("derives count", derivesCount);
		
		for (int c = 1; c <= derivesCount; c++) {
			
			for (int i = coefficients.length - 1; i >= c; i--) {
				coefficients[i] = (coefficients.length - i) * coefficients[i - 1];
			}
			
			coefficients[c - 1] = 0.0;
		}
				
		removeLeadingZeroCoefficients();
		
		return this;
	}
	
	//method
	/**
	 * @param object
	 * @return true if this polynom equals the given object
	 */
	public final boolean equals(Object object) {
		
		if (object == null) {
			return false;
		}
		
		if (!(object instanceof Polynom)) {
			return false;
		}
		
		Polynom polynom = (Polynom)object;
		
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
	 * @return a clone of this polynom
	 */
	public final Polynom getClone() {
		Polynom polynom = new Polynom(getDegree());
		polynom.coefficients = coefficients.clone();
		return polynom;
	}
	
	//method
	/**
	 * @return a vector with the coefficients of this polynom
	 */
	public final Vector getCoefficientsVector() {
		return new Vector(getDegree() + 1).setValues(coefficients);
	}
	
	//method
	/**
	 * @return the degree of this polynom
	 */
	public final int getDegree() {
		return (coefficients.length - 1);
	}
	
	//method
	/**
	 * @param x
	 * @return the slope of this polynom at x
	 */
	public final double getSlopeAt(double x) {
		return getClone().derive().getValueAt(x);
	}
	
	//method
	/**
	 * @param x
	 * @return the function value of the given argument
	 * 
	 * This implementation uses the Horner scheme to calculate the function value.
	 */
	public final double getValueAt(double x) {
		
		if (isZeroPolynom()) {
			return 0;
		}
		
		double functionValue = coefficients[0];
		
		double currentBase = 1;
		for (int i = 1; i < coefficients.length; i++) {
			currentBase *= x;
			functionValue += coefficients[i] * currentBase;
		}
		
		return functionValue;
	}
	
	//method
	/**
	 * Integrates this polynom.
	 * 
	 * @return this polynom
	 */
	public final Polynom integrate() {
		return integrate(1);
	}
	
	public final Polynom integrate(int integrationsCount) {
		
		Validator.throwExceptionIfValueIsNegative("number of integrations", integrationsCount); 
		
		double[] coefficients = new double[getDegree() + 1 + integrationsCount];
		
		for (int i = 0; i < this.coefficients.length; i++) {
			coefficients[i] = this.coefficients[i];
		}
		
		//TODO
		
		
		return this;
	}
	
	//method
	/**
	 * @return true if this polynom is a zero polynom
	 */
	public final boolean isZeroPolynom() {
		return (coefficients.length == 0);
	}
	
	//method
	/**
	 * Multiplies the coefficients of this polynom with the given factor.
	 * 
	 * @param factor
	 * @throws Exception if the given factor is 0
	 */
	public final void multiplyCoefficientsWith(double factor) {
		
		Validator.throwExceptionIfValueIsZero("factor", factor);
		
		for (int i = 0; i < coefficients.length; i++) {
			coefficients[i] *= factor;
		}
	}
	
	
	//method
	/**
	 * Sets the coefficients of this polynom.
	 * 
	 * @param coefficients
	 * @return this polynom
	 * @throws Exception if not as many coefficients are given as the number of coefficients of this polynom
	 */
	public final Polynom setCoefficients(double... coefficients) {
		
		Validator.throwExceptionIfValueIsNotEqual("number of coefficients", getDegree() + 1, coefficients.length);
		
		if (!isZeroPolynom()) {
			Validator.throwExceptionIfValueIsZero("highest coefficient", coefficients[0]);
		}
		
		for (int i = 0; i < this.coefficients.length; i++) {
			this.coefficients[i] = coefficients[i];
		}
		
		return this;
	}
	
	//method
	/**
	 * Set the coefficients of this polynom to the given value.
	 * 
	 * @param value
	 * @return this polynom
	 * @throws Exception if the given value is zero
	 */
	public final Polynom setAllCoefficients(double value) {
		
		Validator.throwExceptionIfValueIsZero("value", value);
		
		for (int i = 0; i < coefficients.length; i++) {
			coefficients[i] = value;
		}
		
		return this;
	}
	
	//method
	/**
	 * @return an array with the coefficients of this polynom
	 */
	public final double[] toArray() {
		return coefficients.clone();
	}
	
	//method
	/**
	 * @return a string representation of this polynom
	 */
	public final String toString() {
		
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
	 * Removes all leading zero coefficients to restore this polynom.
	 * This method must be called internally when necessary!
	 */
	private void removeLeadingZeroCoefficients() {
		
		//Extracts index of the first coefficient that is not 0.
		int firstNonZeroCoefficientIndex = 0;
		for (int i = 0; i < coefficients.length; i++) {
			if (coefficients[i] != 0) {
				firstNonZeroCoefficientIndex = i;
				break;
			}
		}
		
		//Removes all first coefficients that are 0.
		if (firstNonZeroCoefficientIndex > 0) {	
			double[] oldCoefficients = coefficients;
			coefficients = new double[oldCoefficients.length - firstNonZeroCoefficientIndex];
			for (int i = 0; i < coefficients.length; i++) {
				coefficients[i] = oldCoefficients[i + firstNonZeroCoefficientIndex];
			}
		}
	}
}
