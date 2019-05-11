//package declaration
package ch.nolix.elementTest.GUITest;

import ch.nolix.element.widget.Label;
import ch.nolix.element.widget.SingleContainer;

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
	public void testCase_reset() {
		
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
