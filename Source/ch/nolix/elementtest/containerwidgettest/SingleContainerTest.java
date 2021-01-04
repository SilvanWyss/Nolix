//package declaration
package ch.nolix.elementtest.containerwidgettest;

//own imports
import ch.nolix.common.basetest.TestCase;
import ch.nolix.element.containerwidget.SingleContainer;
import ch.nolix.element.gui.Widget;
import ch.nolix.element.widget.Label;

//class
/**
 * The {@link SingleContainerTest} is a test for {@link SingleContainer}.
 * 
 * @author Silvan Wyss
 * @month 2018-12
 * @lines 60
 */
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
