//package declaration
package ch.nolix.elementTest.elementEnumsTest;

//own import
import ch.nolix.common.baseTest.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2017-09
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
