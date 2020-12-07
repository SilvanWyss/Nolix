//package declaration
package ch.nolix.commontest.nodetest;

//own imports
import ch.nolix.common.basetest.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 20
 */
public final class NodeTestPool extends TestPool {
	
	//constructor
	/**
	 * Creates a new {@link NodeTestPool}.
	 */
	public NodeTestPool() {
		super(NodeTest.class);
	}
}
