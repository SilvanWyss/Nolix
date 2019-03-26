//package declaration
package ch.nolix.elementTest.GUITest;

import ch.nolix.core.test.ObjectTest;
import ch.nolix.element.GUI.CursorIcon;
import ch.nolix.element.GUI.Widget;

//abstract test class
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
	public void testCase_collapse() {
		
		//setup
		final var widget = createTestObject();
		
		//execution
		widget.setCollapsed();
		
		//verification
		expect(widget.isCollapsed());
	}
	
	//test case
	public void testCase_getHeight() {
		
		//setup
		final var widget = createTestObject();
		
		//execution
		widget.setCollapsed();
		
		//verification
		expect(widget.getHeight()).isZero();
	}
	
	//test case
	public void testCase_getWidth() {
		
		//setup
		final var widget = createTestObject();
		
		//execution
		widget.setCollapsed();
		
		//verification
		expect(widget.getWidth()).isZero();
	}
	
	//test case
	public void testCase_noteAnyLeftMouseButtonPress() {
		
		//setup
		final var widget = createTestObject();
		widget.setParentCursorPosition(1, 1);
		
		//setup verification
		expect(widget.isNormal());
		
		//execution
		widget.noteAnyLeftMouseButtonPress();
		
		//verification
		expect(widget.isHoverFocused());
	}
	
	//test case
	public void testCase_reset() {
		
		//setup
		final var widget = createTestObject();
		
		//execution
		widget.reset();
		
		//verification
			expect(widget.isNormal());
			expect(widget.getCustomCursorIcon()).isEqualTo(CursorIcon.Arrow);
			
			expectNot(
				widget.hasLeftMouseButtonPressCommand(),
				widget.hasLeftMouseButtonReleaseCommand(),
				widget.hasRightMouseButtonPressCommand(),
				widget.hasRightMouseButtonReleaseCommand()
			);
	}
}
