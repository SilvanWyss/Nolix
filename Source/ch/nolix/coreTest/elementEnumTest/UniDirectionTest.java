//package declaration
package ch.nolix.coreTest.elementEnumTest;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
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
		final List<DocumentNode> attributes
		= UniDirection.Horizontal.getAttributes();
		
		//verification
			expect(attributes.getSize()).isEqualTo(1);
			
			expect(attributes.getRefOne())
			.isEqualTo(DocumentNode.createFromString("Horizontal"));
	}

	//test case
	public void testCase_getSpecification() {
		
		//execution & verification
		expect(UniDirection.Horizontal.getSpecification())
		.isEqualTo(DocumentNode.createFromString("UniDirection(Horizontal)"));
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
