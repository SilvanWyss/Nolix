//package declaration
package ch.nolix.elementTest.GUITest;

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
 * @month 2017-03
 * @lines 30
 */
public final class GUITestPool extends TestPool {
	
	//constructor
	/**
	 * Creates a new {@link GUITestPool}.
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
