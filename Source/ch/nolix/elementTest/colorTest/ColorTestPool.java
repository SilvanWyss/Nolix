//package declaration
package ch.nolix.elementTest.colorTest;

//own import
import ch.nolix.core.testoid.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 20
 */
public final class ColorTestPool extends TestPool {

	//constructor
	/**
	 * Creates new color test pool.
	 */
	public ColorTestPool() {
		addTest(new ColorTest());
	}
}
