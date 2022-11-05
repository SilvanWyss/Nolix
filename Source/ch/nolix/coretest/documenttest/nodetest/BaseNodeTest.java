//package declaration
package ch.nolix.coretest.documenttest.nodetest;

//own imports
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public abstract class BaseNodeTest<BN extends BaseNode<BN>> extends Test {
	
	//method
	@TestCase
	public void testCase_getRefSingleChildNode_whenTheNodeDoesNotContainChildNodes() {
		
		//setup
		final var testUnit = createNodeWithHeaderAndChildNodes("a");
		
		//execution & verification
		expectRunning(testUnit::getRefSingleChildNode).throwsException().ofType(InvalidArgumentException.class);
	}
	
	//method declaration
	protected abstract BN createBlankNode();
	
	//method declaration
	protected abstract BN createNodeWithHeaderAndChildNodes(String header, String... childNodeHeaders);
}
