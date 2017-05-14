//package declaration
package ch.nolix.elementTest.dataTest;

//own imports
import ch.nolix.core.zetaTest.ZetaTest;
import ch.nolix.element.data.Width;

//class
/**
 * This class is a test class for the width class.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 50
 */
public class WidthTest extends ZetaTest {
	
	//test method
	public void testGetValue() {
		
		//setup
		final Width width = new Width();
		
		//verification
		expectThat(width.getType()).equals(Width.SIMPLE_CLASS_NAME);		
	}
	
	//test method
	public void testReset() {
		
		//setup
		final Width width = new Width(250);
		
		//execution
		width.reset();
		
		//verification
		expectThat(width.getValue()).equals(Width.DEFAULT_VALUE);;
	}		

	//test method
	public void testSetValue() {
		
		//setup
		final Width width = new Width();
		
		//execution
		width.setValue(250);
		
		//verification
		expectThat(width.getValue()).equals(250);
	}
}
