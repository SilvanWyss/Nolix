//package declaration
package ch.nolix.coreTest.specificationTest;

import ch.nolix.primitive.testoid.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 20
 */
public class SpecificationTestPool extends TestPool {

	//constructor
	/**
	 * Creates a new specification test pool.
	 */
	public SpecificationTestPool() {
		addTest(
			new StandardSpecificationTest(),
			new StatementTest()
		);
	}
}
