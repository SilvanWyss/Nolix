//package declaration
package ch.nolix.coreTest.documentNodeTest;

//own imports
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.test.ObjectTest;

//abstract test class
public abstract class DocumentNodeoidTest extends ObjectTest<DocumentNodeoid> {
	
	//test case
	public void testCase_reset_8() {
		
		//execution
		final var documentNode = createTestObject();
		documentNode.reset("A(B1(C1,C2),B2(C3,C4))");
		
		//verification
		expect(documentNode.hasHeader());
		expect(documentNode.getRefAttributes().getSize()).isEqualTo(2);
		expect(documentNode.toString()).isEqualTo("A(B1(C1,C2),B2(C3,C4))");
	}
	
	//test case
	public void testCase_getCopy() {
		
		//setup
		final var documentNode = createTestObject();
		createTestObject();
		
		//execution
		final var copy = documentNode.getCopy();
		
		//verification
		expectNot(copy.hasHeader());
		expect(copy.getRefAttributes().getSize()).isEqualTo(0);
		expect(copy.toString()).isEqualTo("");
	}
	
	//test case
	public void testCase_copy_2() {
		
		//setup
		final var documentNode = 
				createTestObject();documentNode.reset("");
		
		//execution
		final var copy = documentNode.getCopy();
		
		//verification
		expectNot(copy.hasHeader());
		expect(copy.getRefAttributes().getSize()).isEqualTo(0);
		expect(copy.toString()).isEqualTo("");
	}
	
	//test case
	public void testCase_copy_3() {
		
		//setup
		final var documentNode = createTestObject();
		documentNode.reset("A");
		
		//execution
		final var copy = documentNode.getCopy();
		
		//verification
		expect(copy.hasHeader());
		expect(copy.getRefAttributes().getSize()).isEqualTo(0);
		expect(copy.toString()).isEqualTo("A");
	}
	
	//test case
	public void testCase_copy_4() {
		
		//setup
		final var documentNode = createTestObject();
		documentNode.reset("A(B)");
		
		//execution
		final var copy = documentNode.getCopy();
		
		//verification
		expect(copy.hasHeader());
		expect(copy.getRefAttributes().getSize()).isEqualTo(1);
		expect(copy.toString()).isEqualTo("A(B)");
	}
	
	//test case
	public void testCase_copy_5() {
		
		//setup
		final var documentNode = createTestObject();
		documentNode.reset("A(B1,B2,B3)");
		
		//execution
		final var copy = documentNode.getCopy();
		
		//verification
		expect(copy.hasHeader());
		expect(copy.getRefAttributes().getSize()).isEqualTo(3);
		expect(copy.toString()).isEqualTo("A(B1,B2,B3)");
	}
	
	//test case
	public void testCase_copy_6() {
		
		//setup
		final var documentNode = createTestObject();
		documentNode.reset("A(B(C))");
		
		//execution
		final var copy = documentNode.getCopy();
		
		//verification
		expect(copy.hasHeader());
		expect(copy.getRefAttributes().getSize()).isEqualTo(1);
		expect(copy.toString()).isEqualTo("A(B(C))");
	}
	
	//test case
	public void testCase_copy_7() {
		
		//setup
		final var documentNode = createTestObject();
		documentNode.reset("A(B1(C1),B2(C2))");
		
		//execution
		final var copy = documentNode.getCopy();
		
		//verification
		expect(copy.hasHeader());
		expect(copy.getRefAttributes().getSize()).isEqualTo(2);
		expect(copy.toString()).isEqualTo("A(B1(C1),B2(C2))");
	}
	
	//test case
	public void testCase_copy_8() {
		
		//setup
		final var documentNode = createTestObject();
		documentNode.reset("A(B1(C1,C2),B2(C3,C4))");
		
		//execution
		final var copy = documentNode.getCopy();
		
		//verification
		expect(copy.hasHeader());
		expect(copy.getRefAttributes().getSize()).isEqualTo(2);
		expect(copy.toString()).isEqualTo("A(B1(C1,C2),B2(C3,C4))");
	}
	
	//test case
	public void testCase_removeHeader() {
		
		//setup
		final var documentNode = createTestObject();
		documentNode.setHeader("Lorem");
		
		//setup verification
		expect(documentNode.hasHeader());
		
		//execution
		documentNode.removeHeader();
		
		//verification
		expectNot(documentNode.hasHeader());
	}
	
	//test case
	public void testCase_reset() {
		
		//execution
		final var documentNode = createTestObject();
		
		//verification
		expectNot(documentNode.hasHeader());
		expect(documentNode.getRefAttributes().getSize()).isEqualTo(0);
		expect(documentNode.toString()).isEqualTo("");
	}
	
	//test case
	public void testCase_reset_2() {
		
		//execution
		final var documentNode = createTestObject();
		documentNode.reset("");
		
		//verification
		expectNot(documentNode.hasHeader());
		expect(documentNode.getRefAttributes().getSize()).isEqualTo(0);
		expect(documentNode.toString()).isEqualTo("");
	}
	
	//test case
	public void testCase_reset_3() {
		
		//execution
		final var documentNode = createTestObject();
		documentNode.reset("A");
		
		//verification
		expect(documentNode.hasHeader());
		expect(documentNode.getRefAttributes().getSize()).isEqualTo(0);
		expect(documentNode.toString()).isEqualTo("A");
	}
	
	//test case
	public void testCase_reset_4() {
		
		//execution
		final var documentNode = createTestObject();
		documentNode.reset("A(B)");
		
		//verification
		expect(documentNode.hasHeader());
		expect(documentNode.getRefAttributes().getSize()).isEqualTo(1);
		expect(documentNode.toString()).isEqualTo("A(B)");
	}
	
	//test case
	public void testCase_reset_5() {
		
		//execution
		final var documentNode = createTestObject();
		documentNode.reset("A(B1,B2,B3)");
		
		//verification
		expect(documentNode.hasHeader());
		expect(documentNode.getRefAttributes().getSize()).isEqualTo(3);
		expect(documentNode.toString()).isEqualTo("A(B1,B2,B3)");
	}
	
	//test case
	public void testCase_reset_6() {
		
		//execution
		final var documentNode = createTestObject();
		documentNode.reset("A(B(C))");
		
		//verification
		expect(documentNode.hasHeader());
		expect(documentNode.getRefAttributes().getSize()).isEqualTo(1);
		expect(documentNode.toString()).isEqualTo("A(B(C))");
	}
	
	//test case
	public void testCase_reset_7() {
		
		//execution
		final var documentNode = createTestObject();
		documentNode.reset("A(B1(C1),B2(C2))");
		
		//verification
		expect(documentNode.hasHeader());
		expect(documentNode.getRefAttributes().getSize()).isEqualTo(2);
		expect(documentNode.toString()).isEqualTo("A(B1(C1),B2(C2))");
	}
	
	//test case
	public void testCase_setHeader() {
		
		//setup
		final var documentNode = createTestObject();
		documentNode.setHeader("Lorem");
		
		//setup verification
		expect(documentNode.hasHeader());
		
		//execution
		documentNode.setHeader("Ipsum");
		
		//verification
		expect(documentNode.getHeader()).isEqualTo("Ipsum");
	}
}
