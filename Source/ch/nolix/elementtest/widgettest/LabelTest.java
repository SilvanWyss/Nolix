//package declaration
package ch.nolix.elementtest.widgettest;

//own imports
import ch.nolix.common.basetest.TestCase;
import ch.nolix.element.color.Color;
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
	public void testCase_getRefBaseLook_setTextColor() {
		
		//setup
		final var testUnit = new Label();
		
		//execution
		testUnit.getRefBaseLook().setTextColor(Color.BLUE);
		
		//verification
		expect(testUnit.getRefBaseLook().getRecursiveOrDefaultTextColor()).isEqualTo(Color.BLUE);
	}
		
	//method
	@TestCase
	public void testCase_getRefBaseLook_setTextSize() {
		
		//setup
		final var testUnit = new Label();
		
		//execution
		testUnit.getRefBaseLook().setTextSize(25);
		
		//verification
		expect(testUnit.getRefBaseLook().getRecursiveOrDefaultTextSize()).isEqualTo(25);
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
