//package declaration
package ch.nolix.coreTest.specificationTest;

//own imports
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.test2.Test;

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
		expectThatNot(standardSpecification.hasHeader());
		expectThatNot(standardSpecification.containsAttributes());
	}
	
	//test method
	public void test_constructor_2() {
		
		//setup
		final StandardSpecification standardSpecification = new StandardSpecification("a");
				
		//verification
		expectThat(standardSpecification.hasHeader());
		expectThatNot(standardSpecification.containsAttributes());
	}
	
	//test method
	public void test_constructor_3() {
		
		//setup
		final StandardSpecification standardSpecification = new StandardSpecification("a(b)");
				
		//verification
		expectThat(standardSpecification.hasHeader());
		expectThat(standardSpecification.containsAttributes());
	}
	
	//test method
	public void test_constructor_4() {
		
		//setup
		final StandardSpecification standardSpecification = new StandardSpecification("a(b.)");
				
		//verification
		expectThat(standardSpecification.hasHeader());
		expectThat(standardSpecification.containsAttributes());
	}
	
	//test method
	public void test_toString_1() {
		
		//setup
		final StandardSpecification standardSpecification = new StandardSpecification();
		
		//verification
		expectThat(standardSpecification.toString()).isEmpty();
	}
	
	//test method
	public void test_toString_2() {
		
		//setup
		final StandardSpecification standardSpecification = new StandardSpecification("a");
		
		//verification
		expectThat(standardSpecification.toString()).equals("a");
	}
	
	//test method
	public void test_toString_3() {
		
		//setup
		final StandardSpecification standardSpecification = new StandardSpecification("a(b)");
		
		//verification
		expectThat(standardSpecification.toString()).equals("a(b)");
	}
}
