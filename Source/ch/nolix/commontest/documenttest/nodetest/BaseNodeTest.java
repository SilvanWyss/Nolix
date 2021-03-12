//package declaration
package ch.nolix.commontest.documenttest.nodetest;

import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.common.testing.test.ObjectTest;

//class
public abstract class BaseNodeTest extends ObjectTest<BaseNode> {
	
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
		expect(result.getRefAttributes().getElementCount()).isEqualTo(0);
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
		expect(result.getRefAttributes().getElementCount()).isEqualTo(1);
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
		expect(result.getRefAttributes().getElementCount()).isEqualTo(3);
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
		expect(result.getRefAttributes().getElementCount()).isEqualTo(1);
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
		expect(result.getRefAttributes().getElementCount()).isEqualTo(2);
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
		expect(result.getRefAttributes().getElementCount()).isEqualTo(2);
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
		expect(result.getRefAttributes().getElementCount()).isEqualTo(0);
		expect(result.toString()).isEqualTo("");
	}

	//method
	@TestCase
	public void testCase_getRefAttributeAt() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.addAttribute(Node.withHeader("a"), Node.withHeader("b"), Node.withHeader("c"));
		
		//execution
		final var result1 = testUnit.getRefAttributeAt(1);
		final var result2 = testUnit.getRefAttributeAt(2);
		final var result3 = testUnit.getRefAttributeAt(3);
		
		//verification part 1
		expect(result1.toString()).isEqualTo("a");
		expect(result2.toString()).isEqualTo("b");
		expect(result3.toString()).isEqualTo("c");
		
		//verification part 2
		expectRunning(() -> testUnit.getRefAttributeAt(-1)).throwsException().ofType(NonPositiveArgumentException.class);
		expectRunning(() -> testUnit.getRefAttributeAt(0)).throwsException().ofType(NonPositiveArgumentException.class);
		expectRunning(() -> testUnit.getRefAttributeAt(4)).throwsException().ofType(InvalidArgumentException.class);
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
		testUnit.addAttribute(Node.withHeader("a"));
		
		//execution
		final var result = testUnit.getRefOneAttribute();
		
		
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
		expectRunning(testUnit::getRefOneAttribute).throwsException().ofType(InvalidArgumentException.class);
	}
	
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
		expect(testUnit.getRefAttributes().getElementCount()).isEqualTo(0);
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
		expect(testUnit.getRefAttributes().getElementCount()).isEqualTo(0);
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
		expect(testUnit.getRefAttributes().getElementCount()).isEqualTo(0);
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
		expect(testUnit.getRefAttributes().getElementCount()).isEqualTo(1);
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
		expect(testUnit.getRefAttributes().getElementCount()).isEqualTo(3);
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
		expect(testUnit.getRefAttributes().getElementCount()).isEqualTo(1);
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
		expect(testUnit.getRefAttributes().getElementCount()).isEqualTo(2);
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
		expect(testUnit.getRefAttributes().getElementCount()).isEqualTo(2);
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
	public void testCase_toString_1() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.addAttribute(Node.withHeader("a"), Node.withHeader("b"), Node.withHeader("c"));
		
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
