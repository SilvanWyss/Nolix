//package declaration
package ch.nolix.elementTest.GUITest;

import ch.nolix.core.test2.Test;
import ch.nolix.element.GUI.TextBox;
import ch.nolix.element.color.Color;

//test class
/**
 * A text box test is a test for the text box class.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 60
 */
public class TextBoxTest extends Test {

	//test case
	public final void testCase_constructor() {
		
		//execution
		final TextBox textBox = new TextBox();
		
		//verification
		expect(textBox.getText()).isEmpty();
	}
	
	//test case
	public final void testCase_setNormalTextColor() {
		
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
	
	//test case
	public final void testCase_setText() {
				
		//setup
		final TextBox textBox = new TextBox();
		
		//execution
		textBox.setText("Test");
		
		//verification
		expect(textBox.getText()).isEqualTo("Test");
	}
}
