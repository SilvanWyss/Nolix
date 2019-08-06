//package declaration
package ch.nolix.coreTest.commonTypesHelperTest;

import ch.nolix.core.baseTest.TestPool;

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
