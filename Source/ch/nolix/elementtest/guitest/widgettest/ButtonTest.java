//package declaration
package ch.nolix.elementtest.guitest.widgettest;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.system.gui.widget.Button;
import ch.nolix.system.gui.widget.ButtonRole;

//class
public final class ButtonTest extends BorderWidgetTest<Button> {
	
	//method
	@TestCase
	public void testCase_creation() {
		
		//execution
		final var result = new Button();
		
		//verification
		expectNot(result.hasRole());
		expect(result.getText()).isEqualTo("-");
	}
	
	//method
	@TestCase
	public void testCase_setRole() {
		
		//setup
		final var testUnit = new Button();
		
		//execution
		testUnit.setRole(ButtonRole.CANCEL_BUTTON);
		
		//verification
		expect(testUnit.getRole()).isEqualTo(ButtonRole.CANCEL_BUTTON);
	}
	
	//method
	@TestCase
	public void testCase_setRole_whenTheGivenRoleIsNull() {
		
		//setup
		final var testUnit = new Button();
		
		//execution & verification
		expectRunning(() -> testUnit.setRole(null))
		.throwsException()
		.ofType(ArgumentIsNullException.class)
		.withMessage("The given value is null.");
	}
	
	//method
	@Override
	protected Button createTestUnit() {
		return new Button();
	}
}
