//package declaration
package ch.nolix.elementTest.dataTest;

//own imports
import ch.nolix.core.test2.Test;
import ch.nolix.element.intData.Width;

//class
/**
 * A width test is a test for the width class.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 40
 */
public class WidthTest extends Test {
	
	//test method
	public void test_constructor() {
		
		//test parameter
		final int value = 250;
		
		//execution
		final Width width = new Width(value);
		
		//verification
		expect(width.getValue()).isEqualTo(value);
	}
	
	//test method
	public void test_getType() {
		
		//setup
		final Width width = new Width();
		
		//execution and verification
		expect(width.getType()).equals(Width.TYPE_NAME);		
	}
}
