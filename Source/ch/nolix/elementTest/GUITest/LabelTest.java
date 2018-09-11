//package declaration
package ch.nolix.elementTest.GUITest;

//own imports
import ch.nolix.element.GUI.Label;
import ch.nolix.element.color.Color;

//test class
/**
 * A {@link LabelTest} is a test for {@link Label}.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 60
 */
public final class LabelTest extends WidgetTest<Label> {
	
	//test case
	public void testCase_getRefBaseLook_setTextColor() {
		
		//setup
		final var label = new Label();
		
		//execution
		label.getRefBaseLook().setTextColor(Color.BLUE);
		
		//verification
		expect(label.getRefBaseLook().getRecursiveOrDefaultTextColor()).isEqualTo(Color.BLUE);
	}
		
	//test case
	public void testCase_getRefBaseLook_setTextSize() {
		
		//setup
		final var label = new Label();
		
		//execution
		label.getRefBaseLook().setTextSize(25);
		
		//verification
		expect(label.getRefBaseLook().getRecursiveOrDefaultTextSize()).isEqualTo(25);
	}
	
	//test case
	public void testCase_setText() {
		
		//setup
		final var label = new Label();
		
		//execution
		label.setText("Helix");
		
		//verification
		expect(label.getText()).isEqualTo("Helix");
	}

	//method
	protected Label createTestObject() {
		return new Label();
	}
}
