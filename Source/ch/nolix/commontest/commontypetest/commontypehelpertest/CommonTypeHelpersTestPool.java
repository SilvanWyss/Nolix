//package declaration
package ch.nolix.commontest.commontypetest.commontypehelpertest;

//own imports
import ch.nolix.common.testing.basetest.TestPool;

//class
/**
 * @author Silvan Wyss
 * @date 2018-09-01
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
