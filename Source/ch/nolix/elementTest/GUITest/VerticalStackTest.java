/*
 * file:	VerticalStackTest.java
 * author:	Silvan Wyss
 * month:	2016-06
 * lines:	50
 */

//package declaration
package ch.nolix.elementTest.GUITest;

import ch.nolix.core.test2.Test;
import ch.nolix.element.GUI.Button;
import ch.nolix.element.GUI.Label;
import ch.nolix.element.GUI.TextBox;
import ch.nolix.element.GUI.VerticalStack;
import ch.nolix.element.GUI.WidgetState;

//test class
/**
 * This class is a test class for the vertical stack class.
 */
public final class VerticalStackTest extends Test {
	
	//test case
	public void testConstructor() {
		
		//execution
		final VerticalStack verticalStack = new VerticalStack();
		
		//verification
		expect(verticalStack.getState()).isEqualTo(WidgetState.Normal);
		expect(verticalStack.isEmpty());
		expectNot(verticalStack.belongsToGUI());
	}
	
	//test case
	public void testClear() {
		
		//setup
		final VerticalStack verticalStack = new VerticalStack()	
		.addWidget(
			new Label(),
			new Button(),
			new TextBox()
		);
		
		//execution
		verticalStack.clear();
		
		//verification
		expect(verticalStack.isEmpty());
	}
}
