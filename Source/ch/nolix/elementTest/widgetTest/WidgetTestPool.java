//package declaration
package ch.nolix.elementTest.widgetTest;

//own import
import ch.nolix.core.testoid.TestPool;
import ch.nolix.elementTest.widgetTest.AreaTest;
import ch.nolix.elementTest.widgetTest.CaptionPositionTest;
import ch.nolix.elementTest.widgetTest.CheckboxTest;
import ch.nolix.elementTest.widgetTest.LabelTest;
import ch.nolix.elementTest.widgetTest.TextBoxTest;

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
