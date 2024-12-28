package ch.nolix.application.databaseapplication.frontend.fieldbinder;

import ch.nolix.system.webgui.atomiccontrol.Textbox;
import ch.nolix.systemapi.objectdataapi.modelapi.IOptionalValue;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ITextbox;

public class OptionalValueBinder extends FieldBinder<IOptionalValue<?>, ITextbox> {

  @Override
  protected void addSelectionOptionsToControlForField(
    final ITextbox control,
    final IOptionalValue<?> optionalValue) {
    //Does nothing.
  }

  @Override
  protected ITextbox createControl() {
    return new Textbox();
  }

  @Override
  protected void setNoteUpdateActionToControl(final ITextbox textBox, final Runnable noteUpdateAction) {
    textBox.setUpdateTextAction(noteUpdateAction);
  }

  @Override
  protected void updateFieldFromControl(final IOptionalValue<?> optionalValue, final ITextbox textBox) {
    if (textBox.getText().isEmpty()) {
      optionalValue.clear();
    } else {
      optionalValue.setValueFromString(textBox.getText());
    }
  }

  @Override
  protected void updateControlFromField(final ITextbox textBox, final IOptionalValue<?> optionalValue) {
    if (optionalValue.isEmpty()) {
      textBox.emptyText();
    } else {
      textBox.setText(optionalValue.getStoredValue().toString());
    }
  }
}
