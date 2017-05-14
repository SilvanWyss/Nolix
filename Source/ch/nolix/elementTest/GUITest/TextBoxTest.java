/*
 * file:	TextBoxTest.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	60
 */

//package declaration
package ch.nolix.elementTest.GUITest;

//own imports
import ch.nolix.core.test2.ZetaTest;
import ch.nolix.element.GUI.TextBox;
import ch.nolix.element.basic.Color;

//test class
/**
 * This class is a test class for the text box class.
 */
public class TextBoxTest extends ZetaTest {

	//test method
	public final void testDefaultConstructor() {
		
		//execution
		final TextBox textBox = new TextBox();
		
		//verification
		expectThat(textBox.getText()).isEmpty();
	}
	
	//test method
	public final void testSetNormalTextColor() {
		
		//setup
		final TextBox textBox = new TextBox();
		
		//execution
		textBox.getRefNormalStructure().setTextColor(Color.BLUE);
		
		//verification
		expectThat(textBox.getRefNormalStructure().getCurrentTextColor().getValue()).equals(Color.BLUE);
		expectThat(textBox.getRefHoverStructure().getCurrentTextColor().getValue()).equals(Color.BLUE);
		expectThat(textBox.getRefFocusStructure().getCurrentTextColor().getValue()).equals(Color.BLUE);
	}
	
	//test method
	public final void testSetText() {
				
		//setup
		final TextBox textBox = new TextBox();
		
		//execution
		textBox.setText("Test");
		
		//verification
		expectThat(textBox.getText()).equals("Test");
	}
}
