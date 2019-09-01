//package declaration
package ch.nolix.coreTest.nodeTest;

import ch.nolix.common.invalidArgumentExceptions.NonRepresentingArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.test.ObjectTest;

//abstract test class
public abstract class BaseNodeTest extends ObjectTest<BaseNode> {
	
	//test case
	public void testCase_getCopy() {
		
		//setup
		final var node = createTestObject();
		
		//execution
		final var copy = node.getCopy();
		
		//verification
		expectNot(copy.hasHeader());
		expect(copy.getRefAttributes().getSize()).isEqualTo(0);
		expect(copy.toString()).isEqualTo("");
	}
	
	//test case
	public void testCase_copy_2() {
		
		//setup
		final var node = createTestObject();node.reset("");
		
		//execution
		final var copy = node.getCopy();
		
		//verification
		expectNot(copy.hasHeader());
		expect(copy.getRefAttributes().getSize()).isEqualTo(0);
		expect(copy.toString()).isEqualTo("");
	}
	
	//test case
	public void testCase_copy_3() {
		
		//setup
		final var node = createTestObject();
		node.reset("A");
		
		//execution
		final var copy = node.getCopy();
		
		//verification
		expect(copy.hasHeader());
		expect(copy.getRefAttributes().getSize()).isEqualTo(0);
		expect(copy.toString()).isEqualTo("A");
	}
	
	//test case
	public void testCase_copy_4() {
		
		//setup
		final var node = createTestObject();
		node.reset("A(B)");
		
		//execution
		final var copy = node.getCopy();
		
		//verification
		expect(copy.hasHeader());
		expect(copy.getRefAttributes().getSize()).isEqualTo(1);
		expect(copy.toString()).isEqualTo("A(B)");
	}
	
	//test case
	public void testCase_copy_5() {
		
		//setup
		final var node = createTestObject();
		node.reset("A(B1,B2,B3)");
		
		//execution
		final var copy = node.getCopy();
		
		//verification
		expect(copy.hasHeader());
		expect(copy.getRefAttributes().getSize()).isEqualTo(3);
		expect(copy.toString()).isEqualTo("A(B1,B2,B3)");
	}
	
	//test case
	public void testCase_copy_6() {
		
		//setup
		final var node = createTestObject();
		node.reset("A(B(C))");
		
		//execution
		final var copy = node.getCopy();
		
		//verification
		expect(copy.hasHeader());
		expect(copy.getRefAttributes().getSize()).isEqualTo(1);
		expect(copy.toString()).isEqualTo("A(B(C))");
	}
	
	//test case
	public void testCase_copy_7() {
		
		//setup
		final var node = createTestObject();
		node.reset("A(B1(C1),B2(C2))");
		
		//execution
		final var copy = node.getCopy();
		
		//verification
		expect(copy.hasHeader());
		expect(copy.getRefAttributes().getSize()).isEqualTo(2);
		expect(copy.toString()).isEqualTo("A(B1(C1),B2(C2))");
	}
	
	//test case
	public void testCase_copy_8() {
		
		//setup
		final var node = createTestObject();
		node.reset("A(B1(C1,C2),B2(C3,C4))");
		
		//execution
		final var copy = node.getCopy();
		
		//verification
		expect(copy.hasHeader());
		expect(copy.getRefAttributes().getSize()).isEqualTo(2);
		expect(copy.toString()).isEqualTo("A(B1(C1,C2),B2(C3,C4))");
	}
	
	//test case
	public void testCase_removeHeader() {
		
		//setup
		final var node = createTestObject();
		node.setHeader("Lorem");
		
		//setup verification
		expect(node.hasHeader());
		
		//execution
		node.removeHeader();
		
		//verification
		expectNot(node.hasHeader());
	}
	
	//test case
	public void testCase_reset() {
		
		//setup
		final var node = createTestObject();
		
		//execution
		node.reset();
		
		//verification
		expectNot(node.hasHeader());
		expect(node.getRefAttributes().getSize()).isEqualTo(0);
		expect(node.toString()).isEqualTo("");
	}
	
	//test case
	public void testCase_reset_2() {
		
		//setup
		final var node = createTestObject();
		
		//execution
		node.reset("");
		
		//verification
		expectNot(node.hasHeader());
		expect(node.getRefAttributes().getSize()).isEqualTo(0);
		expect(node.toString()).isEqualTo("");
	}
	
	//test case
	public void testCase_reset_3() {
		
		//setup
		final var node = createTestObject();
		
		//execution
		node.reset("A");
		
		//verification
		expect(node.hasHeader());
		expect(node.getRefAttributes().getSize()).isEqualTo(0);
		expect(node.toString()).isEqualTo("A");
	}
	
	//test case
	public void testCase_reset_4() {
		
		//setup
		final var node = createTestObject();
		
		//execution
		node.reset("A(B)");
		
		//verification
		expect(node.hasHeader());
		expect(node.getRefAttributes().getSize()).isEqualTo(1);
		expect(node.toString()).isEqualTo("A(B)");
	}
	
	//test case
	public void testCase_reset_5() {
		
		//setup
		final var node = createTestObject();
		
		//execution
		node.reset("A(B1,B2,B3)");
		
		//verification
		expect(node.hasHeader());
		expect(node.getRefAttributes().getSize()).isEqualTo(3);
		expect(node.toString()).isEqualTo("A(B1,B2,B3)");
	}
	
	//test case
	public void testCase_reset_6() {
		
		//setup
		final var node = createTestObject();
		
		//execution
		node.reset("A(B(C))");
		
		//verification
		expect(node.hasHeader());
		expect(node.getRefAttributes().getSize()).isEqualTo(1);
		expect(node.toString()).isEqualTo("A(B(C))");
	}
	
	//test case
	public void testCase_reset_7() {
		
		//setup
		final var node = createTestObject();
		
		//execution
		node.reset("A(B1(C1),B2(C2))");
		
		//verification
		expect(node.hasHeader());
		expect(node.getRefAttributes().getSize()).isEqualTo(2);
		expect(node.toString()).isEqualTo("A(B1(C1),B2(C2))");
	}
	
	//test case
	public void testCase_reset_8() {
		
		//setup
		final var node = createTestObject();
		
		//execution
		node.reset("A(B1(C1,C2),B2(C3,C4))");
		
		//verification
		expect(node.hasHeader());
		expect(node.getRefAttributes().getSize()).isEqualTo(2);
		expect(node.toString()).isEqualTo("A(B1(C1,C2),B2(C3,C4))");
	}

	//test case
	public void testCase_reset_whenTheGivenStringIsNotValid() {
		
		//setup
		final var node = createTestObject();
		
		//execution & verification
		expect(() -> node.reset("A(B).C"))
		.throwsException()
		.ofType(NonRepresentingArgumentException.class);
	}
	
	//test case
	public void testCase_setHeader() {
		
		//setup
		final var node = createTestObject();
		node.setHeader("Lorem");
		
		//setup verification
		expect(node.hasHeader());
		
		//execution
		node.setHeader("Ipsum");
		
		//verification
		expect(node.getHeader()).isEqualTo("Ipsum");
	}
	
	//test case
	public void testCase_toString() {
		
		//setup
		final var node = createTestObject();
		node.setHeader("Lorem(");
		
		//execution & verification
		expect(node.toString()).isEqualTo("Lorem$O");
	}
	
	//test case
	public void testCase_toString_2() {
		
		//setup
		final var node = createTestObject();
		node.setHeader("Lorem,");
		
		//execution & verification
		expect(node.toString()).isEqualTo("Lorem$M");
	}
}
