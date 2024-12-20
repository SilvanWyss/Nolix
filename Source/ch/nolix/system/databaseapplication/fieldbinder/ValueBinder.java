package ch.nolix.system.databaseapplication.fieldbinder;

import ch.nolix.system.webgui.atomiccontrol.Textbox;
import ch.nolix.systemapi.objectdataapi.dataapi.IValue;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ITextbox;

public final class ValueBinder extends FieldBinder<IValue<?>, ITextbox> {

  @Override
  protected void addSelectionOptionsToControlForField(final ITextbox control, final IValue<?> property) {
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
  protected void updateFieldFromControl(final IValue<?> value, final ITextbox textBox) {
    value.setValueFromString(textBox.getText());
  }

  @Override
  protected void updateControlFromField(final ITextbox textBox, final IValue<?> value) {
    if (value.containsAny()) {
      textBox.setText(value.getStoredValue().toString());
    }
  }
}
