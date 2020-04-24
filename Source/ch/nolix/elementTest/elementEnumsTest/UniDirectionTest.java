//package declaration
package ch.nolix.elementTest.elementEnumsTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.node.Node;
import ch.nolix.common.test.Test;
import ch.nolix.element.elementEnums.UniDirection;

//class
/**
 * This class is a test class for the direction test.
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 50
 */
public final class UniDirectionTest extends Test {
	
	//method
	@TestCase
	public void testCase_getAttributes() {
		
		//execution
		final LinkedList<Node> attributes
		= UniDirection.Horizontal.getAttributes();
		
		//verification
			expect(attributes.getSize()).isEqualTo(1);
			
			expect(attributes.getRefOne())
			.isEqualTo(Node.fromString("Horizontal"));
	}

	//method
	@TestCase
	public void testCase_getSpecification() {
		
		//execution & verification
		expect(UniDirection.Horizontal.getSpecification())
		.isEqualTo(Node.fromString("UniDirection(Horizontal)"));
	}
	
	//method
	@TestCase
	public void testCase_getType() {
		
		//execution & verification
		expect(UniDirection.Horizontal.getType()).isEqualTo("UniDirection");
	}
	
	//method
	@TestCase
	public void testCase_toString() {
		
		//execution & verification
		expect(UniDirection.Horizontal.toString()).isEqualTo("Horizontal");
	}
}
