//package declaration
package ch.nolix.commontest.nodetest;

//own import
import ch.nolix.common.node.Node;

//class
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
	protected Node createTestUnit() {
		return new Node();
	}
}
