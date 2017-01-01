/*
 * file:	SpecificationTest.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	80
 */

//package declaration
package ch.nolix.commonTest.specificationTest;

//own imports
import ch.nolix.common.specification.Specification;
import ch.nolix.common.zetaTest.ZetaTest;

//test class
/**
 * This class is a test class for the specification class.
 */
public final class SpecificationTest extends ZetaTest {
	
	//test method
	public void testConstructor1() {
		
		//setup
		final Specification specification = new Specification();
				
		//verification
		expectThatNot(specification.hasHeader());
		expectThatNot(specification.containsAttributes());
	}
	
	//test method
	public void testConstructor2() {
		
		//setup
		final Specification specification = new Specification("a");
				
		//verification
		expectThat(specification.hasHeader());
		expectThatNot(specification.containsAttributes());
	}
	
	//test method
	public void testConstructor3() {
		
		//setup
		final Specification specification = new Specification("a(b)");
				
		//verification
		expectThat(specification.hasHeader());
		expectThat(specification.containsAttributes());
	}
	
	//test method
	public void testToString1() {
		
		//setup
		final Specification specification = new Specification();
		
		//verification
		expectThat(specification.toString()).isEmpty();
	}
	
	//test method
	public void testToString2() {
		
		//setup
		final Specification specification = new Specification("a");
		
		//verification
		expectThat(specification.toString()).equals("a");
	}
	
	//test method
	public void testToString3() {
		
		//setup
		final Specification specification = new Specification("a(b)");
		
		//verification
		expectThat(specification.toString()).equals("a(b)");
	}
}
