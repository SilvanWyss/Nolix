//package declaration
package ch.nolix.commonTest.commonTypeWrappersTest;

//own import
import ch.nolix.common.baseTest.TestPool;

//class
public final class CommonTypeWrapperTestPool extends TestPool {
	
	//constructor
	public CommonTypeWrapperTestPool() {
		addTestClass(WrapperByteTest.class);
	}
}
