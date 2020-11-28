//package declaration
package ch.nolix.elementtest.elementenumtest;

import ch.nolix.common.basetest.TestPool;

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
