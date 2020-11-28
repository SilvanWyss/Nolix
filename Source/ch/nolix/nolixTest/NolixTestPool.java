//package declaration
package ch.nolix.nolixTest;

//own imports
import ch.nolix.common.baseTest.TestPool;
import ch.nolix.commonTest.CommonTestPool;
import ch.nolix.elementTest.ElementTestPool;
import ch.nolix.nolixLicenseTest.NolixLicensesTestPool;
import ch.nolix.systemtest.SystemTestPool;
import ch.nolix.techTest.TechTestPool;
import ch.nolix.templatetest.TemplatesTestPool;

//class
/**
 * A {@link NolixTestPool} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 30
 */
public final class NolixTestPool extends TestPool {
	
	//constructor
	/**
	 * Creates a new {@link NolixTestPool}.
	 */
	public NolixTestPool() {
		super(
			new CommonTestPool(),
			new ElementTestPool(),
			new SystemTestPool(),
			new TechTestPool(),
			new TemplatesTestPool(),
			new NolixLicensesTestPool()
		);
	}
}
