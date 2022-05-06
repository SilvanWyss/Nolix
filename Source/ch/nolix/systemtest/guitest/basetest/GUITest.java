//package declaration
package ch.nolix.systemtest.guitest.basetest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.ObjectTest;
import ch.nolix.system.gui.base.GUI;

//class
public abstract class GUITest<G extends GUI<G>> extends ObjectTest<G> {
	
	//method
	@TestCase
	public final void testCase_setTitle() {
		try (final var testUnit = createTestUnit()) {
			
			//execution
			testUnit.setTitle("MyGUI");
			
			//verification
			expect(testUnit.getTitle()).isEqualTo("MyGUI");
		}
	}
}
