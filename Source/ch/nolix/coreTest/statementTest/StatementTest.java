//package declaration
package ch.nolix.coreTest.statementTest;

//own imports
import ch.nolix.core.statement.Statement;
import ch.nolix.core.test.Test;

//test class
/**
 * A {@link StatementTest} is a test for {@link Statement}.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 60
 */
public final class StatementTest extends Test {
	
	//test case
	public void testCase_fromString() {
		
		//execution
		final var statement = Statement.fromString("");
		
		//verification
		expectNot(statement.hasHeader());
		expectNot(statement.containsAttributes());
		expectNot(statement.hasNextStatement());
		expect(statement.toString()).isEqualTo("");
	}

	//test case
	public void testCase_fromString_2() {
		
		//execution
		final var statement =Statement.fromString("a");
		
		//verification
		expect(statement.hasHeader());
		expectNot(statement.containsAttributes());
		expectNot(statement.hasNextStatement());
		expect(statement.toString()).isEqualTo("a");
	}
	
	//test case
	public void testCase_fromString_3() {
		
		//execution
		final var statement = Statement.fromString("a(b)");
		
		//verification
		expect(statement.hasHeader());
		expect(statement.containsAttributes());
		expectNot(statement.hasNextStatement());
		expect(statement.toString()).isEqualTo("a(b)");
	}
	
	//test case
	public void testCase_fromString_4() {
		
		//execution
		final var statement = Statement.fromString("a.b(c)");
		
		//verification
		expect(statement.hasHeader());
		expectNot(statement.containsAttributes());
		expect(statement.hasNextStatement());
		expect(statement.toString()).isEqualTo("a.b(c)");
	}
	
	//test case
	public void testCase_fromString_5() {
		
		//execution
		final var statement = Statement.fromString("a(b).c");
		
		//verification
		expect(statement.hasHeader());
		expect(statement.containsAttributes());
		expect(statement.hasNextStatement());
		expect(statement.toString()).isEqualTo("a(b).c");
	}
	
	//test case
	public void testCase_fromString_6() {
		
		//execution
		final var statement = Statement.fromString("a.(b.c)");
		
		//verification
		expect(statement.hasHeader());
		expectNot(statement.containsAttributes());
		expect(statement.hasNextStatement());
		expect(statement.toString()).isEqualTo("a.(b$Dc)");
	}
	
	//test case
	public void testCase_fromString_7() {
		
		//execution
		final var statement = Statement.fromString("(a.b).c");
		
		//verification
		expectNot(statement.hasHeader());
		expect(statement.containsAttributes());
		expect(statement.hasNextStatement());
		expect(statement.toString()).isEqualTo("(a$Db).c");
	}
	
	//test case
	public void testCase_fromString_8() {
		
		//execution
		final var statement = Statement.fromString("a.b.c");
		
		//verification
		expect(statement.toString()).isEqualTo("a.b.c");
	}
}
