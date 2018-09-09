//package declaration
package ch.nolix.coreTest.mathematicsTest;

//own imports
import ch.nolix.core.mathematics.Polynom;
import ch.nolix.primitive.test2.Test;

//test class
/**
 * This class is a test class for the polynom class.
 * 
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 70
 */
public final class PolynomTest extends Test {
	
	//test case
	public void testCase_constructor() {
		
		//execution
		final Polynom polynom = new Polynom(10);
		
		//verification
		expect(polynom.getDegree()).isEqualTo(10);
	}
	
	//test case
	public void testCase_integrate_1() {
		
		//setup
		final Polynom polynom = new Polynom(2).setCoefficients(6.0);
		
		//execution
		polynom.integrate();
		
		//verification
		expect(polynom.getDegree()).isEqualTo(3);
		expect(polynom.toString()).isEqualTo("x->2x^3");
	}
	
	//test case
	public void testCase_integrate_2() {
		
		//setup
		final Polynom polynom = new Polynom(10).setCoefficients(66.0, 60.0);
		
		//execution
		polynom.integrate();
		
		//verification
		expect(polynom.getDegree()).isEqualTo(11);
		final double[] coefficients = polynom.toArray();
		expect(coefficients.length).isEqualTo(12);
		expect(coefficients[0]).isEqualTo(6.0);
		expect(coefficients[1]).isEqualTo(6.0);
	}
	
	//test case
	public void testCase_toString_1() {
		
		//setup
		final Polynom polynom = new Polynom(2).setCoefficients(1.0, 2.0, 3.0);
			
		//execution and verification
		expect(polynom.toString()).isEqualTo("x->x^2+2x+3");
	}
	
	//test case
	public void testCase_toString_2() {
		
		//setup
		final Polynom polynom = new Polynom(5).setCoefficients(1.0, 2.0, 3.0, 4.0, 5.0, 6.0);
		
		//execution and verification
		expect(polynom.toString()).isEqualTo("x->x^5+2x^4+3x^3+4x^2+5x+6");
	}
}
