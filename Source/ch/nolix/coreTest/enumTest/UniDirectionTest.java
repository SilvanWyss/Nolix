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
		expect(UniDirection.Horizontal.getAttribute())
		.equalsTo(new StandardSpecification("Horizontal"));
	}
	
	//test method
	public void test_getAttributes() {
		
		//execution
		final List<StandardSpecification> attributes
		= UniDirection.Horizontal.getAttributes();
		
		//verification
			expect(attributes.getElementCount()).isEqualTo(1);
			
			expect(attributes.getRefOne())
			.equalsTo(new StandardSpecification("Horizontal"));
	}

	//test method
	public void test_getSpecification() {
		
		//execution and verification
		expect(UniDirection.Horizontal.getSpecification())
		.equalsTo(new StandardSpecification("UniDirection(Horizontal)"));
	}
	
	//test method
	public void test_getType() {
		
		//execution and verification
		expect(UniDirection.Horizontal.getType())
		.equals("UniDirection");
	}
	
	//test method
	public void test_toString() {
		
		//execution and verification
		expect(UniDirection.Horizontal.toString())
		.equals("UniDirection(Horizontal)");
	}
}
