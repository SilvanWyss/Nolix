//package declaration
package ch.nolix.commontest.commontypehelpertest;

//own imports
import ch.nolix.common.basetest.TestPool;

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
