//package declaration
package ch.nolix.elementTest.widgetTest;

import ch.nolix.common.basetest.TestCase;
import ch.nolix.element.color.Color;
import ch.nolix.element.widget.Label;

//class
/**
 * A {@link LabelTest} is a test for {@link Label}.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 60
 */
public final class LabelTest extends WidgetTest<Label> {
	
	//method
	@TestCase
	public void testCase_getRefBaseLook_setTextColor() {
		
		//setup
		final var label = new Label();
		
		//execution
		label.getRefBaseLook().setTextColor(Color.BLUE);
		
		//verification
		expect(label.getRefBaseLook().getRecursiveOrDefaultTextColor()).isEqualTo(Color.BLUE);
	}
		
	//method
	@TestCase
	public void testCase_getRefBaseLook_setTextSize() {
		
		//setup
		final var label = new Label();
		
		//execution
		label.getRefBaseLook().setTextSize(25);
		
		//verification
		expect(label.getRefBaseLook().getRecursiveOrDefaultTextSize()).isEqualTo(25);
	}
	
	//method
	@TestCase
	public void testCase_setText() {
		
		//setup
		final var label = new Label();
		
		//execution
		label.setText("Helix");
		
		//verification
		expect(label.getText()).isEqualTo("Helix");
	}
	
	//method
	@Override
	protected Label createTestUnit() {
		return new Label();
	}
}
