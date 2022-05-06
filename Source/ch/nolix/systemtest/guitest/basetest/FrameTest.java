//package declaration
package ch.nolix.systemtest.guitest.basetest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.system.gui.base.Frame;

//class
public final class FrameTest extends WidgetGUITest<Frame> {
	
	//method
	@Override
	protected Frame createTestUnit() {
		return new Frame();
	}
	
	//method
	@TestCase
	public void testCase_isRootGUI() {
		try (final var testUnit = createTestUnit()) {
			expect(testUnit.isRootGUI());
		}
	}
}
