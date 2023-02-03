//package declaration
package ch.nolix.coretest.documenttest.xmltest;

//own imports
import ch.nolix.core.document.xml.MutableXMLNode;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class MutableXMLNodeTest extends Test {
	
	//method
	@TestCase
	public void test_toString() {
		
		//setup
		final var testUnit = new MutableXMLNode().setName("Node");
		
		//execution
		final var result = testUnit.toString();
		
		//verification
		expect(result).isEqualTo("<Node></Node>");
	}
	
	//method
	@TestCase
	public void test_toString_whenHas1Attribute() {
		
		//setup
		final var testUnit = new MutableXMLNode().setName("Node").addAttributeWithNameAndValue("key", "value");
		
		//execution
		final var result = testUnit.toString();
		
		//verification
		expect(result).isEqualTo("<Node key='value'></Node>");
	}
	
	//method
	@TestCase
	public void test_toString_whenHas1ChildNode() {
		
		//setup
		final var testUnit = new MutableXMLNode().setName("Node").addChildNode(new MutableXMLNode().setName("ChildNode"));
		
		//execution
		final var result = testUnit.toString();
		
		//verification
		expect(result).isEqualTo("<Node><ChildNode></ChildNode></Node>");
	}
	
	//method
	@TestCase
	public void test_toString_whenHas2Attributes() {
		
		//setup
		final var testUnit =
		new MutableXMLNode()
		.setName("Node")
		.addAttributeWithNameAndValue("key1", "value1")
		.addAttributeWithNameAndValue("key2", "value2");
		
		//execution
		final var result = testUnit.toString();
		
		//verification
		expect(result).isEqualTo("<Node key1='value1' key2='value2'></Node>");
	}
	
	//method
	@TestCase
	public void test_toString_whenHas2ChildNodes() {
		
		//setup
		final var testUnit =
		new MutableXMLNode()
		.setName("Node")
		.addChildNode(new MutableXMLNode().setName("ChildNode1"))
		.addChildNode(new MutableXMLNode().setName("ChildNode2"));
		
		//execution
		final var result = testUnit.toString();
		
		//verification
		expect(result).isEqualTo("<Node><ChildNode1></ChildNode1><ChildNode2></ChildNode2></Node>");
	}
}