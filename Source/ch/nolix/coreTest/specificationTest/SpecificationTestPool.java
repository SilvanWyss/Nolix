//package declaration
package ch.nolix.coreTest.specificationTest;

//own import
import ch.nolix.core.testBase.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 20
 */
public class SpecificationTestPool extends TestPool {

	//constructor
	/**
	 * Creates new specification test pool.
	 */
	public SpecificationTestPool() {
		addTest(
			new SpecificationTest(),
			new StatementTest()
		);
	}
}
