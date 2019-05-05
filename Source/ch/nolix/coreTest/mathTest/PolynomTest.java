//package declaration
package ch.nolix.coreTest.mathTest;

import ch.nolix.core.math.Polynom;
import ch.nolix.core.test.Test;

//class
/**
 * A {@link PolynomTest} is a test for {@link Polynom}.
 * 
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 70
 */
public final class PolynomTest extends Test {
	
	//test case
	public void testCase_creation() {
		
		//execution
		final var polynom = new Polynom(10);
		
		//verification
		expect(polynom.getDegree()).isEqualTo(10);
	}
	
	//test case
	public void testCase_integrate_1() {
		
		//setup
		final var polynom = new Polynom(2).setCoefficients(6.0);
		
		//execution
		polynom.integrate();
		
		//verification
		expect(polynom.getDegree()).isEqualTo(3);
		expect(polynom.toString()).isEqualTo("x->2x^3");
	}
	
	//test case
	public void testCase_integrate_2() {
		
		//setup
		final var polynom = new Polynom(10).setCoefficients(66.0, 60.0);
		
		//execution
		polynom.integrate();
		
		//verification
			expect(polynom.getDegree()).isEqualTo(11);
			
			final var coefficients = polynom.toArray();
			expect(coefficients.length).isEqualTo(12);
			expect(coefficients[0]).isEqualTo(6.0);
			expect(coefficients[1]).isEqualTo(6.0);
	}
	
	//test case
	public void testCase_toString_1() {
		
		//setup
		final var polynom = new Polynom(2).setCoefficients(1.0, 2.0, 3.0);
			
		//execution & verification
		expect(polynom.toString()).isEqualTo("x->x^2+2x+3");
	}
	
	//test case
	public void testCase_toString_2() {
		
		//setup
		final var polynom = new Polynom(5).setCoefficients(1.0, 2.0, 3.0, 4.0, 5.0, 6.0);
		
		//execution & verification
		expect(polynom.toString()).isEqualTo("x->x^5+2x^4+3x^3+4x^2+5x+6");
	}
}
