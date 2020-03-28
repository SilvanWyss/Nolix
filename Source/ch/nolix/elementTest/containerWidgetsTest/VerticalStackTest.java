//package declaration
package ch.nolix.elementTest.containerWidgetsTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.element.GUI.WidgetState;
import ch.nolix.element.widgets.Button;
import ch.nolix.element.widgets.Label;
import ch.nolix.element.widgets.TextBox;
import ch.nolix.element.widgets.VerticalStack;
import ch.nolix.elementTest.widgetsTest.BorderWidgetTest;

//class
/**
 * A {@link VerticalStackTest} is a test for {@link VerticalStack}.
 * 
 * @author Silvan Wyss
 * @month 2016-06
 * @lines 50
 */
public final class VerticalStackTest extends BorderWidgetTest<VerticalStack> {
	
	//method
	@TestCase
	public void testCase_creation() {
		
		//execution
		final var verticalStack = new VerticalStack();
		
		//verification
		expect(verticalStack.getState()).isEqualTo(WidgetState.NORMAL);
		expect(verticalStack.isEmpty());
		expectNot(verticalStack.belongsToGUI());
	}
	
	//method
	@TestCase
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
