//package declaration
package ch.nolix.elementtest.elementenumtest;

//own imports
import ch.nolix.common.testing.basetest.TestPool;

//class
/**
 * @author Silvan Wyss
 * @date 2017-09-16
 * @lines 20
 */
public final class ElementEnumTestPool extends TestPool {

	//constructor
	/**
	 * Creates a new {@link ElementEnumTestPool}.
	 */
	public ElementEnumTestPool() {
		super(
			DirectionTest.class,
			UniDirectionTest.class
		);
	}
}
