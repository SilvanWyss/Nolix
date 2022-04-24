//package declaration
package ch.nolix.elementtest.guitest.widgettest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.system.gui.widget.CheckBox;

//class
/**
 * A {@link CheckBoxTest} is a test for {@link CheckBox}.
 * 
 * @author Silvan Wyss
 * @date 2017-03-12
 */
public final class CheckBoxTest extends WidgetTest<CheckBox> {
	
	//method
	@TestCase
	public void testCase_creation() {
		
		//execution
		final var checkBox = new CheckBox();
		
		//verification
		expectNot(checkBox.isChecked());
	}
	
	//method
	@TestCase
	public void testCase_check() {
		
		//setup
		final var checkBox = new CheckBox();
		
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
		final var checkBox = new CheckBox();
		checkBox.check();
				
		//execution		
		checkBox.uncheck();
		
		//verification
		expectNot(checkBox.isChecked());
	}
	
	//method
	@Override
	protected CheckBox createTestUnit() {
		return new CheckBox();
	}
}
