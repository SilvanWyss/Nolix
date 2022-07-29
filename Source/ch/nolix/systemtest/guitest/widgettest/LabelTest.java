//package declaration
package ch.nolix.systemtest.guitest.widgettest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

//class
public final class LabelTest extends BorderWidgetTest<Label> {
	
	//method
	@TestCase
	public void testCase_getRefLook_setTextColorForState() {
		
		//setup
		final var testUnit = new Label();
		
		//execution
		testUnit.getRefStyle().setTextColorForState(ControlState.BASE, Color.BLUE);
		
		//verification
		expect(testUnit.getRefStyle().getTextColor()).isEqualTo(Color.BLUE);
	}
		
	//method
	@TestCase
	public void testCase_getRefBaseLook_setTextSizeForState() {
		
		//setup
		final var testUnit = new Label();
		
		//execution
		testUnit.getRefStyle().setTextSizeForState(ControlState.BASE, 25);
		
		//verification
		expect(testUnit.getRefStyle().getTextSize()).isEqualTo(25);
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
