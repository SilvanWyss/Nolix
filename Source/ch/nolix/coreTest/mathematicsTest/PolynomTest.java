//package declaration
package ch.nolix.coreTest.mathematicsTest;

//own imports
import ch.nolix.core.mathematics.Polynom;
import ch.nolix.core.test2.Test;

//test class
/**
 * This class is a test class for the polynom class.
 * 
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 70
 */
public final class PolynomTest extends Test {
	
	//test method
	public void test_constructor() {
		
		//execution
		final Polynom polynom = new Polynom(10);
		
		//verification
		expectThat(polynom.getDegree()).equals(10);
	}
	
	//test method
	public void test_integrate_1() {
		
		//setup
		final Polynom polynom = new Polynom(2).setCoefficients(6.0);
		
		//execution
		polynom.integrate();
		
		//verification
		expectThat(polynom.getDegree()).equals(3);
		expectThat(polynom.toString()).equals("x->2x^3");
	}
	
	//test method
	public void test_integrate_2() {
		
		//setup
		final Polynom polynom = new Polynom(10).setCoefficients(66.0, 60.0);
		
		//execution
		polynom.integrate();
		
		//verification
		expectThat(polynom.getDegree()).equals(11);
		final double[] coefficients = polynom.toArray();
		expectThat(coefficients.length).equals(12);
		expectThat(coefficients[0]).equals(6.0);
		expectThat(coefficients[1]).equals(6.0);
	}
	
	//test method
	public void test_toString_1() {
		
		//setup
		final Polynom polynom = new Polynom(2).setCoefficients(1.0, 2.0, 3.0);
			
		//execution and verification
		expectThat(polynom.toString()).equals("x->x^2+2x+3");
	}
	
	//test method
	public void test_toString_2() {
		
		//setup
		final Polynom polynom = new Polynom(5).setCoefficients(1.0, 2.0, 3.0, 4.0, 5.0, 6.0);
		
		//execution and verification
		expectThat(polynom.toString()).equals("x->x^5+2x^4+3x^3+4x^2+5x+6");
	}
}
