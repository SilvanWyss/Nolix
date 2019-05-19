//package declaration
package ch.nolix.elementTest.widgetTest;

import ch.nolix.element.widget.Checkbox;

//test class
/**
 * A {@link CheckboxTest} is a test for {@link Checkbox}.
 * 
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 60
 */
public final class CheckboxTest extends WidgetTest<Checkbox> {
	
	//test case
	public void testCase_creation() {
		
		//execution
		final var checkBox = new Checkbox();
		
		//verification
		expectNot(checkBox.isChecked());
	}
	
	//test case
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
	
	//test case
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
