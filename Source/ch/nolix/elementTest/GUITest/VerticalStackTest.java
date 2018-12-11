//package declaration
package ch.nolix.elementTest.GUITest;

//own imports
import ch.nolix.element.GUI.Button;
import ch.nolix.element.GUI.Label;
import ch.nolix.element.GUI.TextBox;
import ch.nolix.element.GUI.VerticalStack;
import ch.nolix.element.GUI.WidgetState;

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
	public void testCase_constructor() {
		
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
