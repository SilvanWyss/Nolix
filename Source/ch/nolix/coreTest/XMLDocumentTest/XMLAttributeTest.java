//package declaration
package ch.nolix.coreTest.XMLDocumentTest;

//own imports
import ch.nolix.core.XMLDocument.XMLAttribute;
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.test.Test;

//test class
public final class XMLAttributeTest extends Test {
	
	//test case
	public void testCase_constructor() {
		
		//execution & verification
		expect(() -> new XMLAttribute("x", StringCatalogue.EMPTY_STRING));
	}
	
	//test case
	public void testCase_constructor_2() {
		
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
