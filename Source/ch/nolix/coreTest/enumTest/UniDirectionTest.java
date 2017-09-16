//package declaration
package ch.nolix.coreTest.enumTest;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.enums.UniDirection;
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
public final class UniDirectionTest extends Test {
	
	//test method
	public void test_getAttribtue() {
		
		//execution and verification
		expectThat(UniDirection.Horizontal.getAttribute())
		.equals(new StandardSpecification("Horizontal"));
	}
	
	//test method
	public void test_getAttributes() {
		
		//execution
		final List<StandardSpecification> attributes
		= UniDirection.Horizontal.getAttributes();
		
		//verification
			expectThat(attributes.getElementCount()).equals(1);
			
			expectThat(attributes.getRefOne())
			.equals(new StandardSpecification("Horizontal"));
	}

	//test method
	public void test_getSpecification() {
		
		//execution and verification
		expectThat(UniDirection.Horizontal.getSpecification())
		.equals(new StandardSpecification("UniDirection(Horizontal)"));
	}
	
	//test method
	public void test_getType() {
		
		//execution and verification
		expectThat(UniDirection.Horizontal.getType())
		.equals("UniDirection");
	}
	
	//test method
	public void test_toString() {
		
		//execution and verification
		expectThat(UniDirection.Horizontal.toString())
		.equals("UniDirection(Horizontal)");
	}
}
