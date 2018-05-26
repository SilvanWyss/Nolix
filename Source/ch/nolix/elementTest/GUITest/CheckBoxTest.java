//package declaration
package ch.nolix.elementTest.GUITest;

//own imports
import ch.nolix.element.GUI.Checkbox;
import ch.nolix.primitive.test2.Test;

//test class
/**
 * A {@link CheckboxTest} is a test for a {@link Checkbox}.
 * 
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 50
 */
public final class CheckboxTest extends Test {

	//test method
	public void test_constructor() {
		
		//execution
		final var checkBox = new Checkbox();
		
		//verification
		expectNot(checkBox.isChecked());
	}
	
	//test method
	public void test_check() {
		
		//setup
		final var checkBox = new Checkbox();
		
		//execution
		checkBox.check();
		
		//verification
		expect(checkBox.isChecked());
	}
	
	//test method
	public void test_uncheck() {
		
		//setup
		final var checkBox = new Checkbox();
		checkBox.check();
		
		//execution		
		checkBox.uncheck();
		
		//verification
		expectNot(checkBox.isChecked());
	}
}
