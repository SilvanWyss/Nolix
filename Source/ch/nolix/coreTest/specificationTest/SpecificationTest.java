//package declaration
package ch.nolix.coreTest.specificationTest;

//own imports
import ch.nolix.core.specification.Specification;
import ch.nolix.core.zetaTest.ZetaTest;

//test class
/**
 * This class is a test class for the specification class.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 80
 */
public final class SpecificationTest extends ZetaTest {
	
	//test method
	public void test_constructor_1() {
		
		//setup
		final Specification specification = new Specification();
				
		//verification
		expectThatNot(specification.hasHeader());
		expectThatNot(specification.containsAttributes());
	}
	
	//test method
	public void test_constructor_2() {
		
		//setup
		final Specification specification = new Specification("a");
				
		//verification
		expectThat(specification.hasHeader());
		expectThatNot(specification.containsAttributes());
	}
	
	//test method
	public void test_constructor_3() {
		
		//setup
		final Specification specification = new Specification("a(b)");
				
		//verification
		expectThat(specification.hasHeader());
		expectThat(specification.containsAttributes());
	}
	
	//test method
	public void test_toString_1() {
		
		//setup
		final Specification specification = new Specification();
		
		//verification
		expectThat(specification.toString()).isEmpty();
	}
	
	//test method
	public void test_toString_2() {
		
		//setup
		final Specification specification = new Specification("a");
		
		//verification
		expectThat(specification.toString()).equals("a");
	}
	
	//test method
	public void test_toString_3() {
		
		//setup
		final Specification specification = new Specification("a(b)");
		
		//verification
		expectThat(specification.toString()).equals("a(b)");
	}
}
