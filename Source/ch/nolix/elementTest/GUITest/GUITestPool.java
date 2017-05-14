/*
 * file:	DialogTestPool.java
 * author:	Silvan Wyss
 * month:	201-08
 * lines:	30
 */

//package declaration
package ch.nolix.elementTest.GUITest;

//own import
import ch.nolix.core.test.TestPool;

//class
public final class GUITestPool extends TestPool {

	//constructor
	/**
	 * Creates new dialog test pool.
	 */
	public GUITestPool() {
		addTest(
			new AreaTest(),
			new CaptionPositionTest(),
			new CheckBoxTest(),
			new LabelTest(),
			new SingleContainerTest(),
			new TextBoxTest(),
			new VerticalStackTest()
		);
	}
}
