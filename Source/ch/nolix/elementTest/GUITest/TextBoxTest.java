//package declaration
package ch.nolix.elementTest.GUITest;

//own imports
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
		textBox.getRefNormalStructure().setTextColor(Color.BLUE);
		
		//verification
			expect(textBox.getRefNormalStructure().getActiveTextColor().getValue())
			.isEqualTo(Color.BLUE_INT);
			
			expect(textBox.getRefHoverStructure().getActiveTextColor().getValue())
			.isEqualTo(Color.BLUE_INT);
			
			expect(textBox.getRefFocusStructure().getActiveTextColor().getValue())
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
