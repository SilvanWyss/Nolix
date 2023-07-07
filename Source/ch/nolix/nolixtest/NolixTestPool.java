//package declaration
package ch.nolix.nolixtest;

//own imports
import ch.nolix.businesstest.BusinessTestPool;
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coretest.CoreTestPool;
import ch.nolix.systemtest.SystemTestPool;
import ch.nolix.templatetest.TemplateTestPool;

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
			new SystemTestPool(),
			new BusinessTestPool(),
			new TemplateTestPool()
		);
	}
}
