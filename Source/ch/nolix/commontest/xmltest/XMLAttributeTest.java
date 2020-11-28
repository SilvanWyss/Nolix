//package declaration
package ch.nolix.commontest.xmltest;

import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.test.Test;
import ch.nolix.common.xml.XMLAttribute;

//class
public final class XMLAttributeTest extends Test {
	
	//method
	@TestCase
	public void testCase_creation() {
		
		//execution
		final var result = new XMLAttribute("color", "green");
		
		//verification
		expect(result.getName()).isEqualTo("color");
		expect(result.getValue()).isEqualTo("green");
	}
	
	//method
	@TestCase
	public void testCase_toString() {
		
		//setup
		final var lXMLAttribute = new XMLAttribute("color", "green");
		
		//execution
		final var result = lXMLAttribute.toString();
		
		//verification
		expect(result).isEqualTo("color='green'");
	}
}
