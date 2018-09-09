//package declaration
package ch.nolix.coreTest.helperTest;

//own import
import ch.nolix.primitive.testoid.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2018-08
 * @lines 20
 */
public final class HelperTestPool extends TestPool {
	
	//constructor
	/**
	 * Creates a new {@link HelperTestPool}.
	 */
	public HelperTestPool() {
		addTestClass(
			CharacterHelperTest.class,
			StringHelperTest.class
		);
	}
}
