//package declaration
package ch.nolix.elementtest.widgettest;

//own imports
import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.widget.Label;

//class
/**
 * A {@link LabelTest} is a test for {@link Label}s.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 60
 */
public final class LabelTest extends BorderWidgetTest<Label> {
	
	//method
	@TestCase
	public void testCase_getRefLook_setTextColorForState() {
		
		//setup
		final var testUnit = new Label();
		
		//execution
		testUnit.getRefLook().setTextColorForState(WidgetLookState.BASE, Color.BLUE);
		
		//verification
		expect(testUnit.getRefLook().getTextColor()).isEqualTo(Color.BLUE);
	}
		
	//method
	@TestCase
	public void testCase_getRefBaseLook_setTextSizeForState() {
		
		//setup
		final var testUnit = new Label();
		
		//execution
		testUnit.getRefLook().setTextSizeForState(WidgetLookState.BASE, 25);
		
		//verification
		expect(testUnit.getRefLook().getTextSize()).isEqualTo(25);
	}
	
	//method
	@TestCase
	public void testCase_setText() {
		
		//setup
		final var testUnit = new Label();
		
		//execution
		testUnit.setText("Helix");
		
		//verification
		expect(testUnit.getText()).isEqualTo("Helix");
	}
	
	//method
	@Override
	protected Label createTestUnit() {
		return new Label();
	}
}
