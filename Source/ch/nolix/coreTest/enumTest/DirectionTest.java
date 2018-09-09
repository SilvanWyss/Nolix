//package declaration
package ch.nolix.coreTest.enumTest;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.enums.Direction;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.primitive.test2.Test;

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
		final List<StandardSpecification> attributes
		= Direction.LeftToRight.getAttributes();
		
		//verification
			expect(attributes.getElementCount()).isEqualTo(1);
			
			expect(attributes.getRefOne())
			.isEqualTo(new StandardSpecification("LeftToRight"));
	}

	//test case
	public void testCase_getSpecification() {
		
		//execution and verification
		expect(Direction.LeftToRight.getSpecification())
		.isEqualTo(new StandardSpecification("Direction(LeftToRight)"));
	}
	
	//test case
	public void testCase_getType() {
		
		//execution and verification
		expect(Direction.LeftToRight.getType()).isEqualTo("Direction");
	}
	
	//test case
	public void testCase_toString() {
		
		//execution and verification
		expect(Direction.LeftToRight.toString()).isEqualTo("LeftToRight");
	}
}
