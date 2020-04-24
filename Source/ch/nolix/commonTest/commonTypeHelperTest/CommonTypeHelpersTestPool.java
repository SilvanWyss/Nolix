//package declaration
package ch.nolix.commonTest.commonTypeHelperTest;

//own import
import ch.nolix.common.baseTest.TestPool;

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
