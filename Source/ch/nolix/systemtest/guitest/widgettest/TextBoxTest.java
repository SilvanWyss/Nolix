//package declaration
package ch.nolix.systemtest.guitest.widgettest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.system.gui.textbox.TextBox;

//class
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
