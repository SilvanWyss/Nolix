//package declaration
package ch.nolix.coreTest.nodeTest;

//own import
import ch.nolix.core.baseTest.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 20
 */
public class NodeTestPool extends TestPool {

	//constructor
	/**
	 * Creates a new specification test pool.
	 */
	public NodeTestPool() {
		addTestClass(NodeTest.class);
	}
}
