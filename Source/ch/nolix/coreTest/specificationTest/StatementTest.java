//package declaration
package ch.nolix.coreTest.specificationTest;

//own imports
import ch.nolix.core.specification.Statement;
import ch.nolix.primitive.test2.Test;

//test class
/**
 * This class is a test class for the statement class.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 60
 */
public final class StatementTest extends Test {
	
	//text method
	public void test_constructor_1() {
		
		//execution
		final Statement statement = new Statement();
		
		//verification
		expectNot(statement.hasHeader());
		expectNot(statement.containsAttributes());
		expectNot(statement.hasNextStatement());
	}

	//text method
	public void test_constructor_2() {
		
		//execution
		final Statement statement = new Statement("a");
		
		//verification
		expect(statement.hasHeader());
		expectNot(statement.containsAttributes());
		expectNot(statement.hasNextStatement());
	}
	
	//text method
	public void test_constructor_3() {
		
		//execution
		final Statement statement = new Statement("a(b)");
		
		//verification
		expect(statement.hasHeader());
		expect(statement.containsAttributes());
		expectNot(statement.hasNextStatement());
	}
	
	//text method
	public void test_constructor_4() {
		
		//execution
		final Statement statement = new Statement("a(b).c");
		
		//verification
		expect(statement.hasHeader());
		expect(statement.containsAttributes());
		expect(statement.hasNextStatement());
	}
}
