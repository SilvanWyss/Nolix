/*
 * file:	StatementTest.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	60
 */

//package declaration
package ch.nolix.commonTest.specificationTest;

//own imports
import ch.nolix.common.specification.Statement;
import ch.nolix.common.zetaTest.ZetaTest;

//test class
/**
 * This class is a test class for the statement class.
 */
public final class StatementTest extends ZetaTest {
	
	//text method
	public void testConstructor1() {
		
		//setup
		final Statement statement = new Statement();
		
		//verification
		expectThatNot(statement.hasHeader());
		expectThatNot(statement.containsAttributes());
		expectThatNot(statement.hasNextStatement());
	}

	//text method
	public void testConstructor2() {
		
		//setup
		final Statement statement = new Statement("a");
		
		//verification
		expectThat(statement.hasHeader());
		expectThatNot(statement.containsAttributes());
		expectThatNot(statement.hasNextStatement());
	}
	
	//text method
	public void testConstructor3() {
		
		//setup
		final Statement statement = new Statement("a(b)");
		
		//verification
		expectThat(statement.hasHeader());
		expectThat(statement.containsAttributes());
		expectThatNot(statement.hasNextStatement());
	}
	
	//text method
	public void testConstructor4() {
		
		//setup
		final Statement statement = new Statement("a(b).c");
		
		//verification
		expectThat(statement.hasHeader());
		expectThat(statement.containsAttributes());
		expectThat(statement.hasNextStatement());
	}
}
