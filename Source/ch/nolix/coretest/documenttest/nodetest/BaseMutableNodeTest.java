//package declaration
package ch.nolix.coretest.documenttest.nodetest;

//own imports
import ch.nolix.core.document.node.BaseMutableNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.testing.basetest.TestCase;

//class
public abstract class BaseMutableNodeTest<MN extends BaseMutableNode<MN>> extends BaseNodeTest<MN> {
	
	//method
	@TestCase
	public void testCase_removeHeader() {
		
		//setup
		final var testUnit = createBlankNode();
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
	public void testCase_resetFromString_1A() {
		
		//setup
		final var testUnit = createBlankNode();
		
		//execution
		testUnit.reset();
		
		//verification
		expectNot(testUnit.hasHeader());
		expect(testUnit.getStoredChildNodes().getElementCount()).isEqualTo(0);
		expect(testUnit.toString()).isEqualTo("");
	}
	
	//method
	@TestCase
	public void testCase_resetFromString_1B() {
		
		//setup
		final var testUnit = createBlankNode();
		
		//execution
		testUnit.resetFromString("");
		
		//verification
		expectNot(testUnit.hasHeader());
		expect(testUnit.getStoredChildNodes().getElementCount()).isEqualTo(0);
		expect(testUnit.toString()).isEqualTo("");
	}
	
	//method
	@TestCase
	public void testCase_resetFromString_2A() {
		
		//setup
		final var testUnit = createBlankNode();
		
		//execution
		testUnit.resetFromString("a");
		
		//verification
		expect(testUnit.hasHeader());
		expect(testUnit.getStoredChildNodes().getElementCount()).isEqualTo(0);
		expect(testUnit.toString()).isEqualTo("a");
	}
	
	//method
	@TestCase
	public void testCase_resetFromString_2B() {
		
		//setup
		final var testUnit = createBlankNode();
		
		//execution
		testUnit.resetFromString("a(b)");
		
		//verification
		expect(testUnit.hasHeader());
		expect(testUnit.getStoredChildNodes().getElementCount()).isEqualTo(1);
		expect(testUnit.toString()).isEqualTo("a(b)");
	}
	
	//method
	@TestCase
	public void testCase_resetFromString_2C() {
		
		//setup
		final var testUnit = createBlankNode();
		
		//execution
		testUnit.resetFromString("a(b,c,d)");
		
		//verification
		expect(testUnit.hasHeader());
		expect(testUnit.getStoredChildNodes().getElementCount()).isEqualTo(3);
		expect(testUnit.toString()).isEqualTo("a(b,c,d)");
	}
	
	//method
	@TestCase
	public void testCase_resetFromString_2D() {
		
		//setup
		final var testUnit = createBlankNode();
		
		//execution
		testUnit.resetFromString("a(b(c))");
		
		//verification
		expect(testUnit.hasHeader());
		expect(testUnit.getStoredChildNodes().getElementCount()).isEqualTo(1);
		expect(testUnit.toString()).isEqualTo("a(b(c))");
	}
	
	//method
	@TestCase
	public void testCase_resetFromString_3A() {
		
		//setup
		final var testUnit = createBlankNode();
		
		//execution
		testUnit.resetFromString("a(b(c),d(e))");
		
		//verification
		expect(testUnit.hasHeader());
		expect(testUnit.getStoredChildNodes().getElementCount()).isEqualTo(2);
		expect(testUnit.toString()).isEqualTo("a(b(c),d(e))");
	}
	
	//method
	@TestCase
	public void testCase_resetFromString_3B() {
		
		//setup
		final var testUnit = createBlankNode();
		
		//execution
		testUnit.resetFromString("a(b(c,d),e(f,g))");
		
		//verification
		expect(testUnit.hasHeader());
		expect(testUnit.getStoredChildNodes().getElementCount()).isEqualTo(2);
		expect(testUnit.toString()).isEqualTo("a(b(c,d),e(f,g))");
	}
	
	//method
	@TestCase
	public void testCase_resetFromString_whenTheGivenStringIsNotValid() {
		
		//setup
		final var testUnit = createBlankNode();
		
		//execution & verification
		expectRunning(() -> testUnit.resetFromString("a(b).c"))
		.throwsException()
		.ofType(UnrepresentingArgumentException.class);
	}
	
	//method
	@TestCase
	public void testCase_setHeader() {
		
		//setup
		final var testUnit = createBlankNode();
		testUnit.setHeader("Lorem");
		
		//setup verification
		expect(testUnit.hasHeader());
		
		//execution
		testUnit.setHeader("Ipsum");
		
		//verification
		expect(testUnit.getHeader()).isEqualTo("Ipsum");
	}
}
