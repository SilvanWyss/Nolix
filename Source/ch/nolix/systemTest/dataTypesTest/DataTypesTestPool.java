//package declaration
package ch.nolix.systemTest.dataTypesTest;

//own import
import ch.nolix.common.baseTest.TestPool;

//class
public final class DataTypesTestPool extends TestPool {
	
	//constructor
	public DataTypesTestPool() {
		addTestClass(DataTypeHelperTest.class);
	}
}
