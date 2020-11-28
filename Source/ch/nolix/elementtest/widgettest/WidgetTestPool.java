//package declaration
package ch.nolix.elementtest.widgettest;

import ch.nolix.common.basetest.TestPool;

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
		super(
			AreaTest.class,
			CaptionPositionTest.class,
			CheckBoxTest.class,
			LabelTest.class,
			TextBoxTest.class
		);
	}
}
