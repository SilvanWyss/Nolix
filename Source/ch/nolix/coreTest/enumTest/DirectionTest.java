//package declaration
package ch.nolix.coreTest.enumTest;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.enums.Direction;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.test2.Test;

//test class
/**
 * This class is a test class for the direction test.
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 60
 */
public final class DirectionTest extends Test {
	
	//test method
	public void test_getAttribtue() {
		
		//execution and verification
		expectThat(Direction.LeftToRight.getAttribute())
		.equals(new StandardSpecification("LeftToRight"));
	}
	
	//test method
	public void test_getAttributes() {
		
		//execution
		final List<StandardSpecification> attributes
		= Direction.LeftToRight.getAttributes();
		
		//verification
			expectThat(attributes.getElementCount()).equals(1);
			
			expectThat(attributes.getRefOne())
			.equals(new StandardSpecification("LeftToRight"));
	}

	//test method
	public void test_getSpecification() {
		
		//execution and verification
		expectThat(Direction.LeftToRight.getSpecification())
		.equals(new StandardSpecification("Direction(LeftToRight)"));
	}
	
	//test method
	public void test_getType() {
		
		//execution and verification
		expectThat(Direction.LeftToRight.getType())
		.equals("Direction");
	}
	
	//test method
	public void test_toString() {
		
		//execution and verification
		expectThat(Direction.LeftToRight.toString())
		.equals("Direction(LeftToRight)");
	}
}
