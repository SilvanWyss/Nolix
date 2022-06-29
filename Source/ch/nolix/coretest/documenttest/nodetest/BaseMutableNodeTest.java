//package declaration
package ch.nolix.coretest.documenttest.nodetest;

//own imports
import ch.nolix.core.document.node.BaseMutableNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.ObjectTest;

//class
public abstract class BaseMutableNodeTest<MN extends BaseMutableNode<MN>> extends ObjectTest<MN> {
	
	//method
	@TestCase
	public void testCase_removeHeader() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.setHeader("Lorem");
		
		//setup verification
		expect(testUnit.hasHeader());
		
		//execution
		testUnit.removeHeader();
		
		//verification
		expectNot(testUnit.hasHeader());
	}
	
	//method
	@TestCase
	public void testCase_reset_1A() {
		
		//setup
		final var testUnit = createTestUnit();
		
		//execution
		testUnit.reset();
		
		//verification
		expectNot(testUnit.hasHeader());
		expect(testUnit.getRefChildNodes().getElementCount()).isEqualTo(0);
		expect(testUnit.toString()).isEqualTo("");
	}
	
	//method
	@TestCase
	public void testCase_reset_1B() {
		
		//setup
		final var testUnit = createTestUnit();
		
		//execution
		testUnit.resetFromString("");
		
		//verification
		expectNot(testUnit.hasHeader());
		expect(testUnit.getRefChildNodes().getElementCount()).isEqualTo(0);
		expect(testUnit.toString()).isEqualTo("");
	}
	
	//method
	@TestCase
	public void testCase_reset_2A() {
		
		//setup
		final var testUnit = createTestUnit();
		
		//execution
		testUnit.resetFromString("a");
		
		//verification
		expect(testUnit.hasHeader());
		expect(testUnit.getRefChildNodes().getElementCount()).isEqualTo(0);
		expect(testUnit.toString()).isEqualTo("a");
	}
	
	//method
	@TestCase
	public void testCase_reset_2B() {
		
		//setup
		final var testUnit = createTestUnit();
		
		//execution
		testUnit.resetFromString("a(b)");
		
		//verification
		expect(testUnit.hasHeader());
		expect(testUnit.getRefChildNodes().getElementCount()).isEqualTo(1);
		expect(testUnit.toString()).isEqualTo("a(b)");
	}
	
	//method
	@TestCase
	public void testCase_reset_2C() {
		
		//setup
		final var testUnit = createTestUnit();
		
		//execution
		testUnit.resetFromString("a(b,c,d)");
		
		//verification
		expect(testUnit.hasHeader());
		expect(testUnit.getRefChildNodes().getElementCount()).isEqualTo(3);
		expect(testUnit.toString()).isEqualTo("a(b,c,d)");
	}
	
	//method
	@TestCase
	public void testCase_reset_2D() {
		
		//setup
		final var testUnit = createTestUnit();
		
		//execution
		testUnit.resetFromString("a(b(c))");
		
		//verification
		expect(testUnit.hasHeader());
		expect(testUnit.getRefChildNodes().getElementCount()).isEqualTo(1);
		expect(testUnit.toString()).isEqualTo("a(b(c))");
	}
	
	//method
	@TestCase
	public void testCase_reset_3A() {
		
		//setup
		final var testUnit = createTestUnit();
		
		//execution
		testUnit.resetFromString("a(b(c),d(e))");
		
		//verification
		expect(testUnit.hasHeader());
		expect(testUnit.getRefChildNodes().getElementCount()).isEqualTo(2);
		expect(testUnit.toString()).isEqualTo("a(b(c),d(e))");
	}
	
	//method
	@TestCase
	public void testCase_reset_3B() {
		
		//setup
		final var testUnit = createTestUnit();
		
		//execution
		testUnit.resetFromString("a(b(c,d),e(f,g))");
		
		//verification
		expect(testUnit.hasHeader());
		expect(testUnit.getRefChildNodes().getElementCount()).isEqualTo(2);
		expect(testUnit.toString()).isEqualTo("a(b(c,d),e(f,g))");
	}
	
	//method
	@TestCase
	public void testCase_reset_whenTheGivenStringIsNotValid() {
		
		//setup
		final var testUnit = createTestUnit();
		
		//execution & verification
		expectRunning(() -> testUnit.resetFromString("a(b).c"))
		.throwsException()
		.ofType(UnrepresentingArgumentException.class);
	}
	
	//method
	@TestCase
	public void testCase_setHeader() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.setHeader("Lorem");
		
		//setup verification
		expect(testUnit.hasHeader());
		
		//execution
		testUnit.setHeader("Ipsum");
		
		//verification
		expect(testUnit.getHeader()).isEqualTo("Ipsum");
	}
	
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
