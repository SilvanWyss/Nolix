//package declaration
package ch.nolix.commontest.xmltest;

//own imports
import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.document.xml.XMLNode;
import ch.nolix.common.test.Test;

//class
public final class XMLNodeTest extends Test {
	
	//method
	@TestCase
	public void test_toString() {
		
		//setup
		final var testUnit = new XMLNode().setName("Node");
		
		//execution
		final var result = testUnit.toString();
		
		//verification
		expect(result).isEqualTo("<Node></Node>");
	}
	
	//method
	@TestCase
	public void test_toString_whenHas1Attribute() {
		
		//setup
		final var testUnit = new XMLNode().setName("Node").addAttribute("key", "value");
		
		//execution
		final var result = testUnit.toString();
		
		//verification
		expect(result).isEqualTo("<Node key='value'></Node>");
	}
	
	//method
	@TestCase
	public void test_toString_whenHas1ChildNode() {
		
		//setup
		final var testUnit = new XMLNode().setName("Node").addChildNode(new XMLNode().setName("ChildNode"));
		
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
		new XMLNode()
		.setName("Node")
		.addAttribute("key1", "value1")
		.addAttribute("key2", "value2");
		
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
		new XMLNode()
		.setName("Node")
		.addChildNode(new XMLNode().setName("ChildNode1"))
		.addChildNode(new XMLNode().setName("ChildNode2"));
		
		//execution
		final var result = testUnit.toString();
		
		//verification
		expect(result).isEqualTo("<Node><ChildNode1></ChildNode1><ChildNode2></ChildNode2></Node>");
	}
}
