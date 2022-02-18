//package declaration
package ch.nolix.coretest.documenttest.nodetest;

import ch.nolix.core.document.node.Node;

//class
/**
 * A {@link NodeTest} is a test for {@link Node}.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public final class NodeTest extends BaseNodeTest {
	
	//method
	@Override
	protected Node createTestUnit() {
		return new Node();
	}
}
