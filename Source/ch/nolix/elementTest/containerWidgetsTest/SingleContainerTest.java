//package declaration
package ch.nolix.elementTest.containerWidgetsTest;

import ch.nolix.element.containerWidgets.SingleContainer;
import ch.nolix.element.widgets.Label;
import ch.nolix.elementTest.widgetsTest.BorderWidgetTest;

//test class
/**
 * The {@link SingleContainerTest} is a test for {@link SingleContainer}.
 * 
 * @author Silvan Wyss
 * @month 2018-12
 * @lines 50
 */
public class SingleContainerTest extends BorderWidgetTest<SingleContainer> {
	
	//test case
	public void testCase_reset_2() {
		
		//setup
		final var singleContainer = new SingleContainer(new Label("Test"));
		
		//setup verification
		expect(singleContainer.containsAny());
		
		//execution
		singleContainer.reset();
		
		//verification
		expect(singleContainer.isEmpty());
	}
	
	//test case
	public void testCase_setWidget() {
		
		//setup
		final var singleContainer = new SingleContainer();
		final var label = new Label("Test");
		
		//execution
		singleContainer.setWidget(label);
		
		//verification
		expect(singleContainer.getRefWidget()).isSameAs(label);
	}
	
	@Override
	protected SingleContainer createTestObject() {
		return new SingleContainer();
	}
}
