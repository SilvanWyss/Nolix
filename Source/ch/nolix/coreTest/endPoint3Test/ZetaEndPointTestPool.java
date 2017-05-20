//package declaration
package ch.nolix.coreTest.endPoint3Test;

//own import
import ch.nolix.core.testBase.TestPool;

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
