//package declaration
package ch.nolix.elementTest.widgetsTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.test.ObjectTest;
import ch.nolix.element.GUI.Widget;

//class
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
	
	//method
	@TestCase
	public final void testCase_collapse() {
		
		//setup
		final var widget = createTestObject();
		
		//execution
		widget.setCollapsed();
		
		//verification
		expect(widget.isCollapsed());
	}
	
	//method
	@TestCase
	public final void testCase_getHeight() {
		
		//setup
		final var widget = createTestObject();
		
		//execution
		widget.setCollapsed();
		
		//verification
		expect(widget.getHeight()).isZero();
	}
	
	//method
	@TestCase
	public final void testCase_getWidth() {
		
		//setup
		final var widget = createTestObject();
		
		//execution
		widget.setCollapsed();
		
		//verification
		expect(widget.getWidth()).isZero();
	}
	
	//method
	@TestCase
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
