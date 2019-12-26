//package declaration
package ch.nolix.nolixLicensesTest;

//own import
import ch.nolix.common.baseTest.TestPool;

//class
public final class NolixLicensesTestPool extends TestPool {
	
	//constructor
	public NolixLicensesTestPool() {
		addTestClass(Nolix2020ClassicTest.class);
	}
}
