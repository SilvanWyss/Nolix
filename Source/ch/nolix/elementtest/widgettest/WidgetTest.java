//package declaration
package ch.nolix.elementtest.widgettest;

import ch.nolix.common.constant.FunctionCatalogue;
import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.common.testing.test.ObjectTest;
import ch.nolix.element.gui.base.InvisibleGUI;
import ch.nolix.element.gui.base.Widget;

//class
/**
 * A {@link WidgetTest} is a test for {@link Widget}s.
 * 
 * @author Silvan Wyss
 * @date 2018-09-11
 * @lines 110
 * @param <W> is the type of the {@link Widget}s of a {@link WidgetTest}.
 */
public abstract class WidgetTest<W extends Widget<W, ?>> extends ObjectTest<W> {
	
	//method
	@TestCase
	public final void testCase_addToGUI() {
		
		//setup
		final var testUnit = createTestUnit();
		final var invisibleGUI = new InvisibleGUI();
		
		//setup verification
		expectNot(testUnit.belongsToGUI());
		
		//execution
		invisibleGUI.addLayerOnTop(testUnit);
		
		//verification
		expect(testUnit.belongsToGUI());
	}
	
	//method
	@TestCase
	public final void testCase_collapse() {
		
		//setup
		final var testUnit = createTestUnit();
		
		//setup verification
		expectNot(testUnit.isCollapsed());
		
		//execution
		testUnit.setCollapsed();
		
		//verification
		expect(testUnit.isCollapsed());
	}
	
	//method
	@TestCase
	public final void testCase_getHeight_whenIsCollapsed() {
		
		//setup
		final var testUnit = createTestUnit();
		
		//execution
		testUnit.setCollapsed();
		
		//verification
		expect(testUnit.getHeight()).isEqualTo(0);
	}
	
	//method
	@TestCase
	public final void testCase_getWidth_whenIsCollapsed() {
		
		//setup
		final var testUnit = createTestUnit();
		
		//execution
		testUnit.setCollapsed();
		
		//verification
		expect(testUnit.getWidth()).isEqualTo(0);
	}
	
	//method
	@TestCase
	public final void testCase_reset() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.setLeftMouseButtonPressAction(FunctionCatalogue::doNothing);
		testUnit.setLeftMouseButtonReleaseAction(FunctionCatalogue::doNothing);
		testUnit.setRightMouseButtonPressAction(FunctionCatalogue::doNothing);
		testUnit.setRightMouseButtonReleaseAction(FunctionCatalogue::doNothing);
		testUnit.setFocused();
		testUnit.setHovered();
		
		//setup verification
		expect(testUnit.hasLeftMouseButtonPressAction());
		expect(testUnit.hasLeftMouseButtonReleaseAction());
		expect(testUnit.hasRightMouseButtonPressAction());
		expect(testUnit.hasRightMouseButtonReleaseAction());
		expect(testUnit.isFocused());
		expect(testUnit.isHovered());
		
		//execution
		testUnit.reset();
		
		//verification
		expectNot(testUnit.hasLeftMouseButtonPressAction());
		expectNot(testUnit.hasLeftMouseButtonReleaseAction());
		expectNot(testUnit.hasRightMouseButtonPressAction());
		expectNot(testUnit.hasRightMouseButtonReleaseAction());
		expectNot(testUnit.isFocused());
		expectNot(testUnit.isHovered());
	}
}
