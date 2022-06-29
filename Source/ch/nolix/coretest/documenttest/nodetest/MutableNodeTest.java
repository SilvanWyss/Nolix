//package declaration
package ch.nolix.coretest.documenttest.nodetest;

//own imports
import ch.nolix.core.document.node.MutableNode;

//class
public final class MutableNodeTest extends BaseMutableNodeTest<MutableNode> {
	
	//method
	@Override
	protected MutableNode createTestUnit() {
		return new MutableNode();
	}
}
