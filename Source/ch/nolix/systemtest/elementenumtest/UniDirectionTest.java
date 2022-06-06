//package declaration
package ch.nolix.systemtest.elementenumtest;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.systemapi.guiapi.structureproperty.DirectionInRectangle;

//class
/**
 * A {@link UniDirectionTest} is a test for {@link DirectionInRectangle}s.
 * 
 * @author Silvan Wyss
 * @date 2017-09-16
 */
public final class UniDirectionTest extends Test {
	
	//method
	@TestCase
	public void testCase_getAttributes() {
		
		//execution
		final LinkedList<Node> attributes
		= DirectionInRectangle.HORIZONTAL.getAttributes();
		
		//verification
			expect(attributes.getElementCount()).isEqualTo(1);
			
			expect(attributes.getRefOne())
			.isEqualTo(Node.fromString("Horizontal"));
	}

	//method
	@TestCase
	public void testCase_getSpecification() {
		
		//execution & verification
		expect(DirectionInRectangle.HORIZONTAL.getSpecification())
		.isEqualTo(Node.fromString("UniDirection(Horizontal)"));
	}
	
	//method
	@TestCase
	public void testCase_toString() {
		
		//execution & verification
		expect(DirectionInRectangle.HORIZONTAL.toString()).isEqualTo("HORIZONTAL");
	}
}
