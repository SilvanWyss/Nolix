//package declaration
package ch.nolix.commonTest.chainedNodeTest;

//own imports
import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.common.test.Test;

//test class
/**
 * A {@link ChainedNodeTest} is a test for {@link ChainedNode}.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 210
 */
public final class ChainedNodeTest extends Test {
	
	//test case
	public void testCase_fromString_1A() {
		
		//execution
		final var statement = ChainedNode.fromString("");
		
		//verification
		expectNot(statement.hasHeader());
		expectNot(statement.containsAttributes());
		expectNot(statement.hasNextNode());
		expect(statement.toString()).isEqualTo("");
	}
	
	//test case
	public void testCase_fromString_1B() {
		
		//execution
		final var statement =ChainedNode.fromString("a");
		
		//verification
		expect(statement.hasHeader());
		expectNot(statement.containsAttributes());
		expectNot(statement.hasNextNode());
		expect(statement.toString()).isEqualTo("a");
	}
	
	//test case
	public void testCase_fromString_1C() {
		
		//execution
		final var statement = ChainedNode.fromString("a(b)");
		
		//verification
		expect(statement.hasHeader());
		expect(statement.containsAttributes());
		expectNot(statement.hasNextNode());
		expect(statement.toString()).isEqualTo("a(b)");
	}
	
	//test case
	public void testCase_fromString_2A() {
		
		//execution
		final var statement = ChainedNode.fromString("a.b");
		
		//verification
		expect(statement.hasHeader());
		expectNot(statement.containsAttributes());
		expect(statement.hasNextNode());
		expect(statement.toString()).isEqualTo("a.b");
	}
	
	//test case
	public void testCase_fromString_2B() {
		
		//execution
		final var statement = ChainedNode.fromString("a(b).c");
		
		//verification
		expect(statement.hasHeader());
		expect(statement.containsAttributes());
		expect(statement.hasNextNode());
		expect(statement.toString()).isEqualTo("a(b).c");
	}
	
	//test case
	public void testCase_fromString_2C() {
		
		//execution
		final var statement = ChainedNode.fromString("a.b(c)");
		
		//verification
		expect(statement.hasHeader());
		expectNot(statement.containsAttributes());
		expect(statement.hasNextNode());
		expect(statement.toString()).isEqualTo("a.b(c)");
	}
	
	//test case
	public void testCase_fromString_2D() {
		
		//execution
		final var statement = ChainedNode.fromString("(a.b).c");
		
		//verification
		expectNot(statement.hasHeader());
		expect(statement.containsAttributes());
		expect(statement.hasNextNode());
		expect(statement.toString()).isEqualTo("(a.b).c");
	}
	
	//test case
	public void testCase_fromString_2E() {
		
		//execution
		final var statement = ChainedNode.fromString("a.(b.c)");
		
		//verification
		expect(statement.hasHeader());
		expectNot(statement.containsAttributes());
		expect(statement.hasNextNode());
		expect(statement.toString()).isEqualTo("a.(b.c)");
	}
	
	//test case
	public void testCase_fromString_2F() {
		
		//execution
		final var statement = ChainedNode.fromString("a.b.c");
		
		//verification
		expect(statement.hasHeader());
		expectNot(statement.containsAttributes());
		expect(statement.hasNextNode());
		expect(statement.toString()).isEqualTo("a.b.c");
	}
	
	//test case
	public void testCase_fromString_3A() {
		
		//execution
		final var statement = ChainedNode.fromString("a(b,c,d)");
		
		//verification
		expect(statement.hasHeader());
		expect(statement.containsAttributes());
		expectNot(statement.hasNextNode());
		expect(statement.toString()).isEqualTo("a(b,c,d)");
	}
	
	//test case
	public void testCase_fromString_3B() {
		
		//execution
		final var statement = ChainedNode.fromString("a(b(c),d(e),f(g))");
		
		//verification
		expect(statement.hasHeader());
		expect(statement.containsAttributes());
		expectNot(statement.hasNextNode());
		expect(statement.toString()).isEqualTo("a(b(c),d(e),f(g))");
	}
	
	//test case
	public void testCase_fromString_3C() {
		
		//execution
		final var statement = ChainedNode.fromString("a(b.c,d.e,f.g)");
		
		//verification
		expect(statement.hasHeader());
		expect(statement.containsAttributes());
		expectNot(statement.hasNextNode());
		expect(statement.toString()).isEqualTo("a(b.c,d.e,f.g)");
	}
	
	//test case
	public void testCase_fromString_3D() {
		
		//execution
		final var statement = ChainedNode.fromString("a(b(c).d,e(f).g,h(i).j)");
		
		//verification
		expect(statement.hasHeader());
		expect(statement.containsAttributes());
		expectNot(statement.hasNextNode());
		expect(statement.toString()).isEqualTo("a(b(c).d,e(f).g,h(i).j)");
	}
	
	//test case
	public void testCase_fromString_3E() {
		
		//execution
		final var statement = ChainedNode.fromString("a(b.c(d),e.(f.g),h.(i,j))");
		
		//verification
		expect(statement.hasHeader());
		expect(statement.containsAttributes());
		expectNot(statement.hasNextNode());
		expect(statement.toString()).isEqualTo("a(b.c(d),e.(f.g),h.(i,j))");
	}
	
	//test case
	public void testCase_fromString_3F() {
		
		//execution
		final var statement = ChainedNode.fromString("a(b.c.d,e.f.g,h.i.j)");
		
		//verification
		expect(statement.hasHeader());
		expect(statement.containsAttributes());
		expectNot(statement.hasNextNode());
		expect(statement.toString()).isEqualTo("a(b.c.d,e.f.g,h.i.j)");
	}
}
