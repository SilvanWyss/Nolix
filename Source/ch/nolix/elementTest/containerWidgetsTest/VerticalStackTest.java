//package declaration
package ch.nolix.elementTest.containerWidgetsTest;

import ch.nolix.element.GUI_API.WidgetState;
import ch.nolix.element.widgets.Button;
import ch.nolix.element.widgets.Label;
import ch.nolix.element.widgets.TextBox;
import ch.nolix.element.widgets.VerticalStack;
import ch.nolix.elementTest.widgetTest.BorderWidgetTest;

//test class
/**
 * A {@link VerticalStackTest} is a test for {@link VerticalStack}.
 * 
 * @author Silvan Wyss
 * @month 2016-06
 * @lines 50
 */
public final class VerticalStackTest extends BorderWidgetTest<VerticalStack> {
	
	//test case
	public void testCase_creation() {
		
		//execution
		final var verticalStack = new VerticalStack();
		
		//verification
		expect(verticalStack.getState()).isEqualTo(WidgetState.Normal);
		expect(verticalStack.isEmpty());
		expectNot(verticalStack.belongsToGUI());
	}
	
	//test case
	public void testCase_clear() {
		
		//setup
		final var verticalStack =
		new VerticalStack(
			new Label(),
			new Button(),
			new TextBox()
		);
		
		//setup verification
		expectNot(verticalStack.isEmpty());
		
		//execution
		verticalStack.clear();
		
		//verification
		expect(verticalStack.isEmpty());
	}
	
	//method
	protected VerticalStack createTestObject() {
		return new VerticalStack();
	}
}
