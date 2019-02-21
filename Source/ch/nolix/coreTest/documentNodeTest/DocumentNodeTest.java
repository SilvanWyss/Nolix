//package declaration
package ch.nolix.coreTest.documentNodeTest;

//own imports
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.test.Test;

//test class
/**
 * A {@link DocumentNodeTest} is a test for {@link DocumentNode}.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 110
 */
public final class DocumentNodeTest extends Test {
	
	//test case
	public void testCase_constructor_1() {
		
		//execution
		final var documentNode = new DocumentNode();
				
		//verification
		expectNot(documentNode.hasHeader());
		expect(documentNode.getRefAttributes().getSize()).isEqualTo(0);
		expect(documentNode.toString()).isEqualTo("");
	}
	
	//test case
	public void testCase_constructor_2() {
		
		//execution
		final var documentNode = new DocumentNode("");
				
		//verification
		expectNot(documentNode.hasHeader());
		expect(documentNode.getRefAttributes().getSize()).isEqualTo(0);
		expect(documentNode.toString()).isEqualTo("");
	}
	
	//test case
	public void testCase_constructor_3() {
		
		//execution
		final var documentNode = new DocumentNode("A");
				
		//verification
		expect(documentNode.hasHeader());
		expect(documentNode.getRefAttributes().getSize()).isEqualTo(0);
		expect(documentNode.toString()).isEqualTo("A");
	}
	
	//test case
	public void testCase_constructor_4() {
		
		//execution
		final var documentNode = new DocumentNode("A(B)");
				
		//verification
		expect(documentNode.hasHeader());
		expect(documentNode.getRefAttributes().getSize()).isEqualTo(1);
		expect(documentNode.toString()).isEqualTo("A(B)");
	}
	
	//test case
	public void testCase_constructor_5() {
		
		//execution
		final var documentNode = new DocumentNode("A(B1,B2,B3)");
				
		//verification
		expect(documentNode.hasHeader());
		expect(documentNode.getRefAttributes().getSize()).isEqualTo(3);
		expect(documentNode.toString()).isEqualTo("A(B1,B2,B3)");
	}
	
	//test case
	public void testCase_constructor_6() {
		
		//execution
		final var documentNode = new DocumentNode("A(B(C))");
				
		//verification
		expect(documentNode.hasHeader());
		expect(documentNode.getRefAttributes().getSize()).isEqualTo(1);
		expect(documentNode.toString()).isEqualTo("A(B(C))");
	}
	
	//test case
	public void testCase_constructor_7() {
		
		//execution
		final var documentNode = new DocumentNode("A(B1(C1),B2(C2))");
				
		//verification
		expect(documentNode.hasHeader());
		expect(documentNode.getRefAttributes().getSize()).isEqualTo(2);
		expect(documentNode.toString()).isEqualTo("A(B1(C1),B2(C2))");
	}
	
	//test case
	public void testCase_constructor_8() {
		
		//execution
		final var documentNode = new DocumentNode("A(B1(C1,C2),B2(C3,C4))");
				
		//verification
		expect(documentNode.hasHeader());
		expect(documentNode.getRefAttributes().getSize()).isEqualTo(2);
		expect(documentNode.toString()).isEqualTo("A(B1(C1,C2),B2(C3,C4))");
	}
}
