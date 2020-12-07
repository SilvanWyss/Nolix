//package declaration
package ch.nolix.commontest.endpointtest;

//own imports
import ch.nolix.common.basetest.TestPool;

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
