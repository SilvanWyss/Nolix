//package declaration
package ch.nolix.elementTest.containerWidgetTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.Label;
import ch.nolix.element.widget.TextBox;
import ch.nolix.element.widget.VerticalStack;
import ch.nolix.elementTest.widgetTest.BorderWidgetTest;

//class
/**
 * A {@link VerticalStackTest} is a test for {@link VerticalStack}.
 * 
 * @author Silvan Wyss
 * @month 2016-06
 * @lines 60
 */
public final class VerticalStackTest extends BorderWidgetTest<VerticalStack> {
	
	//method
	@TestCase
	public void testCase_creation() {
		
		//execution
		final var verticalStack = new VerticalStack();
		
		//verification
		expect(verticalStack.isEmpty());
		expectNot(verticalStack.belongsToGUI());
	}
	
	//method
	@TestCase
	public void testCase_clear() {
		
		//setup
		final var verticalStack =
		new VerticalStack()
		.addWidget(
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
	protected VerticalStack createTestUnit() {
		return new VerticalStack();
	}
}
