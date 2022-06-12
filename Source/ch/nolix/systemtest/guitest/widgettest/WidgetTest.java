//package declaration
package ch.nolix.systemtest.guitest.widgettest;

//own imports
import ch.nolix.core.constant.FunctionCatalogue;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.ObjectTest;
import ch.nolix.system.gui.base.InvisibleGUI;
import ch.nolix.system.gui.widget.Widget;

//class
public abstract class WidgetTest<W extends Widget<W, ?>> extends ObjectTest<W> {
	
	//method
	@TestCase
	public final void testCase_addToGUI() {
		
		//setup
		final var testUnit = createTestUnit();
		
		//setup verification
		expectNot(testUnit.belongsToGUI());
		
		try (final var invisibleGUI = new InvisibleGUI()) {
			
			//execution
			invisibleGUI.pushLayerWithRootWidget(testUnit);
			
			//verification
			expect(testUnit.belongsToGUI());
		}
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
		
		//setup verification
		expect(testUnit.hasLeftMouseButtonPressAction());
		expect(testUnit.hasLeftMouseButtonReleaseAction());
		expect(testUnit.hasRightMouseButtonPressAction());
		expect(testUnit.hasRightMouseButtonReleaseAction());
		expect(testUnit.isFocused());
		
		//execution
		testUnit.reset();
		
		//verification
		expectNot(testUnit.hasLeftMouseButtonPressAction());
		expectNot(testUnit.hasLeftMouseButtonReleaseAction());
		expectNot(testUnit.hasRightMouseButtonPressAction());
		expectNot(testUnit.hasRightMouseButtonReleaseAction());
		expectNot(testUnit.isFocused());
	}
}
