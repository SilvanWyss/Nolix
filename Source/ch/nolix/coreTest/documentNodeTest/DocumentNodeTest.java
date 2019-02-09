//package declaration
package ch.nolix.coreTest.documentNodeTest;

//own imports
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.test.Test;

//test class
/**
 * This class is a test class for the specification class.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 80
 */
public final class DocumentNodeTest extends Test {
	
	//test case
	public void testCase_constructor_1() {
		
		//setup
		final DocumentNode standardSpecification = new DocumentNode();
				
		//verification
		expectNot(standardSpecification.hasHeader());
		expectNot(standardSpecification.containsAttributes());
	}
	
	//test case
	public void testCase_constructor_2() {
		
		//setup
		final DocumentNode standardSpecification = new DocumentNode("a");
				
		//verification
		expect(standardSpecification.hasHeader());
		expectNot(standardSpecification.containsAttributes());
	}
	
	//test case
	public void testCase_constructor_3() {
		
		//setup
		final DocumentNode standardSpecification = new DocumentNode("a(b)");
				
		//verification
		expect(standardSpecification.hasHeader());
		expect(standardSpecification.containsAttributes());
	}
	
	//test case
	public void testCase_constructor_4() {
		
		//setup
		final DocumentNode standardSpecification = new DocumentNode("a(b.)");
				
		//verification
		expect(standardSpecification.hasHeader());
		expect(standardSpecification.containsAttributes());
	}
	
	//test case
	public void testCase_toString_1() {
		
		//setup
		final DocumentNode standardSpecification = new DocumentNode();
		
		//verification
		expect(standardSpecification.toString()).isEmpty();
	}
	
	//test case
	public void testCase_toString_2() {
		
		//setup
		final DocumentNode standardSpecification = new DocumentNode("a");
		
		//verification
		expect(standardSpecification.toString()).isEqualTo("a");
	}
	
	//test case
	public void testCase_toString_3() {
		
		//setup
		final DocumentNode standardSpecification = new DocumentNode("a(b)");
		
		//verification
		expect(standardSpecification.toString()).isEqualTo("a(b)");
	}
}
