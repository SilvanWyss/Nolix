//package declaration
package ch.nolix.systemtest.elementenumtest;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.elementenum.Direction;

//class
/**
 * A {@link DirectionTest} is a test for {@link Direction}s.
 * 
 * @author Silvan Wyss
 * @date 2017-09-16
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
	public void testCase_toString() {
		
		//execution & verification
		expect(Direction.LEFT_TO_RIGHT.toString()).isEqualTo("LEFT_TO_RIGHT");
	}
}
