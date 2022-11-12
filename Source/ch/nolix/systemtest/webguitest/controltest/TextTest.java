//package declaration
package ch.nolix.systemtest.webguitest.controltest;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.system.webgui.control.Text;

//class
public final class TextTest extends ControlTest<Text> {
	
	//method
	@TestCase
	public void testCase_setText() {
		
		//setup
		final var testUnit = createTestUnit();
		
		//setup verification
		expect(testUnit.getText()).isEqualTo("-");
		
		//execution
		testUnit.setText("Lorem Ipsum");
		
		//verification
		expect(testUnit.getText()).isEqualTo("Lorem Ipsum");
	}
	
	//method
	@TestCase
	public void testCase_setText_whenGivenTextIsBlank() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.setText("Lorem Ipsum");
		
		//execution & verification
		expectRunning(() -> testUnit.setText(" "))
		.throwsException().ofType(InvalidArgumentException.class)
		.withMessage("The given text is blank.");
		
		//verification
		expect(testUnit.getText()).isEqualTo("Lorem Ipsum");
	}
	
	//method
	@TestCase
	public void testCase_setText_whenGivenTextIsNull() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.setText("Lorem Ipsum");
		
		//execution & verification
		expectRunning(() -> testUnit.setText(null))
		.throwsException().ofType(ArgumentIsNullException.class)
		.withMessage("The given text is null.");
		
		//verification
		expect(testUnit.getText()).isEqualTo("Lorem Ipsum");
	}
	
	//method
	@Override
	protected Text createTestUnit() {
		return new Text();
	}
}
