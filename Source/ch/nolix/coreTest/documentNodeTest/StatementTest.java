//package declaration
package ch.nolix.coreTest.documentNodeTest;

//own imports
import ch.nolix.core.documentNode.Statement;
import ch.nolix.core.test.Test;

//test class
/**
 * This class is a test class for the statement class.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 60
 */
public final class StatementTest extends Test {
	
	//test case
	public void testCase_constructor_1() {
		
		//execution
		final Statement statement = new Statement();
		
		//verification
		expectNot(statement.hasHeader());
		expectNot(statement.containsAttributes());
		expectNot(statement.hasNextStatement());
	}

	//test case
	public void testCase_constructor_2() {
		
		//execution
		final Statement statement = new Statement("a");
		
		//verification
		expect(statement.hasHeader());
		expectNot(statement.containsAttributes());
		expectNot(statement.hasNextStatement());
	}
	
	//test case
	public void testCase_constructor_3() {
		
		//execution
		final Statement statement = new Statement("a(b)");
		
		//verification
		expect(statement.hasHeader());
		expect(statement.containsAttributes());
		expectNot(statement.hasNextStatement());
	}
	
	//test case
	public void testCase_constructor_4() {
		
		//execution
		final Statement statement = new Statement("a(b).c");
		
		//verification
		expect(statement.hasHeader());
		expect(statement.containsAttributes());
		expect(statement.hasNextStatement());
	}
}
