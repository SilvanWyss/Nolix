/*
 * file:	LabelTest.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	50
 */

//package declaration
package ch.nolix.elementTest.dialogTest;

//own imports
import ch.nolix.common.zetaTest.ZetaTest;
import ch.nolix.element.dialog.Label;

//test class
/**
 * This class is a test class for the label class.
 */
public final class LabelTest extends ZetaTest {
	
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
