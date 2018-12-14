//package declaration
package ch.nolix.elementTest.GUITest;

//own imports
import ch.nolix.core.test2.Test;
import ch.nolix.element.GUI.Checkbox;

//test class
/**
 * A {@link CheckboxTest} is a test for {@link Checkbox}.
 * 
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 50
 */
public final class CheckboxTest extends Test {
	
	//test case
	public void testCase_constructor() {
		
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
}
