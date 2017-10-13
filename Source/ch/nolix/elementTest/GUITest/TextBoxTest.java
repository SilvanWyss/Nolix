/*
 * file:	TextBoxTest.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	60
 */

//package declaration
package ch.nolix.elementTest.GUITest;

//own imports
import ch.nolix.core.test2.Test;
import ch.nolix.element.GUI.TextBox;
import ch.nolix.element.color.Color;

//test class
/**
 * This class is a test class for the text box class.
 */
public class TextBoxTest extends Test {

	//test method
	public final void testDefaultConstructor() {
		
		//execution
		final TextBox textBox = new TextBox();
		
		//verification
		expect(textBox.getText()).isEmpty();
	}
	
	//test method
	public final void testSetNormalTextColor() {
		
		//setup
		final TextBox textBox = new TextBox();
		
		//execution
		textBox.getRefNormalStructure().setTextColor(Color.BLUE);
		
		//verification
		expect(textBox.getRefNormalStructure().getActiveTextColor()).equalsTo(Color.BLUE);
		expect(textBox.getRefHoverStructure().getActiveTextColor()).equalsTo(Color.BLUE);
		expect(textBox.getRefFocusStructure().getActiveTextColor()).equalsTo(Color.BLUE);
	}
	
	//test method
	public final void testSetText() {
				
		//setup
		final TextBox textBox = new TextBox();
		
		//execution
		textBox.setText("Test");
		
		//verification
		expect(textBox.getText()).equals("Test");
	}
}
