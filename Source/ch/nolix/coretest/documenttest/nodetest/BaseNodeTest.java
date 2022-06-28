//package declaration
package ch.nolix.coretest.documenttest.nodetest;

//own imports
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.ObjectTest;

//class
public abstract class BaseNodeTest<BN extends BaseNode<BN>> extends ObjectTest<BN> {
	
	//TODO: Adjust this test cases.
	/*
	//method
	@TestCase
	public void testCase_getCopy_1A() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.resetFromString("a");
		
		//execution
		final var result = testUnit.getCopy();
		
		//verification
		expect(result.hasHeader());
		expect(result.getRefChildNodes().getElementCount()).isEqualTo(0);
		expect(result.toString()).isEqualTo("a");
	}
	
	//method
	@TestCase
	public void testCase_getCopy_1B() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.resetFromString("a(b)");
		
		//execution
		final var result = testUnit.getCopy();
		
		//verification
		expect(result.hasHeader());
		expect(result.getRefChildNodes().getElementCount()).isEqualTo(1);
		expect(result.toString()).isEqualTo("a(b)");
	}
	
	//method
	@TestCase
	public void testCase_getCopy_1C() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.resetFromString("a(b,c,d)");
		
		//execution
		final var result = testUnit.getCopy();
		
		//verification
		expect(result.hasHeader());
		expect(result.getRefChildNodes().getElementCount()).isEqualTo(3);
		expect(result.toString()).isEqualTo("a(b,c,d)");
	}
	
	//method
	@TestCase
	public void testCase_getCopy_1D() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.resetFromString("a(b(c))");
		
		//execution
		final var result = testUnit.getCopy();
		
		//verification
		expect(result.hasHeader());
		expect(result.getRefChildNodes().getElementCount()).isEqualTo(1);
		expect(result.toString()).isEqualTo("a(b(c))");
	}
	
	//method
	@TestCase
	public void testCase_getCopy_2A() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.resetFromString("a(b(c),d(e))");
		
		//execution
		final var result = testUnit.getCopy();
		
		//verification
		expect(result.hasHeader());
		expect(result.getRefChildNodes().getElementCount()).isEqualTo(2);
		expect(result.toString()).isEqualTo("a(b(c),d(e))");
	}
	
	//method
	@TestCase
	public void testCase_getCopy_2B() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.resetFromString("a(b(c,d),e(f,g))");
		
		//execution
		final var result = testUnit.getCopy();
		
		//verification
		expect(result.hasHeader());
		expect(result.getRefChildNodes().getElementCount()).isEqualTo(2);
		expect(result.toString()).isEqualTo("a(b(c,d),e(f,g))");
	}
	
	//method
	@TestCase
	public void testCase_getCopy_whenIsEmpty() {
		
		//setup
		final var testUnit = createTestUnit();
		
		//execution
		final var result = testUnit.getCopy();
		
		//verification
		expectNot(result.hasHeader());
		expect(result.getRefChildNodes().getElementCount()).isEqualTo(0);
		expect(result.toString()).isEqualTo("");
	}
	*/
	
	//method
	@TestCase
	public void testCase_getRefAttributeAt() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.addChildNode(Node.withHeader("a"), Node.withHeader("b"), Node.withHeader("c"));
		
		//execution
		final var result1 = testUnit.getRefChildNodeAt1BasedIndex(1);
		final var result2 = testUnit.getRefChildNodeAt1BasedIndex(2);
		final var result3 = testUnit.getRefChildNodeAt1BasedIndex(3);
		
		//verification part 1
		expect(result1.toString()).isEqualTo("a");
		expect(result2.toString()).isEqualTo("b");
		expect(result3.toString()).isEqualTo("c");
		
		//verification part 2
		expectRunning(() -> testUnit.getRefChildNodeAt1BasedIndex(-1)).throwsException().ofType(NonPositiveArgumentException.class);
		expectRunning(() -> testUnit.getRefChildNodeAt1BasedIndex(0)).throwsException().ofType(NonPositiveArgumentException.class);
		expectRunning(() -> testUnit.getRefChildNodeAt1BasedIndex(4)).throwsException().ofType(InvalidArgumentException.class);
	}
	
	//method
	@TestCase
	public void testCase_getHeader_1A() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.setHeader("Lorem(");
		
		//execution
		final var result = testUnit.getHeader();
		
		//verification
		expect(result).isEqualTo("Lorem(");
	}
	
	//method
	@TestCase
	public void testCase_getHeader_1B() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.setHeader("Lorem)");
		
		//execution
		final var result = testUnit.getHeader();
		
		//verification
		expect(result).isEqualTo("Lorem)");
	}
	
	//method
	@TestCase
	public void testCase_getHeader_1C() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.setHeader("Lorem.");
		
		//execution
		final var result = testUnit.getHeader();
		
		//verification
		expect(result).isEqualTo("Lorem.");
	}
	
	//method
	@TestCase
	public void testCase_getHeader_1D() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.setHeader("Lorem,");
		
		//execution
		final var result = testUnit.getHeader();
		
		//verification
		expect(result).isEqualTo("Lorem,");
	}
	
	//method
	@TestCase
	public void testCase_getRefOneAttribute_1A() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.addChildNode(Node.withHeader("a"));
		
		//execution
		final var result = testUnit.getRefSingleChildNode();
		
		
		//verification
		expect(result.toString()).isEqualTo("a");
	}
	
	//method
	@TestCase
	public void testCase_getRefOneAttribute_1B() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.reset();
		
		//execution
		expectRunning(testUnit::getRefSingleChildNode).throwsException().ofType(InvalidArgumentException.class);
	}
	
	//method
	@TestCase
	public void testCase_toString_1() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.addChildNode(Node.withHeader("a"), Node.withHeader("b"), Node.withHeader("c"));
		
		//execution
		final var result = testUnit.toString();
		
		//verification
		expect(result).isEqualTo("(a,b,c)");
	}
	
	//method
	@TestCase
	public void testCase_toString_2A() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.setHeader("Lorem(");
		
		//execution
		final var result = testUnit.toString();
		
		//verification
		expect(result).isEqualTo("Lorem$O");
	}
	
	//method
	@TestCase
	public void testCase_toString_2B() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.setHeader("Lorem)");
		
		//execution
		final var result = testUnit.toString();
		
		//verification
		expect(result).isEqualTo("Lorem$C");
	}
	
	//method
	@TestCase
	public void testCase_toString_2C() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.setHeader("Lorem,");
		
		//execution
		final var result = testUnit.toString();
		
		//verification
		expect(result).isEqualTo("Lorem$M");
	}
}
