//package declaration
package ch.nolix.commontest.documenttest.chainednodetest;

//own imports
import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.document.chainednode.ChainedNode;
import ch.nolix.common.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.common.test.Test;

//class
/**
 * A {@link ChainedNodeTest} is a test for {@link ChainedNode}s.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 290
 */
public final class ChainedNodeTest extends Test {
	
	//method
	@TestCase
	public void testCase_fromString_1A() {
		
		//execution
		final var chainedNode = ChainedNode.fromString("");
		
		//verification
		expectNot(chainedNode.hasHeader());
		expectNot(chainedNode.containsAttributes());
		expectNot(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("");
	}
	
	//method
	@TestCase
	public void testCase_fromString_1B() {
		
		//execution
		final var chainedNode =ChainedNode.fromString("a");
		
		//verification
		expect(chainedNode.hasHeader());
		expectNot(chainedNode.containsAttributes());
		expectNot(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("a");
	}
	
	//method
	@TestCase
	public void testCase_fromString_1C() {
		
		//execution
		final var chainedNode = ChainedNode.fromString("a(b)");
		
		//verification
		expect(chainedNode.hasHeader());
		expect(chainedNode.containsAttributes());
		expectNot(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("a(b)");
	}
	
	//method
	@TestCase
	public void testCase_fromString_2A() {
		
		//execution
		final var chainedNode = ChainedNode.fromString("a.b");
		
		//verification
		expect(chainedNode.hasHeader());
		expectNot(chainedNode.containsAttributes());
		expect(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("a.b");
	}
	
	//method
	@TestCase
	public void testCase_fromString_2B() {
		
		//execution
		final var chainedNode = ChainedNode.fromString("a(b).c");
		
		//verification
		expect(chainedNode.hasHeader());
		expect(chainedNode.containsAttributes());
		expect(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("a(b).c");
	}
	
	//method
	@TestCase
	public void testCase_fromString_2C() {
		
		//execution
		final var chainedNode = ChainedNode.fromString("a.b(c)");
		
		//verification
		expect(chainedNode.hasHeader());
		expectNot(chainedNode.containsAttributes());
		expect(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("a.b(c)");
	}
	
	//method
	@TestCase
	public void testCase_fromString_2D() {
		
		//execution
		final var chainedNode = ChainedNode.fromString("(a.b).c");
		
		//verification
		expectNot(chainedNode.hasHeader());
		expect(chainedNode.containsAttributes());
		expect(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("(a.b).c");
	}
	
	//method
	@TestCase
	public void testCase_fromString_2E() {
		
		//execution
		final var chainedNode = ChainedNode.fromString("a.(b.c)");
		
		//verification
		expect(chainedNode.hasHeader());
		expectNot(chainedNode.containsAttributes());
		expect(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("a.(b.c)");
	}
	
	//method
	@TestCase
	public void testCase_fromString_2F() {
		
		//execution
		final var chainedNode = ChainedNode.fromString("a.b.c");
		
		//verification
		expect(chainedNode.hasHeader());
		expectNot(chainedNode.containsAttributes());
		expect(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("a.b.c");
	}
	
	//method
	@TestCase
	public void testCase_fromString_3A() {
		
		//execution
		final var chainedNode = ChainedNode.fromString("a(b,c,d)");
		
		//verification
		expect(chainedNode.hasHeader());
		expect(chainedNode.containsAttributes());
		expectNot(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("a(b,c,d)");
	}
	
	//method
	@TestCase
	public void testCase_fromString_3B() {
		
		//execution
		final var chainedNode = ChainedNode.fromString("a(b(c),d(e),f(g))");
		
		//verification
		expect(chainedNode.hasHeader());
		expect(chainedNode.containsAttributes());
		expectNot(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("a(b(c),d(e),f(g))");
	}
	
	//method
	@TestCase
	public void testCase_fromString_3C() {
		
		//execution
		final var chainedNode = ChainedNode.fromString("a(b.c,d.e,f.g)");
		
		//verification
		expect(chainedNode.hasHeader());
		expect(chainedNode.containsAttributes());
		expectNot(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("a(b.c,d.e,f.g)");
	}
	
	//method
	@TestCase
	public void testCase_fromString_3D() {
		
		//execution
		final var chainedNode = ChainedNode.fromString("a(b(c).d,e(f).g,h(i).j)");
		
		//verification
		expect(chainedNode.hasHeader());
		expect(chainedNode.containsAttributes());
		expectNot(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("a(b(c).d,e(f).g,h(i).j)");
	}
	
	//method
	@TestCase
	public void testCase_fromString_3E() {
		
		//execution
		final var chainedNode = ChainedNode.fromString("a(b.c(d),e.(f.g),h.(i,j))");
		
		//verification
		expect(chainedNode.hasHeader());
		expect(chainedNode.containsAttributes());
		expectNot(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("a(b.c(d),e.(f.g),h.(i,j))");
	}
	
	//method
	@TestCase
	public void testCase_fromString_3F() {
		
		//execution
		final var chainedNode = ChainedNode.fromString("a(b.c.d,e.f.g,h.i.j)");
		
		//verification
		expect(chainedNode.hasHeader());
		expect(chainedNode.containsAttributes());
		expectNot(chainedNode.hasNextNode());
		expect(chainedNode.toString()).isEqualTo("a(b.c.d,e.f.g,h.i.j)");
	}
	
	//method
	@TestCase
	public void testCase_toInt_1A() {
		
		//setup
		final var testUnit = ChainedNode.withHeader("-100");
		
		//execution
		final var result = testUnit.toInt();
		
		//verification
		expect(result).isEqualTo(-100);
	}
	
	//method
	@TestCase
	public void testCase_toInt_1B() {
		
		//setup
		final var testUnit = ChainedNode.withHeader("0");
		
		//execution
		final var result = testUnit.toInt();
		
		//verification
		expect(result).isEqualTo(0);
	}
	
	//method
	@TestCase
	public void testCase_toInt_1C() {
		
		//setup
		final var testUnit = ChainedNode.withHeader("100");
		
		//execution
		final var result = testUnit.toInt();
		
		//verification
		expect(result).isEqualTo(100);
	}
	
	//method
	@TestCase
	public void testCase_toInt_whenTheChainedNodeDoesNotRepresentAnInt_1A() {
		
		//setup
		final var testUnit = new ChainedNode();
		
		//execution & verification
		expectRunning(testUnit::toInt)
		.throwsException()
		.ofType(UnrepresentingArgumentException.class)
		.withMessage("The given ChainedNode does not represent an Integer.");
	}
	
	//method
	@TestCase
	public void testCase_toInt_whenTheChainedNodeDoesNotRepresentAnInt_1B() {
		
		//setup
		final var testUnit = ChainedNode.fromString("100(x)");
				
		//execution & verification
		expectRunning(testUnit::toInt)
		.throwsException()
		.ofType(UnrepresentingArgumentException.class)
		.withMessage("The given ChainedNode '100(x)' does not represent an Integer.");
	}
}
