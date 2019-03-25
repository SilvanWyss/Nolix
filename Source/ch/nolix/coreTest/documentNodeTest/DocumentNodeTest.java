//package declaration
package ch.nolix.coreTest.documentNodeTest;

//own import
import ch.nolix.core.documentNode.DocumentNode;

//test class
/**
 * A {@link DocumentNodeTest} is a test for {@link DocumentNode}.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 */
public final class DocumentNodeTest extends DocumentNodeoidTest {
	
	//method
	@Override
	protected DocumentNode createTestObject() {
		return new DocumentNode();
	}
}
