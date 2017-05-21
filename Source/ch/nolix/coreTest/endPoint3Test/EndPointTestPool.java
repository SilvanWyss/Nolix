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
public final class EndPointTestPool extends TestPool {

	//constructor
	/**
	 * Creates new end point test pool.
	 */
	public EndPointTestPool() {
		addTest(new EndPointTest());
	}
}
