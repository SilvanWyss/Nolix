//package declaration
package ch.nolix.coreTest.specificationTest;

//own imports
import ch.nolix.core.specification.Statement;
import ch.nolix.core.test2.ZetaTest;

//test class
/**
 * This class is a test class for the statement class.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 60
 */
public final class StatementTest extends ZetaTest {
	
	//text method
	public void test_constructor_1() {
		
		//execution
		final Statement statement = new Statement();
		
		//verification
		expectThatNot(statement.hasHeader());
		expectThatNot(statement.containsAttributes());
		expectThatNot(statement.hasNextStatement());
	}

	//text method
	public void test_constructor_2() {
		
		//execution
		final Statement statement = new Statement("a");
		
		//verification
		expectThat(statement.hasHeader());
		expectThatNot(statement.containsAttributes());
		expectThatNot(statement.hasNextStatement());
	}
	
	//text method
	public void test_constructor_3() {
		
		//execution
		final Statement statement = new Statement("a(b)");
		
		//verification
		expectThat(statement.hasHeader());
		expectThat(statement.containsAttributes());
		expectThatNot(statement.hasNextStatement());
	}
	
	//text method
	public void test_constructor_4() {
		
		//execution
		final Statement statement = new Statement("a(b).c");
		
		//verification
		expectThat(statement.hasHeader());
		expectThat(statement.containsAttributes());
		expectThat(statement.hasNextStatement());
	}
}
