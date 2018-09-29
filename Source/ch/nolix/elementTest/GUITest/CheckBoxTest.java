//package declaration
package ch.nolix.elementTest.GUITest;

import ch.nolix.core.test2.Test;
//own imports
import ch.nolix.element.GUI.Checkbox;

//test class
/**
 * A {@link CheckboxTest} is a test for a {@link Checkbox}.
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
		
		//execution
		checkBox.check();
		
		//verification
		expect(checkBox.isChecked());
	}
	
	//test case
	public void testCase_uncheck() {
		
		//setup
		final var checkBox = new Checkbox();
		checkBox.check();
		
		//execution		
		checkBox.uncheck();
		
		//verification
		expectNot(checkBox.isChecked());
	}
}
