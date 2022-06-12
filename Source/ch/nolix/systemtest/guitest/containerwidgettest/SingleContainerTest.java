//package declaration
package ch.nolix.systemtest.guitest.containerwidgettest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.system.gui.containerwidget.SingleContainer;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widget.Widget;

//class
public class SingleContainerTest extends ContainerWidgetTest<SingleContainer> {
	
	//method
	@TestCase
	public void testCase_reset_2() {
		
		//setup
		final var singleContainer = new SingleContainer().setWidget(new Label().setText("Test"));
		
		//setup verification
		expect(singleContainer.containsAny());
		
		//execution
		singleContainer.reset();
		
		//verification
		expect(singleContainer.isEmpty());
	}
	
	//method
	@TestCase
	public void testCase_setWidget() {
		
		//setup
		final var singleContainer = new SingleContainer();
		final var label = new Label().setText("Test");
		
		//execution
		singleContainer.setWidget(label);
		
		//verification
		expect(singleContainer.getRefWidget()).isSameAs(label);
	}
	
	//method
	@Override
	protected void addWidgetToContainerWidget(final SingleContainer singleContainer, final Widget<?, ?> widget) {
		singleContainer.setWidget(widget);
	}
	
	//method
	@Override
	protected SingleContainer createTestUnit() {
		return new SingleContainer();
	}
}
