//package declaration
package ch.nolix.coreTest.specificationTest;

//own imports
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.primitive.test2.Test;

//test class
/**
 * This class is a test class for the specification class.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 80
 */
public final class StandardSpecificationTest extends Test {
	
	//test method
	public void test_constructor_1() {
		
		//setup
		final StandardSpecification standardSpecification = new StandardSpecification();
				
		//verification
		expectNot(standardSpecification.hasHeader());
		expectNot(standardSpecification.containsAttributes());
	}
	
	//test method
	public void test_constructor_2() {
		
		//setup
		final StandardSpecification standardSpecification = new StandardSpecification("a");
				
		//verification
		expect(standardSpecification.hasHeader());
		expectNot(standardSpecification.containsAttributes());
	}
	
	//test method
	public void test_constructor_3() {
		
		//setup
		final StandardSpecification standardSpecification = new StandardSpecification("a(b)");
				
		//verification
		expect(standardSpecification.hasHeader());
		expect(standardSpecification.containsAttributes());
	}
	
	//test method
	public void test_constructor_4() {
		
		//setup
		final StandardSpecification standardSpecification = new StandardSpecification("a(b.)");
				
		//verification
		expect(standardSpecification.hasHeader());
		expect(standardSpecification.containsAttributes());
	}
	
	//test method
	public void test_toString_1() {
		
		//setup
		final StandardSpecification standardSpecification = new StandardSpecification();
		
		//verification
		expect(standardSpecification.toString()).isEmpty();
	}
	
	//test method
	public void test_toString_2() {
		
		//setup
		final StandardSpecification standardSpecification = new StandardSpecification("a");
		
		//verification
		expect(standardSpecification.toString()).isEqualTo("a");
	}
	
	//test method
	public void test_toString_3() {
		
		//setup
		final StandardSpecification standardSpecification = new StandardSpecification("a(b)");
		
		//verification
		expect(standardSpecification.toString()).isEqualTo("a(b)");
	}
}
