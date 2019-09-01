//package declaration
package ch.nolix.elementTest.widgetsTest;

import ch.nolix.common.baseTest.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2019-05
 * @lines 30
 */
public final class WidgetTestPool extends TestPool {
	
	//constructor
	/**
	 * Creates a new {@link WidgetTestPool}.
	 */
	public WidgetTestPool() {
		addTestClass(
			AreaTest.class,
			CaptionPositionTest.class,
			CheckboxTest.class,
			LabelTest.class,
			TextBoxTest.class
		);
	}
}
