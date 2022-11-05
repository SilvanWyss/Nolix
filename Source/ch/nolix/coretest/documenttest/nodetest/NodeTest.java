//package declaration
package ch.nolix.coretest.documenttest.nodetest;

//own imports
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.document.node.Node;

//class
public final class NodeTest extends BaseNodeTest<Node> {
	
	//method
	@Override
	protected Node createBlankNode() {
		return Node.EMPTY_NODE;
	}
	
	//method
	@Override
	protected Node createNodeWithHeaderAndChildNodes(final String header, final String... childNodeHeaders) {
		return Node.withHeaderAndChildNodes(header, ReadContainer.forArray(childNodeHeaders).to(Node::withHeader));
	}
}
