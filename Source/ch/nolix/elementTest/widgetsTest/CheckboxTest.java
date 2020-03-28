//package declaration
package ch.nolix.elementTest.widgetsTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.element.widgets.Checkbox;

//class
/**
 * A {@link CheckboxTest} is a test for {@link Checkbox}.
 * 
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 60
 */
public final class CheckboxTest extends WidgetTest<Checkbox> {
	
	//method
	@TestCase
	public void testCase_creation() {
		
		//execution
		final var checkBox = new Checkbox();
		
		//verification
		expectNot(checkBox.isChecked());
	}
	
	//method
	@TestCase
	public void testCase_check() {
		
		//setup
		final var checkBox = new Checkbox();
		
		//setup verification
		expectNot(checkBox.isChecked());
		
		//execution
		checkBox.check();
		
		//verification
		expect(checkBox.isChecked());
	}
	
	//method
	@TestCase
	public void testCase_uncheck() {
		
		//setup
		final var checkBox = new Checkbox(true);
		
		//setup verification
		expect(checkBox.isChecked());
		
		//execution		
		checkBox.uncheck();
		
		//verification
		expectNot(checkBox.isChecked());
	}
	
	//method
	@Override
	protected Checkbox createTestObject() {
		return new Checkbox();
	}
}
