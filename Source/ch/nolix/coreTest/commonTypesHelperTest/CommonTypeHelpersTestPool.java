//package declaration
package ch.nolix.coreTest.commonTypesHelperTest;

//own imports
import ch.nolix.core.testoid.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2018-08
 * @lines 20
 */
public final class CommonTypeHelpersTestPool extends TestPool {
	
	//constructor
	/**
	 * Creates a new {@link CommonTypeHelpersTestPool}.
	 */
	public CommonTypeHelpersTestPool() {
		addTestClass(
			CharacterHelperTest.class,
			StringHelperTest.class
		);
	}
}
