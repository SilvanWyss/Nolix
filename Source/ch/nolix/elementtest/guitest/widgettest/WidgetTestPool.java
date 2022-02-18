//package declaration
package ch.nolix.elementtest.guitest.widgettest;

import ch.nolix.core.testing.basetest.TestPool;

//class
/**
 * @author Silvan Wyss
 * @date 2019-05-19
 */
public final class WidgetTestPool extends TestPool {
	
	//constructor
	/**
	 * Creates a new {@link WidgetTestPool}.
	 */
	public WidgetTestPool() {
		super(
			AreaTest.class,
			ButtonTest.class,
			CaptionPositionTest.class,
			CheckBoxTest.class,
			LabelTest.class,
			TextBoxTest.class
		);
	}
}
