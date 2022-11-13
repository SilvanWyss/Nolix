//package declaration
package ch.nolix.coretest.mathtest.algebratest;

import ch.nolix.core.math.algebra.Polynom;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class PolynomTest extends Test {
	
	//method
	@TestCase
	public void testCase_creation_1A() {
		
		//execution
		final var polynom = Polynom.withCoefficients();
		
		//verification
		expect(polynom.getDegree()).isEqualTo(-1);
	}
	
	//method
	@TestCase
	public void testCase_creation_1B() {
		
		//execution
		final var polynom = Polynom.withCoefficients(1.0);
		
		//verification
		expect(polynom.getDegree()).isEqualTo(0);
		expect(polynom.getCoefficientForDegree(0)).isEqualTo(1.0);
	}
	
	//method
	@TestCase
	public void testCase_creation_1C() {
		
		//execution
		final var polynom = Polynom.withCoefficients(2.0, 1.0);
		
		//verification
		expect(polynom.getDegree()).isEqualTo(1);
		expect(polynom.getCoefficientForDegree(0)).isEqualTo(1.0);
		expect(polynom.getCoefficientForDegree(1)).isEqualTo(2.0);
	}
	
	//method
	@TestCase
	public void testCase_creation_1D() {
		
		//execution
		final var polynom = Polynom.withCoefficients(3.0, 2.0, 1.0);
		
		//verification
		expect(polynom.getDegree()).isEqualTo(2);
		expect(polynom.getCoefficientForDegree(0)).isEqualTo(1.0);
		expect(polynom.getCoefficientForDegree(1)).isEqualTo(2.0);
		expect(polynom.getCoefficientForDegree(2)).isEqualTo(3.0);
	}
	
	//method
	@TestCase
	public void testCase_integrate_1A() {
		
		//setup
		final var polynom = Polynom.withCoefficients(3.0, 0.0, 0.0);
		
		//setup verification
		expect(polynom.toString()).isEqualTo("x->3x^2");
		
		//execution
		final var result = polynom.getIntegrated();
		
		//verification
		expect(result.toString()).isEqualTo("x->x^3");
	}
	
	//method
	@TestCase
	public void testCase_integrate_1B() {
		
		//setup
		final var polynom = Polynom.withCoefficients(3.0, 2.0, 1.0);
		
		//setup verification
		expect(polynom.toString()).isEqualTo("x->3x^2+2x+1");
		
		//execution
		final var result = polynom.getIntegrated();
		
		//verification
		expect(result.toString()).isEqualTo("x->x^3+x^2+1x");
	}
	
	//method
	@TestCase
	public void testCase_toString_1A() {
		
		//setup
		final var polynom = Polynom.withCoefficients();
			
		//execution & verification
		expect(polynom.toString()).isEqualTo("x->0.0");
	}
	
	//method
	@TestCase
	public void testCase_toString_1B() {
		
		//setup
		final var polynom = Polynom.withCoefficients(1.0);
			
		//execution & verification
		expect(polynom.toString()).isEqualTo("x->1");
	}
	
	//method
	@TestCase
	public void testCase_toString_1C() {
		
		//setup
		final var polynom = Polynom.withCoefficients(2.0, 1.0);
			
		//execution & verification
		expect(polynom.toString()).isEqualTo("x->2x+1");
	}
	
	//method
	@TestCase
	public void testCase_toString_1D() {
		
		//setup
		final var polynom = Polynom.withCoefficients(3.0, 2.0, 1.0);
			
		//execution & verification
		expect(polynom.toString()).isEqualTo("x->3x^2+2x+1");
	}
}
