//package declaration
package ch.nolix.coretest.documenttest.xmltest;

//own imports
import ch.nolix.core.document.xml.XmlAttribute;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class XmlAttributeTest extends Test {
	
	//method
	@TestCase
	public void testCase_creation() {
		
		//execution
		final var result = new XmlAttribute("color", "green");
		
		//verification
		expect(result.getName()).isEqualTo("color");
		expect(result.getValue()).isEqualTo("green");
	}
	
	//method
	@TestCase
	public void testCase_toString() {
		
		//setup
		final var xmlAttribute = new XmlAttribute("color", "green");
		
		//execution
		final var result = xmlAttribute.toString();
		
		//verification
		expect(result).isEqualTo("color='green'");
	}
}
