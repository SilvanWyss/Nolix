//package declaration
package ch.nolix.commonTest.endPointTest;

//own import
import ch.nolix.common.baseTest.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 20
 */
public final class EndPointTestPool extends TestPool {
	
	//constructor
	/**
	 * Creates a new {@link EndPointTestPool}.
	 */
	public EndPointTestPool() {
		super(
			NetServerTest.class,
			NetEndPointTest.class
		);
	}
}
