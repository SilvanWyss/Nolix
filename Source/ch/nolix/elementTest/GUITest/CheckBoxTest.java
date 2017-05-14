//package declaration
package ch.nolix.elementTest.GUITest;

//own imports
import ch.nolix.core.zetaTest.ZetaTest;
import ch.nolix.element.GUI.CheckBox;

//test class
/**
 * This class is a test class for the check box class.
 * 
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 50
 */
public final class CheckBoxTest extends ZetaTest {

	//test method
	public void test_constructor() {
		
		//execution
		final CheckBox checkBox = new CheckBox();
		
		//verification
		expectThatNot(checkBox.isChecked());
	}
	
	//test method
	public void test_check() {
		
		//setup
		final CheckBox checkBox = new CheckBox();
		
		//execution
		checkBox.check();
		
		//verification
		expectThat(checkBox.isChecked());
	}
	
	//test method
	public void test_uncheck() {
		
		//setup
		final CheckBox checkBox = new CheckBox();
		
		//execution
		checkBox.check();
		checkBox.uncheck();
		
		//verification
		expectThatNot(checkBox.isChecked());
	}
}
