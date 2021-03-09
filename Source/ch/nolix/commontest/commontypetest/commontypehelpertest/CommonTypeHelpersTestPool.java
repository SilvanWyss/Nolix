//package declaration
package ch.nolix.commontest.commontypetest.commontypehelpertest;

import ch.nolix.common.testing.basetest.TestPool;

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
		super(
			CharacterHelperTest.class,
			StringHelperTest.class
		);
	}
}
