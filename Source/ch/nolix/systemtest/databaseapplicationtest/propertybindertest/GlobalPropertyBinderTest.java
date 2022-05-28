//package declaration
package ch.nolix.systemtest.databaseapplicationtest.propertybindertest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.databaseapplication.propertybinder.GlobalPropertyBinder;
import ch.nolix.system.gui.textbox.TextBox;
import ch.nolix.system.objectdata.data.Value;

//class
public final class GlobalPropertyBinderTest extends Test {
	
	//method
	@TestCase
	public void testCase_createWidgetAndBindItWith() {
		
		//setup
		final var value = new Value<String>();
		value.setValue("");
		
		//execution
		final var result = GlobalPropertyBinder.createWidgetAndBindItWith(value);
		
		//verification part 1
		expect(result.getRefProperty()).isSameAs(value);
		expect(result.getRefWidget()).isOfType(TextBox.class);
		final var textBox = (TextBox)result.getRefWidget();
		expect(value.getRefValue()).isEqualTo("");
		expect(textBox.getText()).isEqualTo("");
		
		//verification part 2
		value.setValue("");
		textBox.emptyText();
		value.setValue("zebra");
		expect(value.getRefValue()).isEqualTo("zebra");
		expect(textBox.getText()).isEqualTo("zebra");
		
		//verification part 3
		value.setValue("");
		textBox.setText("");
		textBox.setText("zebra");
		expect(value.getRefValue()).isEqualTo("zebra");
		expect(textBox.getText()).isEqualTo("zebra");
		
		//verification part 4
		value.setValue("zebra");
		textBox.setText("zebra");
		textBox.setText("");
		expect(value.getRefValue()).isEqualTo("");
		expect(textBox.getText()).isEqualTo("");
	}
	
	//method
	@TestCase
	public void testCase_createWidgetAndBindItWith_whenPropertyDoesNotHaveInitialValue() {
		
		//setup
		final var value = new Value<String>();
		
		//setup verification
		expect(value.isEmpty());
		
		//execution
		final var result = GlobalPropertyBinder.createWidgetAndBindItWith(value);
		
		//verification
		expect(result.getRefProperty()).isSameAs(value);
		expect(result.getRefWidget()).isOfType(TextBox.class);
		final var textBox = (TextBox)result.getRefWidget();
		expect(value.isEmpty());
		expect(textBox.getText()).isEqualTo("");
	}
	
	//method
	@TestCase
	public void testCase_createWidgetAndBindItWith_whenPropertyHasInitialValue() {
		
		//setup
		final var value = new Value<String>();
		value.setValue("zebra");
		
		//execution
		final var result = GlobalPropertyBinder.createWidgetAndBindItWith(value);
		
		//verification
		expect(result.getRefProperty()).isSameAs(value);
		expect(result.getRefWidget()).isOfType(TextBox.class);
		final var textBox = (TextBox)result.getRefWidget();
		expect(value.getRefValue()).isEqualTo("zebra");
		expect(textBox.getText()).isEqualTo("zebra");
	}
}
