//package declaration
package ch.nolix.elementtest.guitest.widgettest;

//own imports
import ch.nolix.common.testing.basetest.TestPool;

//class
/**
 * @author Silvan Wyss
 * @date 2019-05-19
 * @lines 20
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
