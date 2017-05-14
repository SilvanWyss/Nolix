//package declaration
package ch.nolix.coreTest.mathematicsTest;

//own imports
import ch.nolix.core.mathematics.Polynom;
import ch.nolix.core.test2.ZetaTest;

//test class
/**
 * This class is a test class for the polynom class.
 * 
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 40
 */
public final class PolynomTest extends ZetaTest {
	
	//test method
	public void test_constructor() {
		
		//execution
		final Polynom polynom = new Polynom(10);
		
		//verification
		expectThat(polynom.getDegree()).equals(10);
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
