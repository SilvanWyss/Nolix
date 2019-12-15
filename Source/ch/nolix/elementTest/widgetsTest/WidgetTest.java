//package declaration
package ch.nolix.elementTest.widgetsTest;

import ch.nolix.common.test.ObjectTest;
import ch.nolix.element.GUI.Widget;

//test class
/**
 * A {@link WidgetTest} is a test for {@link Widget}.
 * 
 * @author Silvan Wyss
 * @month 2018-09
 * @lines 90
 * @param <W> The type of {@link Widget} of a {@link WidgetTest}.
 */
public abstract class WidgetTest<W extends Widget<W, ?>>
extends ObjectTest<W> {
	
	//test case
	public final void testCase_collapse() {
		
		//setup
		final var widget = createTestObject();
		
		//execution
		widget.setCollapsed();
		
		//verification
		expect(widget.isCollapsed());
	}
	
	//test case
	public final void testCase_getHeight() {
		
		//setup
		final var widget = createTestObject();
		
		//execution
		widget.setCollapsed();
		
		//verification
		expect(widget.getHeight()).isZero();
	}
	
	//test case
	public final void testCase_getWidth() {
		
		//setup
		final var widget = createTestObject();
		
		//execution
		widget.setCollapsed();
		
		//verification
		expect(widget.getWidth()).isZero();
	}
	
	//test case
	public final void testCase_reset() {
		
		//setup
		final var widget = createTestObject();
		
		//execution
		widget.reset();
		
		//verification
			expect(widget.isNormal());
			
			expectNot(
				widget.hasLeftMouseButtonPressCommand(),
				widget.hasLeftMouseButtonReleaseCommand(),
				widget.hasRightMouseButtonPressCommand(),
				widget.hasRightMouseButtonReleaseCommand()
			);
	}
}
