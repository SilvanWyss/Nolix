//package declaration
package ch.nolix.elementTest.elementEnumsTest;

import ch.nolix.core.containers.List;
import ch.nolix.core.node.Node;
import ch.nolix.core.test.Test;
import ch.nolix.element.elementEnums.UniDirection;

//test class
/**
 * This class is a test class for the direction test.
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 50
 */
public final class UniDirectionTest extends Test {
	
	//test case
	public void testCase_getAttributes() {
		
		//execution
		final List<Node> attributes
		= UniDirection.Horizontal.getAttributes();
		
		//verification
			expect(attributes.getSize()).isEqualTo(1);
			
			expect(attributes.getRefOne())
			.isEqualTo(Node.createFromString("Horizontal"));
	}

	//test case
	public void testCase_getSpecification() {
		
		//execution & verification
		expect(UniDirection.Horizontal.getSpecification())
		.isEqualTo(Node.createFromString("UniDirection(Horizontal)"));
	}
	
	//test case
	public void testCase_getType() {
		
		//execution & verification
		expect(UniDirection.Horizontal.getType()).isEqualTo("UniDirection");
	}
	
	//test case
	public void testCase_toString() {
		
		//execution & verification
		expect(UniDirection.Horizontal.toString()).isEqualTo("Horizontal");
	}
}
