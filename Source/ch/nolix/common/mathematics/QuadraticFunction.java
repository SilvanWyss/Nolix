/*
 * file:	QuadraticFunction.java
 * author:	Silvan Wyss
 * month:	2016-05
 * lines:	120
 */

//package declaration
package ch.nolix.common.mathematics;

//own import
import ch.nolix.common.util.Validator;

//class
public final class QuadraticFunction {

	//attributes
	private double a = 1.0;
	private double b = 0.0;
	private double c = 0.0;
	
	//method
	/**
	 * @return the discriminant of this quadratic function
	 */
	public final double getDiscriminant() {
		return (Math.pow(b, 2) - (4 * a * c));
	}
	
	//method
	/**
	 * @return the max of this quadratic function
	 * @throws Exception if this quadratic function has no max
	 */
	public final double getMax() {
		
		if (a > 0) {
			throw new RuntimeException("Quadraticfunction " + toString() + " has no max.");
		}
		
		return (-b / (2 * a));
	}
	
	//method
	/**
	 * @return the min of this quadratic function
	 * @throws Exception if this quadratic function has no min
	 */
	public final double getMin() {
		
		if (a < 0) {
			throw new RuntimeException("Quadraticfunction " + toString() + " has no min.");
		}
		
		return (-b / (2 * a));
	}
	
	//method
	/**
	 * @return the solutions of this quadratic function
	 */
	public double[] getSolutions() {
		
		double discriminant = getDiscriminant();
		
		if (discriminant < 0) {
			return new double[0];
		}
		
		if (discriminant == 0) {
			double[] solutions = new double[1];
			solutions[0] = -b / (2 * a);
		}
		
		double[] solutions = new double[2];
		double discriminantRoot = Math.sqrt(discriminant);
		solutions[0] = (-b - discriminantRoot) / (2 * a);
		solutions[1] = (-b + discriminantRoot) / (2 * a);
		return solutions;
	}
	
	//method
	/**
	 * @return the number of solutions of this quadratic function
	 */
	public final int getSolutionsCount() {
		
		double discriminant = getDiscriminant();
		
		if (discriminant < 0) {
			return 0;
		}
		
		if (discriminant == 0) {
			return 1;
		}
		
		return 2;
	}
	
	//method
	/**
	 * Sets the parameters of this quadratic function.
	 * 
	 * @param parameters
	 * @throws Exception if not 3 parameters are given
	 */
	public final void setParameters(double... parameters) {
		
		Validator.throwExceptionIfValueIsNotEqual("parameters count", 3, parameters.length);
		Validator.throwExceptionIfValueIsZero("coefficient of quadratic term", parameters[0]);
		
		a = parameters[0];
		b = parameters[1];
		c = parameters[2];
	}
	
	//method
	/**
	 * @return a polynom representation of this quadratic function
	 */
	public final Polynom toPolynom() {
		return new Polynom(3).setCoefficients(a, b, c);
	}
}
