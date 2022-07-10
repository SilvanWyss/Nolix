//package declaration
package ch.nolix.systemtest.guitest.maintest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.system.gui.widgetgui.InvisibleGUI;

//class
public final class InvisibleGUITest extends WidgetGUITest<InvisibleGUI> {
	
	//method
	@TestCase
	public void testCase_isRootGUI() {
		try (final var testUnit = createTestUnit()) {
			expect(testUnit.isRootGUI());
		}
	}
	
	//method
	@Override
	protected InvisibleGUI createTestUnit() {
		return new InvisibleGUI();
	}
}
