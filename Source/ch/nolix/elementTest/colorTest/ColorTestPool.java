//package declaration
package ch.nolix.elementTest.colorTest;

import ch.nolix.primitive.testoid.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 20
 */
public final class ColorTestPool extends TestPool {

	//constructor
	/**
	 * Creates a new color test pool.
	 */
	public ColorTestPool() {
		addTestClass(ColorTest.class);
	}
}
