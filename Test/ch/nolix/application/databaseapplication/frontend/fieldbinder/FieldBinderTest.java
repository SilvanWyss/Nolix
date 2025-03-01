package ch.nolix.application.databaseapplication.frontend.fieldbinder;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.model.Value;
import ch.nolix.system.webgui.atomiccontrol.textbox.Textbox;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.textboxapi.ITextbox;

final class FieldBinderTest extends StandardTest {

  @Test
  void testCase_createControlAndBindItWith() {

    //setup
    final var value = Value.withInitialValue("");

    //execution
    final var result = FieldBinder.createControlAndBindItWith(value);

    //verification part 1
    expect(result.getStoredField()).is(value);
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

  @Test
  void testCase_createControlAndBindItWith_whenPropertyDoesNotHaveInitialValue() {

    //setup
    final var value = Value.withValueType(String.class);

    //setup verification
    expect(value.isEmpty());

    //execution
    final var result = FieldBinder.createControlAndBindItWith(value);

    //verification
    expect(result.getStoredField()).is(value);
    expect(result.getStoredControl()).isOfType(Textbox.class);
    final var textBox = (ITextbox) result.getStoredControl();
    expect(value.isEmpty()).isTrue();
    expect(textBox.getText()).isEqualTo("");
  }

  @Test
  void testCase_createControlAndBindItWith_whenPropertyHasInitialValue() {

    //setup
    final var value = Value.withInitialValue("zebra");

    //execution
    final var result = FieldBinder.createControlAndBindItWith(value);

    //verification
    expect(result.getStoredField()).is(value);
    expect(result.getStoredControl()).isOfType(Textbox.class);
    final var textBox = (ITextbox) result.getStoredControl();
    expect(value.getStoredValue()).isEqualTo("zebra");
    expect(textBox.getText()).isEqualTo("zebra");
  }
}
