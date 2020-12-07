//package declaration
package ch.nolix.elementtest.widgettest;

//own imports
import ch.nolix.common.basetest.TestCase;
import ch.nolix.element.color.Color;
import ch.nolix.element.widget.TextBox;

//class
/**
 * A {@link TextBoxTest} is a test for {@link TextBox}.
 * 
 * @author Silvan Wyss
 * @month 2016-08
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
	public final void testCase_setNormalTextColor() {
		
		//setup
		final var textBox = new TextBox();
		
		//execution
		textBox.getRefBaseLook().setTextColor(Color.BLUE);
		
		//verification
			expect(textBox.getRefBaseLook().getRecursiveOrDefaultTextColor())
			.isEqualTo(Color.BLUE);
			
			expect(textBox.getRefHoverLook().getRecursiveOrDefaultTextColor())
			.isEqualTo(Color.BLUE);
			
			expect(textBox.getRefFocusLook().getRecursiveOrDefaultTextColor())
			.isEqualTo(Color.BLUE);
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
