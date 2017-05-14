//package declaration
package ch.nolix.core.mathematics;

//own imports
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.zetaValidator.ZetaValidator;

//class
/**
 * A quadratic function is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 170
 */
public class QuadraticFunction {

	//attributes
	private final double a;
	private final double b;
	private final double c;
	
	//constructor
	/**
	 * Creates new quadratic function with the given coefficients.
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @throws ZeroArgumentException if the given coefficient of the quadratic term is 0.0.
	 */
	public QuadraticFunction(final double a, final double b, final double c) {
		
		//Checks if the given coefficient of the quadratic term is not 0.0.
		ZetaValidator.supposeThat(a).thatIsNamed("coefficient of the quadratic term").isNotZero();
		
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	//method
	/**
	 * @return the constant coefficient of this quadratic function.
	 */
	public double getConstantCoefficient() {
		return c;
	}
	
	//method
	/**
	 * @return the discriminant of this quadratic function.
	 */
	public double getDiscriminant() {
		return (Math.pow(b, 2) - (4 * a * c));
	}
	
	//method
	/**
	 * @return the linear coefficient of this quadratic function.
	 */
	public double getLinearCoefficient() {
		return b;
	}
	
	//method
	/**
	 * @return the maximum of this quadratic function.
	 * @throws UnexistingAttributeException if this quadratic function has no maximum.
	 */
	public double getMax() {
		
		//Checks if this quadratic function has a maximum.
		if (!hasMax()) {
			throw new UnexistingAttributeException(this, "maximum");
		}
		
		return (-b / (2 * a));
	}
	
	//method
	/**
	 * @return the minimum of this quadratic function.
	 * @throws UnexistingAttributeException if this quadratic function has no minimum.
	 */
	public double getMin() {
		
		//Checks if this quadratic function has a minimum.
		if (hasMin()) {
			throw new UnexistingAttributeException(this, "minimum");
		}
		
		return (-b / (2 * a));
	}
	
	//method
	/**
	 * @return the quadratic coefficient of this quadratic function.
	 */
	public double getQuadtaticCoefficient() {
		return a;
	}
	
	//method
	/**
	 * @return the solutions of this quadratic function.
	 */
	public double[] getSolutions() {
		
		final double discriminant = getDiscriminant();
		
		//Handles the case if this quadratic function has no solution.
		if (discriminant < 0) {
			return new double[0];
		}
		
		//Handles the case if this quadratic function has 1 solution.
		if (discriminant == 0) {
			final double[] solutions = new double[1];
			solutions[0] = -b / (2 * a);
		}
		
		//Handles the case if this quadratic function has 2 solutions.
		final double[] solutions = new double[2];
		double discriminantRoot = Math.sqrt(discriminant);
		solutions[0] = (-b - discriminantRoot) / (2 * a);
		solutions[1] = (-b + discriminantRoot) / (2 * a);
		
		return solutions;
	}
	
	//method
	/**
	 * @return the number of solutions of this quadratic function.
	 */
	public int getSolutionCount() {
		
		final double discriminant = getDiscriminant();
		
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
	 * @return true if this quadratic function has a maximum.
	 */
	public boolean hasMax() {
		return (a < 0);
	}
	
	//method
	/**
	 * @return ttrue if this quadratic function has a minimum.
	 */
	public boolean hasMin() {
		return (a > 0);
	}
	
	//method
	/**
	 * @return a polynom representation of this quadratic function.
	 */
	public Polynom toPolynom() {
		return new Polynom(3).setCoefficients(a, b, c);
	}
}
