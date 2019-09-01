//package declaration
package ch.nolix.commonTest.chainedNodeTest;

import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.common.test.Test;

//test class
/**
 * A {@link ChainedNodeTest} is a test for {@link ChainedNode}.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 60
 */
public final class ChainedNodeTest extends Test {
	
	//test case
	public void testCase_fromString() {
		
		//execution
		final var statement = ChainedNode.fromString("");
		
		//verification
		expectNot(statement.hasHeader());
		expectNot(statement.containsAttributes());
		expectNot(statement.hasNextNode());
		expect(statement.toString()).isEqualTo("");
	}

	//test case
	public void testCase_fromString_2() {
		
		//execution
		final var statement =ChainedNode.fromString("a");
		
		//verification
		expect(statement.hasHeader());
		expectNot(statement.containsAttributes());
		expectNot(statement.hasNextNode());
		expect(statement.toString()).isEqualTo("a");
	}
	
	//test case
	public void testCase_fromString_3() {
		
		//execution
		final var statement = ChainedNode.fromString("a(b)");
		
		//verification
		expect(statement.hasHeader());
		expect(statement.containsAttributes());
		expectNot(statement.hasNextNode());
		expect(statement.toString()).isEqualTo("a(b)");
	}
	
	//test case
	public void testCase_fromString_4() {
		
		//execution
		final var statement = ChainedNode.fromString("a.b(c)");
		
		//verification
		expect(statement.hasHeader());
		expectNot(statement.containsAttributes());
		expect(statement.hasNextNode());
		expect(statement.toString()).isEqualTo("a.b(c)");
	}
	
	//test case
	public void testCase_fromString_5() {
		
		//execution
		final var statement = ChainedNode.fromString("a(b).c");
		
		//verification
		expect(statement.hasHeader());
		expect(statement.containsAttributes());
		expect(statement.hasNextNode());
		expect(statement.toString()).isEqualTo("a(b).c");
	}
	
	//test case
	public void testCase_fromString_6() {
		
		//execution
		final var statement = ChainedNode.fromString("a.(b.c)");
		
		//verification
		expect(statement.hasHeader());
		expectNot(statement.containsAttributes());
		expect(statement.hasNextNode());
		expect(statement.toString()).isEqualTo("a.(b$Dc)");
	}
	
	//test case
	public void testCase_fromString_7() {
		
		//execution
		final var statement = ChainedNode.fromString("(a.b).c");
		
		//verification
		expectNot(statement.hasHeader());
		expect(statement.containsAttributes());
		expect(statement.hasNextNode());
		expect(statement.toString()).isEqualTo("(a$Db).c");
	}
	
	//test case
	public void testCase_fromString_8() {
		
		//execution
		final var statement = ChainedNode.fromString("a.b.c");
		
		//verification
		expect(statement.toString()).isEqualTo("a.b.c");
	}
}
