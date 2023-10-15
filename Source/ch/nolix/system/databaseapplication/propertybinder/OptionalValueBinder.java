//package declaration
package ch.nolix.system.databaseapplication.propertybinder;

import ch.nolix.system.webgui.atomiccontrol.Textbox;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalValue;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ITextbox;

//class
public class OptionalValueBinder extends PropertyBinder<IOptionalValue<?>, ITextbox> {

  // method
  @Override
  protected void addSelectionOptionsToControlForProperty(
      final ITextbox control,
      final IOptionalValue<?> optionalValue) {
    // Does nothing.
  }

  // method
  @Override
  protected ITextbox createControl() {
    return new Textbox();
  }

  // method
  @Override
  protected void setNoteUpdateActionToControl(final ITextbox textBox, final Runnable noteUpdateAction) {
    textBox.setUpdateTextAction(noteUpdateAction);
  }

  // method
  @Override
  protected void updatePropertyFromControl(final IOptionalValue<?> optionalValue, final ITextbox textBox) {
    if (textBox.getText().isEmpty()) {
      optionalValue.clear();
    } else {
      optionalValue.setValueFromString(textBox.getText());
    }
  }

  // method
  @Override
  protected void updateControlFromProperty(final ITextbox textBox, final IOptionalValue<?> optionalValue) {
    if (optionalValue.isEmpty()) {
      textBox.emptyText();
    } else {
      textBox.setText(optionalValue.getStoredValue().toString());
    }
  }
}
