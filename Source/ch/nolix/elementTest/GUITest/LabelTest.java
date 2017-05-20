/*
 * file:	LabelTest.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	50
 */

//package declaration
package ch.nolix.elementTest.GUITest;

//own imports
import ch.nolix.core.test2.Test;
import ch.nolix.element.GUI.Label;

//test class
/**
 * This class is a test class for the label class.
 */
public final class LabelTest extends Test {
	
	//test method
	public void testSetNormalTextSize() {
		
		//parameter definition
		final int textSize = 25;
		
		//setup
		Label label = new Label();
		
		//execution
		label.getRefNormalStructure().setTextSize(textSize);
		
		//verification
		expectThat(label.getRefNormalStructure().getCurrentTextSize()).equals(textSize);
	}
	
	//test method
	public void testSetText() {
		
		//parameter definition
		final String text = "Test";
		
		//setup
		Label label = new Label();
		
		//execution
		label.setText(text);
		
		//verification
		expectThat(label.getText()).equals(text);
	}
}
