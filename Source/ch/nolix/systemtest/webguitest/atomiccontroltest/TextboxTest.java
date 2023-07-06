//package declaration
package ch.nolix.systemtest.webguitest.atomiccontroltest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.system.webgui.atomiccontrol.Textbox;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ITextbox;

//class
public final class TextboxTest extends ControlTest<ITextbox> {
	
	//method
	@TestCase
	public void testCase_emptyText() {
		
		//setup
		final var testUnit = new Textbox();
		testUnit.setText("Lorem ipsum");
		
		//execution
		testUnit.emptyText();
		
		//verification
		expect(testUnit.getText()).isEmpty();
	}
	
	//method
	@Override
	protected ITextbox createTestUnit() {
		return new Textbox();
	}
}
