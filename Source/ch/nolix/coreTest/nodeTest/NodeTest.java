//package declaration
package ch.nolix.coreTest.nodeTest;

//own import
import ch.nolix.core.node.Node;

//test class
/**
 * A {@link NodeTest} is a test for {@link Node}.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 */
public final class NodeTest extends BaseNodeTest {
	
	//method
	@Override
	protected Node createTestObject() {
		return new Node();
	}
}
