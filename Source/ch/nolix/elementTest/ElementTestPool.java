/*
 * file:	ElementTestPool.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	20
 */

//package declaration
package ch.nolix.elementTest;

//own imports
import ch.nolix.core.testBase.TestPool;
import ch.nolix.elementTest.GUITest.GUITestPool;
import ch.nolix.elementTest.dataTest.DataTestPool;

//class
public final class ElementTestPool extends TestPool {

	//constructor
	/**
	 * Creates new element test pool.
	 */
	public ElementTestPool() {
		addTestPool(
			new DataTestPool(),
			new GUITestPool()
		);
	}
}
