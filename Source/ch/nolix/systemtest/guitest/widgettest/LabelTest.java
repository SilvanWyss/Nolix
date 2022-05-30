//package declaration
package ch.nolix.systemtest.guitest.widgettest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widget.WidgetLookState;

//class
/**
 * A {@link LabelTest} is a test for {@link Label}s.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public final class LabelTest extends BorderWidgetTest<Label> {
	
	//method
	@TestCase
	public void testCase_getRefLook_setTextColorForState() {
		
		//setup
		final var testUnit = new Label();
		
		//execution
		testUnit.getRefActiveLook().setTextColorForState(WidgetLookState.BASE, Color.BLUE);
		
		//verification
		expect(testUnit.getRefActiveLook().getTextColor()).isEqualTo(Color.BLUE);
	}
		
	//method
	@TestCase
	public void testCase_getRefBaseLook_setTextSizeForState() {
		
		//setup
		final var testUnit = new Label();
		
		//execution
		testUnit.getRefActiveLook().setTextSizeForState(WidgetLookState.BASE, 25);
		
		//verification
		expect(testUnit.getRefActiveLook().getTextSize()).isEqualTo(25);
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
