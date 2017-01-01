/*
 * file:	DialogTestPool.java
 * author:	Silvan Wyss
 * month:	201-08
 * lines:	30
 */

//package declaration
package ch.nolix.elementTest.dialogTest;

//own import
import ch.nolix.common.test.TestPool;

//class
public final class DialogTestPool extends TestPool {

	//constructor
	/**
	 * Creates new dialog test pool.
	 */
	public DialogTestPool() {
		addTest(
			new AreaTest(),
			new CaptionPositionTest(),
			new LabelTest(),
			new SingleContainerTest(),
			new TextBoxTest(),
			new VerticalStackTest()
		);
	}
}
