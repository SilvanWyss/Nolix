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
 * @lines 50
 */
public final class UniDirectionTest extends Test {
	
	//test method
	public void test_getAttributes() {
		
		//execution
		final List<StandardSpecification> attributes
		= UniDirection.Horizontal.getAttributes();
		
		//verification
			expect(attributes.getElementCount()).isEqualTo(1);
			
			expect(attributes.getRefOne())
			.isEqualTo(new StandardSpecification("Horizontal"));
	}

	//test method
	public void test_getSpecification() {
		
		//execution and verification
		expect(UniDirection.Horizontal.getSpecification())
		.isEqualTo(new StandardSpecification("UniDirection(Horizontal)"));
	}
	
	//test method
	public void test_getType() {
		
		//execution and verification
		expect(UniDirection.Horizontal.getType()).isEqualTo("UniDirection");
	}
	
	//test method
	public void test_toString() {
		
		//execution and verification
		expect(UniDirection.Horizontal.toString()).isEqualTo("Horizontal");
	}
}
