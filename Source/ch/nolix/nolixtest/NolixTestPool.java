//package declaration
package ch.nolix.nolixtest;

import ch.nolix.common.testing.basetest.TestPool;
import ch.nolix.commontest.CommonTestPool;
import ch.nolix.elementtest.ElementTestPool;
import ch.nolix.nolixlicensetest.NolixLicensesTestPool;
import ch.nolix.systemtest.SystemTestPool;
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
			new TemplatesTestPool(),
			new NolixLicensesTestPool()
		);
	}
}
