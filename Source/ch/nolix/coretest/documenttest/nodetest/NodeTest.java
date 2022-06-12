//package declaration
package ch.nolix.coretest.documenttest.nodetest;

//own imports
import ch.nolix.core.document.node.Node;

//class
public final class NodeTest extends BaseNodeTest {
	
	//method
	@Override
	protected Node createTestUnit() {
		return new Node();
	}
}
