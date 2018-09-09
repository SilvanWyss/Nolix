//package declaration
package ch.nolix.coreTest.enumTest;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.enums.UniDirection;
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
public final class UniDirectionTest extends Test {
	
	//test case
	public void testCase_getAttributes() {
		
		//execution
		final List<StandardSpecification> attributes
		= UniDirection.Horizontal.getAttributes();
		
		//verification
			expect(attributes.getElementCount()).isEqualTo(1);
			
			expect(attributes.getRefOne())
			.isEqualTo(new StandardSpecification("Horizontal"));
	}

	//test case
	public void testCase_getSpecification() {
		
		//execution & verification
		expect(UniDirection.Horizontal.getSpecification())
		.isEqualTo(new StandardSpecification("UniDirection(Horizontal)"));
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
