/*
 * file:	LabelTest.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	50
 */

//package declaration
package ch.nolix.elementTest.GUITest;

import ch.nolix.element.GUI.Label;
import ch.nolix.primitive.test2.Test;

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
		label.getRefBaseLook().setTextSize(textSize);
		
		//verification
		expect(label.getRefBaseLook().getRecursiveOrDefaultTextSize()).isEqualTo(textSize);
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
		expect(label.getText()).isEqualTo(text);
	}
}
