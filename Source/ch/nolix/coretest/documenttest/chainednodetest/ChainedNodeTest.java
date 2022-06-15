//package declaration
package ch.nolix.coretest.documenttest.chainednodetest;

//own imports
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class ChainedNodeTest extends Test {
	
	//method
	@TestCase
	public void testCase_equals_whenIsBlankAndAnUnequalChainedNodeIsGiven() {
		
		//setup
		final var testUnit = ChainedNode.fromString("");
		
		//execution
		final var result = testUnit.equals(ChainedNode.fromString("a"));
		
		//verification
		expectNot(result);
	}
	
	//method
	@TestCase
	public void testCase_equals_whenIsBlankAndAnEqualChainedNodeIsGiven() {
		
		//setup
		final var testUnit = ChainedNode.fromString("");
		
		//execution
		final var result = testUnit.equals(ChainedNode.fromString(""));
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public void testCase_fromString_A1() {
		
		//execution
		final var result = ChainedNode.fromString("");
		
		//verification
		expectNot(result.hasHeader());
		expectNot(result.containsAttributes());
		expectNot(result.hasNextNode());
		expect(result.toString()).isEqualTo("");
	}
	
	//method
	@TestCase
	public void testCase_fromString_A2() {
		
		//execution
		final var result =ChainedNode.fromString("a");
		
		//verification
		expect(result.hasHeader());
		expectNot(result.containsAttributes());
		expectNot(result.hasNextNode());
		expect(result.toString()).isEqualTo("a");
	}
	
	//method
	@TestCase
	public void testCase_fromString_A3() {
		
		//execution
		final var result = ChainedNode.fromString("a(b)");
		
		//verification
		expect(result.hasHeader());
		expect(result.containsAttributes());
		expectNot(result.hasNextNode());
		expect(result.toString()).isEqualTo("a(b)");
	}
	
	//method
	@TestCase
	public void testCase_fromString_B1() {
		
		//execution
		final var result = ChainedNode.fromString("a.b");
		
		//verification
		expect(result.hasHeader());
		expectNot(result.containsAttributes());
		expect(result.hasNextNode());
		expect(result.toString()).isEqualTo("a.b");
	}
	
	//method
	@TestCase
	public void testCase_fromString_B2() {
		
		//execution
		final var result = ChainedNode.fromString("a(b).c");
		
		//verification
		expect(result.hasHeader());
		expect(result.containsAttributes());
		expect(result.hasNextNode());
		expect(result.toString()).isEqualTo("a(b).c");
	}
	
	//method
	@TestCase
	public void testCase_fromString_B3() {
		
		//execution
		final var result = ChainedNode.fromString("a.b(c)");
		
		//verification
		expect(result.hasHeader());
		expectNot(result.containsAttributes());
		expect(result.hasNextNode());
		expect(result.toString()).isEqualTo("a.b(c)");
	}
	
	//method
	@TestCase
	public void testCase_fromString_B4() {
		
		//execution
		final var result = ChainedNode.fromString("(a.b).c");
		
		//verification
		expectNot(result.hasHeader());
		expect(result.containsAttributes());
		expect(result.hasNextNode());
		expect(result.toString()).isEqualTo("(a.b).c");
	}
	
	//method
	@TestCase
	public void testCase_fromString_B5() {
		
		//execution
		final var result = ChainedNode.fromString("a.(b.c)");
		
		//verification
		expect(result.hasHeader());
		expectNot(result.containsAttributes());
		expect(result.hasNextNode());
		expect(result.toString()).isEqualTo("a.(b.c)");
	}
	
	//method
	@TestCase
	public void testCase_fromString_B6() {
		
		//execution
		final var result = ChainedNode.fromString("a.b.c");
		
		//verification
		expect(result.hasHeader());
		expectNot(result.containsAttributes());
		expect(result.hasNextNode());
		expect(result.toString()).isEqualTo("a.b.c");
	}
	
	//method
	@TestCase
	public void testCase_fromString_C1() {
		
		//execution
		final var result = ChainedNode.fromString("a(b,c,d)");
		
		//verification
		expect(result.hasHeader());
		expect(result.containsAttributes());
		expectNot(result.hasNextNode());
		expect(result.toString()).isEqualTo("a(b,c,d)");
	}
	
	//method
	@TestCase
	public void testCase_fromString_C2() {
		
		//execution
		final var result = ChainedNode.fromString("a(b(c),d(e),f(g))");
		
		//verification
		expect(result.hasHeader());
		expect(result.containsAttributes());
		expectNot(result.hasNextNode());
		expect(result.toString()).isEqualTo("a(b(c),d(e),f(g))");
	}
	
	//method
	@TestCase
	public void testCase_fromString_C3() {
		
		//execution
		final var result = ChainedNode.fromString("a(b.c,d.e,f.g)");
		
		//verification
		expect(result.hasHeader());
		expect(result.containsAttributes());
		expectNot(result.hasNextNode());
		expect(result.toString()).isEqualTo("a(b.c,d.e,f.g)");
	}
	
	//method
	@TestCase
	public void testCase_fromString_C4() {
		
		//execution
		final var result = ChainedNode.fromString("a(b(c).d,e(f).g,h(i).j)");
		
		//verification
		expect(result.hasHeader());
		expect(result.containsAttributes());
		expectNot(result.hasNextNode());
		expect(result.toString()).isEqualTo("a(b(c).d,e(f).g,h(i).j)");
	}
	
	//method
	@TestCase
	public void testCase_fromString_C5() {
		
		//execution
		final var result = ChainedNode.fromString("a(b.c(d),e.(f.g),h.(i,j))");
		
		//verification
		expect(result.hasHeader());
		expect(result.containsAttributes());
		expectNot(result.hasNextNode());
		expect(result.toString()).isEqualTo("a(b.c(d),e.(f.g),h.(i,j))");
	}
	
	//method
	@TestCase
	public void testCase_fromString_C6() {
		
		//execution
		final var result = ChainedNode.fromString("a(b.c.d,e.f.g,h.i.j)");
		
		//verification
		expect(result.hasHeader());
		expect(result.containsAttributes());
		expectNot(result.hasNextNode());
		expect(result.toString()).isEqualTo("a(b.c.d,e.f.g,h.i.j)");
	}
	
	//method
	@TestCase
	public void testCase_getOneAttributeAsInt_whenDoesNotContainAttributes() {
		
		//setup
		final var testUnit = ChainedNode.withHeader("a");
		
		//setup verification
		expectNot(testUnit.containsAttributes());
		
		//execution & verification
		expectRunning(testUnit::getOneAttributeAsInt)
		.throwsException()
		.ofType(EmptyArgumentException.class);
	}
	
	//method
	@TestCase
	public void testCase_getOneAttributeAsInt_whenContainsOneAttributeThatDoesNotRepresentAnInt() {
		
		//setup
		final var testUnit = ChainedNode.fromString("a(b)");
		
		//setup verification
		expect(testUnit.getAttributeCount()).isEqualTo(1);
		
		//execution & verification
		expectRunning(testUnit::getOneAttributeAsInt)
		.throwsException()
		.ofType(UnrepresentingArgumentException.class);
	}
	
	//method
	@TestCase
	public void testCase_getOneAttributeAsInt_whenContainsOneAttributeThatRepresentsAnInt() {
		
		//setup
		final var testUnit = ChainedNode.fromString("a(10)");
		
		//setup verification
		expect(testUnit.getAttributeCount()).isEqualTo(1);
		
		//execution
		final var result = testUnit.getOneAttributeAsInt();
		
		//verification
		expect(result).isEqualTo(10);
	}
	
	//method
	@TestCase
	public void testCase_getOneAttributeAsInt_whenContainsSeveralAttributes() {
		
		//setup
		final var testUnit = ChainedNode.fromString("a(10, 20)");
		
		//setup verification
		expect(testUnit.getAttributeCount()).isEqualTo(2);
		
		//execution & verification
		expectRunning(testUnit::getOneAttributeAsInt)
		.throwsException()
		.ofType(InvalidArgumentException.class);
	}
	
	//method
	@TestCase
	public void testCase_isBlank_whenIsBlank() {
		
		//setup
		final var testUnit = ChainedNode.fromString("");
		
		//execution
		final var result = testUnit.isBlank();
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public void testCase_isBlank_whenHasHeaderOnly() {
		
		//setup
		final var testUnit = ChainedNode.withHeader("a");
		
		//execution
		final var result = testUnit.isBlank();
		
		//verification
		expectNot(result);
	}
	
	//method
	@TestCase
	public void testCase_toInt_whenDoesNotRepresentInt_A1() {
		
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
	public void testCase_toInt_whenDoesNotRepresentInt_A2() {
		
		//setup
		final var testUnit = ChainedNode.fromString("100(x)");
				
		//execution & verification
		expectRunning(testUnit::toInt)
		.throwsException()
		.ofType(UnrepresentingArgumentException.class)
		.withMessage("The given ChainedNode '100(x)' does not represent an Integer.");
	}
	
	//method
	@TestCase
	public void testCase_toInt_whenRepresentsNegativeInt() {
		
		//setup
		final var testUnit = ChainedNode.withHeader("-100");
		
		//execution
		final var result = testUnit.toInt();
		
		//verification
		expect(result).isEqualTo(-100);
	}
	
	//method
	@TestCase
	public void testCase_toInt_whenRepresentsPositiveInt() {
		
		//setup
		final var testUnit = ChainedNode.withHeader("100");
		
		//execution
		final var result = testUnit.toInt();
		
		//verification
		expect(result).isEqualTo(100);
	}
	
	//method
	@TestCase
	public void testCase_toInt_whenRepresentsZeroInt() {
		
		//setup
		final var testUnit = ChainedNode.withHeader("0");
		
		//execution
		final var result = testUnit.toInt();
		
		//verification
		expect(result).isEqualTo(0);
	}
	
	//method
	@TestCase
	public void testCase_toNode_whenIsBlank() {
		
		//setup
		final var testUnit = ChainedNode.fromString("");
		
		//execution
		final var result = testUnit.toNode();
		
		//verification
		expect(result).hasStringRepresentation("");
	}
	
	//method
	@TestCase
	public void testCase_toNode_whenHasHeaderOnly() {
		
		//setup
		final var testUnit = ChainedNode.withHeader("a");
		
		//execution
		final var result = testUnit.toNode();
		
		//verification
		expect(result).hasStringRepresentation("a");
	}
	
	//method
	@TestCase
	public void testCase_toNode_whenHasNextNode() {
		
		//setup
		final var testUnit = ChainedNode.fromString("a.b");
		
		//execution & verification
		expectRunning(testUnit::toNode)
		.throwsException()
		.ofType(UnrepresentingArgumentException.class)
		.withMessage("The given ChainedNode 'a.b' does not represent a Node.");
	}
	
	//method
	@TestCase
	public void testCase_withHeader_whenNullHeaderIsGiven() {
		
		//execution & verification
		expectRunning(() -> ChainedNode.withHeader(null))
		.throwsException()
		.ofType(ArgumentIsNullException.class)
		.withMessage("The given header is null.");
	}
	
	//method
	@TestCase
	public void testCase_withHeader_whenHeaderIsGiven() {
		
		//execution
		final var result = ChainedNode.withHeader("a");
		
		//verification
		expect(result).hasStringRepresentation("a");
	}
}
