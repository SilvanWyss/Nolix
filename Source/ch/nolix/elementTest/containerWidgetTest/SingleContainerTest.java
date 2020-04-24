//package declaration
package ch.nolix.elementTest.containerWidgetTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.element.containerWidget.SingleContainer;
import ch.nolix.element.widgets.Label;
import ch.nolix.elementTest.widgetsTest.BorderWidgetTest;

//class
/**
 * The {@link SingleContainerTest} is a test for {@link SingleContainer}.
 * 
 * @author Silvan Wyss
 * @month 2018-12
 * @lines 50
 */
public class SingleContainerTest extends BorderWidgetTest<SingleContainer> {
	
	//method
	@TestCase
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
	
	//method
	@TestCase
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
