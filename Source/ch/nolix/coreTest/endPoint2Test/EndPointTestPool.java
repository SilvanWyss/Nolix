//package declaration
package ch.nolix.coreTest.endPoint2Test;

//own import
import ch.nolix.core.testoid.TestPool;

//class
public final class EndPointTestPool extends TestPool {
	
	//constructor
	public EndPointTestPool() {
		addTestClass(NetEndPointTest.class);
	}
}
