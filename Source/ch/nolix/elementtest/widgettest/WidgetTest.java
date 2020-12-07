//package declaration
package ch.nolix.elementtest.widgettest;

//own imports
import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.test.ObjectTest;
import ch.nolix.element.gui.Widget;

//class
/**
 * A {@link WidgetTest} is a test for {@link Widget}s.
 * 
 * @author Silvan Wyss
 * @month 2018-09
 * @lines 80
 * @param <W> The type of the {@link Widget}s of a {@link WidgetTest}.
 */
public abstract class WidgetTest<W extends Widget<W, ?>> extends ObjectTest<W> {
	
	//method
	@TestCase
	public final void testCase_collapse() {
		
		//setup
		final var widget = createTestUnit();
		
		//execution
		widget.setCollapsed();
		
		//verification
		expect(widget.isCollapsed());
	}
	
	//method
	@TestCase
	public final void testCase_getHeight() {
		
		//setup
		final var widget = createTestUnit();
		
		//execution
		widget.setCollapsed();
		
		//verification
		expect(widget.getHeight()).isEqualTo(0);
	}
	
	//method
	@TestCase
	public final void testCase_getWidth() {
		
		//setup
		final var widget = createTestUnit();
		
		//execution
		widget.setCollapsed();
		
		//verification
		expect(widget.getWidth()).isEqualTo(0);
	}
	
	//method
	@TestCase
	public final void testCase_reset() {
		
		//setup
		final var widget = createTestUnit();
		
		//execution
		widget.reset();
		
		//verification
		expectNot(
			widget.hasLeftMouseButtonPressAction(),
			widget.hasLeftMouseButtonReleaseAction(),
			widget.hasRightMouseButtonPressAction(),
			widget.hasRightMouseButtonReleaseAction(),
			widget.isFocused(),
			widget.isHovered()
		);
	}
}
