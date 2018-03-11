//package declaration
package ch.nolix.elementTest.dataTest;

import ch.nolix.primitive.testoid.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 20
 */
public final class DataTestPool extends TestPool {

	//constructor
	/**
	 * Creates new data test pool.
	 */
	public DataTestPool() {
		addTest(
			new TitleTest(),
			new WidthTest()
		);
	}
}
