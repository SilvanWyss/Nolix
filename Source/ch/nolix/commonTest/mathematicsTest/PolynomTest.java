/*
 * file:	PolynomTest.java
 * author:	Silvan Wyss
 * month:	2016-05
 * lines:	50
 */

//package declaration
package ch.nolix.commonTest.mathematicsTest;

//own imports
import ch.nolix.common.mathematics.Polynom;
import ch.nolix.common.zetaTest.ZetaTest;

//test class
/**
 * This class is a test class for the polynom class.
 */
public final class PolynomTest extends ZetaTest {
	
	public void testConstructor() {
		
		//execution
		final Polynom polynom = new Polynom(10);
		
		//verification
		expectThat(polynom.getDegree()).equals(10);
	}
		
	//test method
	public void testDerive() {
		
		//setup
		final Polynom polynom = new Polynom(10);
		
		//execution
		polynom.derive();
		
		//verification
		expectThat(polynom.getDegree()).equals(9);
	}
	
	//test method
	public void testToString1() {
		
		//setup
		final Polynom polynom = new Polynom(2).setCoefficients(1.0, 2.0, 3.0);
			
		//execution and verification
		expectThat(polynom.toString()).equals("x->x^2+2x+3");
	}
	
	//test method
	public void testToString2() {
		
		//setup
		final Polynom polynom = new Polynom(5).setCoefficients(1.0, 2.0, 3.0, 4.0, 5.0, 6.0);
		
		//execution and verification
		expectThat(polynom.toString()).equals("x->x^5+2x^4+3x^3+4x^2+5x+6");
	}
}
