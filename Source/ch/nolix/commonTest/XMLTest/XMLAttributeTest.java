//package declaration
package ch.nolix.commonTest.XMLTest;

import ch.nolix.common.XML.XMLAttribute;
import ch.nolix.common.constants.StringCatalogue;
import ch.nolix.common.test.Test;

//test class
public final class XMLAttributeTest extends Test {
	
	//test case
	public void testCase_creation() {
		
		//execution & verification
		expect(() -> new XMLAttribute("x", StringCatalogue.EMPTY_STRING));
	}
	
	//test case
	public void testCase_creation_2() {
		
		//execution
		final var XMLAttribute = new XMLAttribute("color", "green");
		
		//verification
		expect(XMLAttribute.getName()).isEqualTo("color");
		expect(XMLAttribute.getValue()).isEqualTo("green");
	}
	
	//test case
	public void testCase_toString() {
		
		//setup
		final var XMLAttribute = new XMLAttribute("color", "green");
		
		//execution & verification
		expect(XMLAttribute.toString()).isEqualTo("color='green'");
	}
}
