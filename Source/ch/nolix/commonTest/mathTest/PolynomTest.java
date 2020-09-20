//package declaration
package ch.nolix.commonTest.mathTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.math.Polynom;
import ch.nolix.common.test.Test;

//class
/**
 * A {@link PolynomTest} is a test for {@link Polynom}s.
 * 
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 80
 */
public final class PolynomTest extends Test {
	
	//method
	@TestCase
	public void testCase_creation() {
		
		//execution
		final var polynom = Polynom.withCoefficients(1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		
		//verification
		expect(polynom.getDegree()).isEqualTo(10);
	}
	
	//method
	@TestCase
	public void testCase_integrate_1() {
		
		//setup
		final var polynom = Polynom.withCoefficients(6.0, 0.0,0.0);
		
		//execution
		final var result = polynom.getIntegrated();
		
		//verification
		expect(result.getDegree()).isEqualTo(3);
		expect(result.toString()).isEqualTo("x->2x^3");
	}
	
	//method
	@TestCase
	public void testCase_integrate_2() {
		
		//setup
		final var polynom = Polynom.withCoefficients(66.0, 60.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		
		//execution
		final var result = polynom.getIntegrated();
		
		//verification part 1
		expect(result.getDegree()).isEqualTo(11);
		
		//verification part 2
		final var coefficients = result.toArray();
		expect(coefficients.length).isEqualTo(12);
		expect(coefficients[0]).isEqualTo(6.0);
		expect(coefficients[1]).isEqualTo(6.0);
	}
	
	//method
	@TestCase
	public void testCase_toString_1() {
		
		//setup
		final var polynom = Polynom.withCoefficients(1.0, 2.0, 3.0);
			
		//execution & verification
		expect(polynom.toString()).isEqualTo("x->x^2+2x+3");
	}
	
	//method
	@TestCase
	public void testCase_toString_2() {
		
		//setup
		final var polynom = Polynom.withCoefficients(1.0, 2.0, 3.0, 4.0, 5.0, 6.0);
		
		//execution & verification
		expect(polynom.toString()).isEqualTo("x->x^5+2x^4+3x^3+4x^2+5x+6");
	}
}
