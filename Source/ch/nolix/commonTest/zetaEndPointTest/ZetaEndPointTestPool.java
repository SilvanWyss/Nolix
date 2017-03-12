//package declaration
package ch.nolix.commonTest.zetaEndPointTest;

//own import
import ch.nolix.common.test.TestPool;

//class
/**
 * @author Silvan
 * @month 2017-02
 * @lines 20
 */
public final class ZetaEndPointTestPool extends TestPool {

	//constructor
	/**
	 * Creates new alpha end point test pool.
	 */
	public ZetaEndPointTestPool() {
		addTest(new ZetaEndPointTest());
	}
}
