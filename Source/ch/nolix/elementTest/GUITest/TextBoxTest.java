//package declaration
package ch.nolix.elementTest.GUITest;

import ch.nolix.element.GUI.TextBox;
import ch.nolix.element.color.Color;
import ch.nolix.primitive.test2.Test;

//test class
/**
 * A text box test is a test for the text box class.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 60
 */
public class TextBoxTest extends Test {

	//test method
	public final void test_constructor() {
		
		//execution
		final TextBox textBox = new TextBox();
		
		//verification
		expect(textBox.getText()).isEmpty();
	}
	
	//test method
	public final void test_setNormalTextColor() {
		
		//setup
		final TextBox textBox = new TextBox();
		
		//execution
		textBox.getRefBaseLook().setTextColor(Color.BLUE);
		
		//verification
			expect(textBox.getRefBaseLook().getRecursiveOrDefaultTextColor().getIntValue())
			.isEqualTo(Color.BLUE_INT);
			
			expect(textBox.getRefHoverLook().getRecursiveOrDefaultTextColor().getIntValue())
			.isEqualTo(Color.BLUE_INT);
			
			expect(textBox.getRefFocusLook().getRecursiveOrDefaultTextColor().getIntValue())
			.isEqualTo(Color.BLUE_INT);
	}
	
	//test method
	public final void test_setText() {
				
		//setup
		final TextBox textBox = new TextBox();
		
		//execution
		textBox.setText("Test");
		
		//verification
		expect(textBox.getText()).isEqualTo("Test");
	}
}
