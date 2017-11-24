//package declaration
package ch.nolix.elementTest.dataTest;

//own imports
import ch.nolix.core.test2.Test;
import ch.nolix.element.data.Title;

//test class
/**
 * A title test is a test for the title class.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 50
 */
public final class TitleTest extends Test {
	
	//test method
	public void test_getType() {
		
		//setup
		final Title title = new Title();
		
		//execution and verification
		expect(title.getType()).isEqualTo("Title");
	}
	
	//test method
	public void test_getValue() {
		
		//test parameters
		final String value = "My World";
		
		//setup
		final Title title = new Title(value);
		
		//execution and verification
		expect(title.getValue()).isEqualTo(value);
	}
	
	//test method
	public void test_getValueInQuotes() {
		
		//test parameters
		final String value = "My World";
		final String valueInQuotes = "'My World'";
		
		//setup
		final Title title = new Title(value);
		
		//execution and verification
		expect(title.getValueInQuotes()).isEqualTo(valueInQuotes);
	}
}
