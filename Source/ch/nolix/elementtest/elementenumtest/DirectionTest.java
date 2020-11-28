//package declaration
package ch.nolix.elementtest.elementenumtest;

import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.node.Node;
import ch.nolix.common.test.Test;
import ch.nolix.element.elementenum.Direction;

//class
/**
 * This class is a test class for the direction test.
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 50
 */
public final class DirectionTest extends Test {
	
	//method
	@TestCase
	public void testCase_getAttributes() {
		
		//execution
		final LinkedList<Node> attributes
		= Direction.LEFT_TO_RIGHT.getAttributes();
		
		//verification
			expect(attributes.getElementCount()).isEqualTo(1);
			
			expect(attributes.getRefOne())
			.isEqualTo(Node.fromString("LeftToRight"));
	}

	//method
	@TestCase
	public void testCase_getSpecification() {
		
		//execution & verification
		expect(Direction.LEFT_TO_RIGHT.getSpecification())
		.isEqualTo(Node.fromString("Direction(LeftToRight)"));
	}
	
	//method
	@TestCase
	public void testCase_getType() {
		
		//execution & verification
		expect(Direction.LEFT_TO_RIGHT.getType()).isEqualTo("Direction");
	}
	
	//method
	@TestCase
	public void testCase_toString() {
		
		//execution & verification
		expect(Direction.LEFT_TO_RIGHT.toString()).isEqualTo("LEFT_TO_RIGHT");
	}
}
