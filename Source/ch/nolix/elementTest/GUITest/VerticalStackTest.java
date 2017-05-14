/*
 * file:	VerticalStackTest.java
 * author:	Silvan Wyss
 * month:	2016-06
 * lines:	50
 */

//package declaration
package ch.nolix.elementTest.GUITest;

//own imports
import ch.nolix.core.zetaTest.ZetaTest;
import ch.nolix.element.GUI.Button;
import ch.nolix.element.GUI.Label;
import ch.nolix.element.GUI.TextBox;
import ch.nolix.element.GUI.VerticalStack;
import ch.nolix.element.GUI.WidgetState;

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
