//package declaration
package ch.nolix.elementtest.widgettest;

//own imports
import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.element.gui.widget.TextBox;

//class
/**
 * A {@link TextBoxTest} is a test for {@link TextBox}.
 * 
 * @author Silvan Wyss
 * @date 2016-09-01
 * @lines 60
 */
public class TextBoxTest extends BorderWidgetTest<TextBox> {
	
	//method
	@TestCase
	public final void testCase_creation() {
		
		//execution
		final var textBox = new TextBox();
		
		//verification
		expect(textBox.getText()).isEmpty();
	}
			
	//method
	@TestCase
	public final void testCase_setText() {
		
		//setup
		final var textBox = new TextBox();
		
		//execution
		textBox.setText("Test");
		
		//verification
		expect(textBox.getText()).isEqualTo("Test");
	}
	
	//method
	@Override
	protected TextBox createTestUnit() {
		return new TextBox();
	}
}
