//package declaration
package ch.nolix.systemtest.guitest.maintest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.ObjectTest;
import ch.nolix.system.gui.main.GUI;

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
