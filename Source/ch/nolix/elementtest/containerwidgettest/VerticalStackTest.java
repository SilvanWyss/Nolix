//package declaration
package ch.nolix.elementtest.containerwidgettest;

//own imports
import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.containerwidget.VerticalStack;
import ch.nolix.element.gui.widget.Button;
import ch.nolix.element.gui.widget.Label;
import ch.nolix.element.gui.widget.TextBox;

//class
/**
 * A {@link VerticalStackTest} is a test for {@link VerticalStack}.
 * 
 * @author Silvan Wyss
 * @date 2016-07-01
 * @lines 60
 */
public final class VerticalStackTest extends ContainerWidgetTest<VerticalStack> {
	
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
	@Override
	protected void addWidgetToContainerWidget(final VerticalStack verticalStack, final Widget<?, ?> widget) {
		verticalStack.addWidget(widget);
	}
	
	//method
	protected VerticalStack createTestUnit() {
		return new VerticalStack();
	}
}
