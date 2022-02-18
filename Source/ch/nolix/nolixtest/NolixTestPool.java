//package declaration
package ch.nolix.nolixtest;

import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coretest.CoreTestPool;
import ch.nolix.elementtest.ElementTestPool;
import ch.nolix.nolixlicensetest.NolixLicensesTestPool;
import ch.nolix.systemtest.SystemTestPool;
import ch.nolix.templatetest.TemplatesTestPool;

//class
/**
 * A {@link NolixTestPool} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2017-11-17
 */
public final class NolixTestPool extends TestPool {
	
	//constructor
	/**
	 * Creates a new {@link NolixTestPool}.
	 */
	public NolixTestPool() {
		super(
			new CoreTestPool(),
			new ElementTestPool(),
			new SystemTestPool(),
			new TemplatesTestPool(),
			new NolixLicensesTestPool()
		);
	}
}
