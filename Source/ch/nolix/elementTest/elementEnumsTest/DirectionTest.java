//package declaration
package ch.nolix.elementTest.elementEnumsTest;

import ch.nolix.core.containers.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.test.Test;
import ch.nolix.element.elementEnums.Direction;

//test class
/**
 * This class is a test class for the direction test.
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 50
 */
public final class DirectionTest extends Test {
	
	//test case
	public void testCase_getAttributes() {
		
		//execution
		final List<DocumentNode> attributes
		= Direction.LeftToRight.getAttributes();
		
		//verification
			expect(attributes.getSize()).isEqualTo(1);
			
			expect(attributes.getRefOne())
			.isEqualTo(DocumentNode.createFromString("LeftToRight"));
	}

	//test case
	public void testCase_getSpecification() {
		
		//execution & verification
		expect(Direction.LeftToRight.getSpecification())
		.isEqualTo(DocumentNode.createFromString("Direction(LeftToRight)"));
	}
	
	//test case
	public void testCase_getType() {
		
		//execution & verification
		expect(Direction.LeftToRight.getType()).isEqualTo("Direction");
	}
	
	//test case
	public void testCase_toString() {
		
		//execution & verification
		expect(Direction.LeftToRight.toString()).isEqualTo("LeftToRight");
	}
}
