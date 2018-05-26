/*
 * file:	DialogTestPool.java
 * author:	Silvan Wyss
 * month:	201-08
 * lines:	30
 */

//package declaration
package ch.nolix.elementTest.GUITest;

import ch.nolix.primitive.testoid.TestPool;

//class
public final class GUITestPool extends TestPool {

	//constructor
	/**
	 * Creates a new dialog test pool.
	 */
	public GUITestPool() {
		addTest(
			new AreaTest(),
			new CaptionPositionTest(),
			new CheckboxTest(),
			new LabelTest(),
			new SingleContainerTest(),
			new TextBoxTest(),
			new VerticalStackTest()
		);
	}
}
