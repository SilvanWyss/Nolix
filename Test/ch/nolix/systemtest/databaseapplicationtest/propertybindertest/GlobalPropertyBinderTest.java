//package declaration
package ch.nolix.systemtest.databaseapplicationtest.propertybindertest;

import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.system.databaseapplication.propertybinder.GlobalPropertyBinder;
import ch.nolix.system.objectdata.data.Value;
import ch.nolix.system.webgui.atomiccontrol.Textbox;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ITextbox;

//class
final class GlobalPropertyBinderTest extends StandardTest {

  //method
  @Test
  void testCase_createControlAndBindItWith() {

    //setup
    final var value = Value.withInitialValue("");

    //execution
    final var result = GlobalPropertyBinder.createControlAndBindItWith(value);

    //verification part 1
    expect(result.getStoredProperty()).is(value);
    expect(result.getStoredControl()).isOfType(Textbox.class);
    final var textBox = (ITextbox) result.getStoredControl();
    expect(value.getStoredValue()).isEqualTo("");
    expect(textBox.getText()).isEqualTo("");

    //verification part 2
    value.setValue("");
    textBox.emptyText();
    value.setValue("zebra");
    expect(value.getStoredValue()).isEqualTo("zebra");
    expect(textBox.getText()).isEqualTo("zebra");

    //verification part 3
    value.setValue("");
    textBox.setText("");
    textBox.setText("zebra");
    expect(value.getStoredValue()).isEqualTo("zebra");
    expect(textBox.getText()).isEqualTo("zebra");

    //verification part 4
    value.setValue("zebra");
    textBox.setText("zebra");
    textBox.setText("");
    expect(value.getStoredValue()).isEqualTo("");
    expect(textBox.getText()).isEqualTo("");
  }

  //method
  @Test
  void testCase_createControlAndBindItWith_whenPropertyDoesNotHaveInitialValue() {

    //setup
    final var value = Value.withValueType(String.class);

    //setup verification
    expect(value.isEmpty());

    //execution
    final var result = GlobalPropertyBinder.createControlAndBindItWith(value);

    //verification
    expect(result.getStoredProperty()).is(value);
    expect(result.getStoredControl()).isOfType(Textbox.class);
    final var textBox = (ITextbox) result.getStoredControl();
    expect(value.isEmpty());
    expect(textBox.getText()).isEqualTo("");
  }

  //method
  @Test
  void testCase_createControlAndBindItWith_whenPropertyHasInitialValue() {

    //setup
    final var value = Value.withInitialValue("zebra");

    //execution
    final var result = GlobalPropertyBinder.createControlAndBindItWith(value);

    //verification
    expect(result.getStoredProperty()).is(value);
    expect(result.getStoredControl()).isOfType(Textbox.class);
    final var textBox = (ITextbox) result.getStoredControl();
    expect(value.getStoredValue()).isEqualTo("zebra");
    expect(textBox.getText()).isEqualTo("zebra");
  }
}
