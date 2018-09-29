/*
 * file:	DialogTestPool.java
 * author:	Silvan Wyss
 * month:	201-08
 * lines:	30
 */

//package declaration
package ch.nolix.elementTest.GUITest;

import ch.nolix.core.testoid.TestPool;

//class
public final class GUITestPool extends TestPool {

	//constructor
	/**
	 * Creates a new dialog test pool.
	 */
	public GUITestPool() {
		addTestClass(
			AccordionTest.class,
			AreaTest.class,
			CaptionPositionTest.class,
			CheckboxTest.class,
			LabelTest.class,
			SingleContainerTest.class,
			TextBoxTest.class,
			VerticalStackTest.class
		);
	}
}
