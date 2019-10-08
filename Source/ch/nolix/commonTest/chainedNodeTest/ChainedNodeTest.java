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
		final var chainedNode = ChainedNode.fromString("");
		
		//verification
		expectNot(chainedNode.hasHeader());
		expectNot(chainedNode.containsAttributes());
		expectNot(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("");
	}
	
	//test case
	public void testCase_fromString_1B() {
		
		//execution
		final var chainedNode =ChainedNode.fromString("a");
		
		//verification
		expect(chainedNode.hasHeader());
		expectNot(chainedNode.containsAttributes());
		expectNot(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("a");
	}
	
	//test case
	public void testCase_fromString_1C() {
		
		//execution
		final var chainedNode = ChainedNode.fromString("a(b)");
		
		//verification
		expect(chainedNode.hasHeader());
		expect(chainedNode.containsAttributes());
		expectNot(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("a(b)");
	}
	
	//test case
	public void testCase_fromString_2A() {
		
		//execution
		final var chainedNode = ChainedNode.fromString("a.b");
		
		//verification
		expect(chainedNode.hasHeader());
		expectNot(chainedNode.containsAttributes());
		expect(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("a.b");
	}
	
	//test case
	public void testCase_fromString_2B() {
		
		//execution
		final var chainedNode = ChainedNode.fromString("a(b).c");
		
		//verification
		expect(chainedNode.hasHeader());
		expect(chainedNode.containsAttributes());
		expect(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("a(b).c");
	}
	
	//test case
	public void testCase_fromString_2C() {
		
		//execution
		final var chainedNode = ChainedNode.fromString("a.b(c)");
		
		//verification
		expect(chainedNode.hasHeader());
		expectNot(chainedNode.containsAttributes());
		expect(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("a.b(c)");
	}
	
	//test case
	public void testCase_fromString_2D() {
		
		//execution
		final var chainedNode = ChainedNode.fromString("(a.b).c");
		
		//verification
		expectNot(chainedNode.hasHeader());
		expect(chainedNode.containsAttributes());
		expect(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("(a.b).c");
	}
	
	//test case
	public void testCase_fromString_2E() {
		
		//execution
		final var chainedNode = ChainedNode.fromString("a.(b.c)");
		
		//verification
		expect(chainedNode.hasHeader());
		expectNot(chainedNode.containsAttributes());
		expect(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("a.(b.c)");
	}
	
	//test case
	public void testCase_fromString_2F() {
		
		//execution
		final var chainedNode = ChainedNode.fromString("a.b.c");
		
		//verification
		expect(chainedNode.hasHeader());
		expectNot(chainedNode.containsAttributes());
		expect(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("a.b.c");
	}
	
	//test case
	public void testCase_fromString_3A() {
		
		//execution
		final var chainedNode = ChainedNode.fromString("a(b,c,d)");
		
		//verification
		expect(chainedNode.hasHeader());
		expect(chainedNode.containsAttributes());
		expectNot(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("a(b,c,d)");
	}
	
	//test case
	public void testCase_fromString_3B() {
		
		//execution
		final var chainedNode = ChainedNode.fromString("a(b(c),d(e),f(g))");
		
		//verification
		expect(chainedNode.hasHeader());
		expect(chainedNode.containsAttributes());
		expectNot(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("a(b(c),d(e),f(g))");
	}
	
	//test case
	public void testCase_fromString_3C() {
		
		//execution
		final var chainedNode = ChainedNode.fromString("a(b.c,d.e,f.g)");
		
		//verification
		expect(chainedNode.hasHeader());
		expect(chainedNode.containsAttributes());
		expectNot(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("a(b.c,d.e,f.g)");
	}
	
	//test case
	public void testCase_fromString_3D() {
		
		//execution
		final var chainedNode = ChainedNode.fromString("a(b(c).d,e(f).g,h(i).j)");
		
		//verification
		expect(chainedNode.hasHeader());
		expect(chainedNode.containsAttributes());
		expectNot(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("a(b(c).d,e(f).g,h(i).j)");
	}
	
	//test case
	public void testCase_fromString_3E() {
		
		//execution
		final var chainedNode = ChainedNode.fromString("a(b.c(d),e.(f.g),h.(i,j))");
		
		//verification
		expect(chainedNode.hasHeader());
		expect(chainedNode.containsAttributes());
		expectNot(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("a(b.c(d),e.(f.g),h.(i,j))");
	}
	
	//test case
	public void testCase_fromString_3F() {
		
		//execution
		final var chainedNode = ChainedNode.fromString("a(b.c.d,e.f.g,h.i.j)");
		
		//verification
		expect(chainedNode.hasHeader());
		expect(chainedNode.containsAttributes());
		expectNot(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("a(b.c.d,e.f.g,h.i.j)");
	}
}
