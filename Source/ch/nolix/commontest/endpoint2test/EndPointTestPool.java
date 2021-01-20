//package declaration
package ch.nolix.commontest.endpoint2test;

//own imports
import ch.nolix.common.basetest.TestPool;

//class
/**
 * @author Silvan
 * @month 2017-02
 * @lines 20
 */
public final class EndPointTestPool extends TestPool {
	
	//constructor
	/**
	 * Creates a new {@link EndPointTestPool}.
	 */
	public EndPointTestPool() {
		super(NetEndPointTest.class);
	}
}
