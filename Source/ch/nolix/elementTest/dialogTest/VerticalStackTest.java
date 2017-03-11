/*
 * file:	VerticalStackTest.java
 * author:	Silvan Wyss
 * month:	2016-06
 * lines:	50
 */

//package declaration
package ch.nolix.elementTest.dialogTest;

//own imports
import ch.nolix.common.zetaTest.ZetaTest;
import ch.nolix.element.dialog.Button;
import ch.nolix.element.dialog.Label;
import ch.nolix.element.dialog.WidgetState;
import ch.nolix.element.dialog.TextBox;
import ch.nolix.element.dialog.VerticalStack;

//test class
/**
 * This class is a test class for the vertical stack class.
 */
public final class VerticalStackTest extends ZetaTest {
	
	//test method
	public void testConstructor() {
		
		//execution
		final VerticalStack verticalStack = new VerticalStack();
		
		//verification
		expectThat(verticalStack.getState()).equals(WidgetState.Normal);
		expectThat(verticalStack.isEmpty());
		expectThatNot(verticalStack.belongsToDialog());
	}
	
	//test method
	public void testClear() {
		
		//setup
		final VerticalStack verticalStack = new VerticalStack()	
		.addRectangle(
			new Label(),
			new Button(),
			new TextBox()
		);
		
		//execution
		verticalStack.clear();
		
		//verification
		expectThat(verticalStack.isEmpty());
	}
}
