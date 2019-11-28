//package declaration
package ch.nolix.commonTest.nodeTest;

//own imports
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.invalidArgumentExceptions.NonPositiveArgumentException;
import ch.nolix.common.invalidArgumentExceptions.UnrepresentingArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.test.ObjectTest;

//abstract test class
public abstract class BaseNodeTest extends ObjectTest<BaseNode> {
	
	//test case
	public void testCase_getCopy_1A() {
		
		//setup
		final var testUnit = createTestObject();
		
		//execution
		final var result = testUnit.getCopy();
		
		//verification
		expectNot(result.hasHeader());
		expect(result.getRefAttributes().getSize()).isEqualTo(0);
		expect(result.toString()).isEqualTo("");
	}
	
	//test case
	public void testCase_copy_1B() {
		
		//setup
		final var testUnit = createTestObject();testUnit.reset("");
		
		//execution
		final var result = testUnit.getCopy();
		
		//verification
		expectNot(result.hasHeader());
		expect(result.getRefAttributes().getSize()).isEqualTo(0);
		expect(result.toString()).isEqualTo("");
	}
	
	//test case
	public void testCase_copy_2A() {
		
		//setup
		final var testUnit = createTestObject();
		testUnit.reset("a");
		
		//execution
		final var result = testUnit.getCopy();
		
		//verification
		expect(result.hasHeader());
		expect(result.getRefAttributes().getSize()).isEqualTo(0);
		expect(result.toString()).isEqualTo("a");
	}
	
	//test case
	public void testCase_copy_2B() {
		
		//setup
		final var testUnit = createTestObject();
		testUnit.reset("a(b)");
		
		//execution
		final var result = testUnit.getCopy();
		
		//verification
		expect(result.hasHeader());
		expect(result.getRefAttributes().getSize()).isEqualTo(1);
		expect(result.toString()).isEqualTo("a(b)");
	}
	
	//test case
	public void testCase_copy_2C() {
		
		//setup
		final var testUnit = createTestObject();
		testUnit.reset("a(b,c,d)");
		
		//execution
		final var result = testUnit.getCopy();
		
		//verification
		expect(result.hasHeader());
		expect(result.getRefAttributes().getSize()).isEqualTo(3);
		expect(result.toString()).isEqualTo("a(b,c,d)");
	}
	
	//test case
	public void testCase_copy_2D() {
		
		//setup
		final var testUnit = createTestObject();
		testUnit.reset("a(b(c))");
		
		//execution
		final var result = testUnit.getCopy();
		
		//verification
		expect(result.hasHeader());
		expect(result.getRefAttributes().getSize()).isEqualTo(1);
		expect(result.toString()).isEqualTo("a(b(c))");
	}
	
	//test case
	public void testCase_copy_3A() {
		
		//setup
		final var testUnit = createTestObject();
		testUnit.reset("a(b(c),d(e))");
		
		//execution
		final var result = testUnit.getCopy();
		
		//verification
		expect(result.hasHeader());
		expect(result.getRefAttributes().getSize()).isEqualTo(2);
		expect(result.toString()).isEqualTo("a(b(c),d(e))");
	}
	
	//test case
	public void testCase_copy_3B() {
		
		//setup
		final var testUnit = createTestObject();
		testUnit.reset("a(b(c,d),e(f,g))");
		
		//execution
		final var result = testUnit.getCopy();
		
		//verification
		expect(result.hasHeader());
		expect(result.getRefAttributes().getSize()).isEqualTo(2);
		expect(result.toString()).isEqualTo("a(b(c,d),e(f,g))");
	}
	
	//test case
	public void testCase_getRefAttributeAt() {
		
		//setup
		final var testUnit = createTestObject();
		testUnit.addAttribute(new Node("a"), new Node("b"), new Node("c"));
		
		//execution
		final var result1 = testUnit.getRefAttributeAt(1);
		final var result2 = testUnit.getRefAttributeAt(2);
		final var result3 = testUnit.getRefAttributeAt(3);
		
		//verification part 1
		expect(result1.toString()).isEqualTo("a");
		expect(result2.toString()).isEqualTo("b");
		expect(result3.toString()).isEqualTo("c");
		
		//verification part 2
		expect(() -> testUnit.getRefAttributeAt(-1)).throwsException().ofType(NonPositiveArgumentException.class);
		expect(() -> testUnit.getRefAttributeAt(0)).throwsException().ofType(NonPositiveArgumentException.class);
		expect(() -> testUnit.getRefAttributeAt(4)).throwsException().ofType(InvalidArgumentException.class);
	}
	
	//test case
	public void testCase_getHeader_1A() {
		
		//setup
		final var testUnit = createTestObject();
		testUnit.setHeader("Lorem(");
		
		//execution
		final var result = testUnit.getHeader();
		
		//verification
		expect(result).isEqualTo("Lorem(");
	}
	
	//test case
	public void testCase_getHeader_1B() {
		
		//setup
		final var testUnit = createTestObject();
		testUnit.setHeader("Lorem)");
		
		//execution
		final var result = testUnit.getHeader();
		
		//verification
		expect(result).isEqualTo("Lorem)");
	}
	
	//test case
	public void testCase_getHeader_1C() {
		
		//setup
		final var testUnit = createTestObject();
		testUnit.setHeader("Lorem.");
		
		//execution
		final var result = testUnit.getHeader();
		
		//verification
		expect(result).isEqualTo("Lorem.");
	}
	
	//test case
	public void testCase_getHeader_1D() {
		
		//setup
		final var testUnit = createTestObject();
		testUnit.setHeader("Lorem,");
		
		//execution
		final var result = testUnit.getHeader();
		
		//verification
		expect(result).isEqualTo("Lorem,");
	}
	
	//test case
	public void testCase_getRefOneAttribute_1A() {
		
		//setup
		final var testUnit = createTestObject();
		testUnit.addAttribute(new Node("a"));
		
		//execution
		final var result = testUnit.getRefOneAttribute();
		
		
		//verification
		expect(result.toString()).isEqualTo("a");
	}
	
	//test case
	public void testCase_getRefOneAttribute_1B() {
		
		//setup
		final var testUnit = createTestObject();
		testUnit.reset();
		
		//execution
		expect(() -> testUnit.getRefOneAttribute()).throwsException().ofType(InvalidArgumentException.class);
	}
	
	//test case
	public void testCase_removeHeader() {
		
		//setup
		final var testUnit = createTestObject();
		testUnit.setHeader("Lorem");
		
		//setup verification
		expect(testUnit.hasHeader());
		
		//execution
		testUnit.removeHeader();
		
		//verification
		expectNot(testUnit.hasHeader());
	}
	
	//test case
	public void testCase_reset_1A() {
		
		//setup
		final var testUnit = createTestObject();
		
		//execution
		testUnit.reset();
		
		//verification
		expectNot(testUnit.hasHeader());
		expect(testUnit.getRefAttributes().getSize()).isEqualTo(0);
		expect(testUnit.toString()).isEqualTo("");
	}
	
	//test case
	public void testCase_reset_1B() {
		
		//setup
		final var testUnit = createTestObject();
		
		//execution
		testUnit.reset("");
		
		//verification
		expectNot(testUnit.hasHeader());
		expect(testUnit.getRefAttributes().getSize()).isEqualTo(0);
		expect(testUnit.toString()).isEqualTo("");
	}
	
	//test case
	public void testCase_reset_2A() {
		
		//setup
		final var testUnit = createTestObject();
		
		//execution
		testUnit.reset("a");
		
		//verification
		expect(testUnit.hasHeader());
		expect(testUnit.getRefAttributes().getSize()).isEqualTo(0);
		expect(testUnit.toString()).isEqualTo("a");
	}
	
	//test case
	public void testCase_reset_2B() {
		
		//setup
		final var testUnit = createTestObject();
		
		//execution
		testUnit.reset("a(b)");
		
		//verification
		expect(testUnit.hasHeader());
		expect(testUnit.getRefAttributes().getSize()).isEqualTo(1);
		expect(testUnit.toString()).isEqualTo("a(b)");
	}
	
	//test case
	public void testCase_reset_2C() {
		
		//setup
		final var testUnit = createTestObject();
		
		//execution
		testUnit.reset("a(b,c,d)");
		
		//verification
		expect(testUnit.hasHeader());
		expect(testUnit.getRefAttributes().getSize()).isEqualTo(3);
		expect(testUnit.toString()).isEqualTo("a(b,c,d)");
	}
	
	//test case
	public void testCase_reset_2D() {
		
		//setup
		final var testUnit = createTestObject();
		
		//execution
		testUnit.reset("a(b(c))");
		
		//verification
		expect(testUnit.hasHeader());
		expect(testUnit.getRefAttributes().getSize()).isEqualTo(1);
		expect(testUnit.toString()).isEqualTo("a(b(c))");
	}
	
	//test case
	public void testCase_reset_3A() {
		
		//setup
		final var testUnit = createTestObject();
		
		//execution
		testUnit.reset("a(b(c),d(e))");
		
		//verification
		expect(testUnit.hasHeader());
		expect(testUnit.getRefAttributes().getSize()).isEqualTo(2);
		expect(testUnit.toString()).isEqualTo("a(b(c),d(e))");
	}
	
	//test case
	public void testCase_reset_3B() {
		
		//setup
		final var testUnit = createTestObject();
		
		//execution
		testUnit.reset("a(b(c,d),e(f,g))");
		
		//verification
		expect(testUnit.hasHeader());
		expect(testUnit.getRefAttributes().getSize()).isEqualTo(2);
		expect(testUnit.toString()).isEqualTo("a(b(c,d),e(f,g))");
	}
	
	//test case
	public void testCase_reset_whenTheGivenStringIsNotValid() {
		
		//setup
		final var testUnit = createTestObject();
		
		//execution & verification
		expect(() -> testUnit.reset("a(b).c"))
		.throwsException()
		.ofType(UnrepresentingArgumentException.class);
	}
	
	//test case
	public void testCase_setHeader() {
		
		//setup
		final var testUnit = createTestObject();
		testUnit.setHeader("Lorem");
		
		//setup verification
		expect(testUnit.hasHeader());
		
		//execution
		testUnit.setHeader("Ipsum");
		
		//verification
		expect(testUnit.getHeader()).isEqualTo("Ipsum");
	}
	
	//test case
	public void testCase_toString_1() {
		
		//setup
		final var testUnit = createTestObject();
		testUnit.addAttribute(new Node("a"), new Node("b"), new Node("c"));
		
		//execution
		final var result = testUnit.toString();
		
		//verification
		expect(result).isEqualTo("(a,b,c)");
	}
	
	//test case
	public void testCase_toString_2A() {
		
		//setup
		final var testUnit = createTestObject();
		testUnit.setHeader("Lorem(");
		
		//execution
		final var result = testUnit.toString();
		
		//verification
		expect(result).isEqualTo("Lorem$O");
	}
	
	//test case
	public void testCase_toString_2B() {
		
		//setup
		final var testUnit = createTestObject();
		testUnit.setHeader("Lorem)");
		
		//execution
		final var result = testUnit.toString();
		
		//verification
		expect(result).isEqualTo("Lorem$C");
	}
	
	//test case
	public void testCase_toString_2C() {
		
		//setup
		final var testUnit = createTestObject();
		testUnit.setHeader("Lorem.");
		
		//execution
		final var result = testUnit.toString();
		
		//verification
		expect(result).isEqualTo("Lorem$D");
	}
	
	//test case
	public void testCase_toString_2D() {
		
		//setup
		final var testUnit = createTestObject();
		testUnit.setHeader("Lorem,");
		
		//execution
		final var result = testUnit.toString();
		
		//verification
		expect(result).isEqualTo("Lorem$M");
	}
}
