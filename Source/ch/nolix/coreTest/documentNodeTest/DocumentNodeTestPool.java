//package declaration
package ch.nolix.coreTest.documentNodeTest;

import ch.nolix.core.baseTest.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 20
 */
public class DocumentNodeTestPool extends TestPool {

	//constructor
	/**
	 * Creates a new specification test pool.
	 */
	public DocumentNodeTestPool() {
		addTestClass(
			DocumentNodeTest.class,
			FileDocumentNodeTest.class
		);
	}
}
