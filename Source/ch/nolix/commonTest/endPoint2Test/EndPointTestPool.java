//package declaration
package ch.nolix.commonTest.endPoint2Test;

//own import
import ch.nolix.common.baseTest.TestPool;

//class
public final class EndPointTestPool extends TestPool {
	
	//constructor
	public EndPointTestPool() {
		addTestClass(NetEndPointTest.class);
	}
}
