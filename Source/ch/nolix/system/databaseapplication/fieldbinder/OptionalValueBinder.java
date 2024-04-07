//package declaration
package ch.nolix.system.databaseapplication.fieldbinder;

//own imports
import ch.nolix.system.webgui.atomiccontrol.Textbox;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalValue;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ITextbox;

//class
public class OptionalValueBinder extends FieldBinder<IOptionalValue<?>, ITextbox> {

  //method
  @Override
  protected void addSelectionOptionsToControlForField(
    final ITextbox control,
    final IOptionalValue<?> optionalValue) {
    //Does nothing.
  }

  //method
  @Override
  protected ITextbox createControl() {
    return new Textbox();
  }

  //method
  @Override
  protected void setNoteUpdateActionToControl(final ITextbox textBox, final Runnable noteUpdateAction) {
    textBox.setUpdateTextAction(noteUpdateAction);
  }

  //method
  @Override
  protected void updateFieldFromControl(final IOptionalValue<?> optionalValue, final ITextbox textBox) {
    if (textBox.getText().isEmpty()) {
      optionalValue.clear();
    } else {
      optionalValue.setValueFromString(textBox.getText());
    }
  }

  //method
  @Override
  protected void updateControlFromField(final ITextbox textBox, final IOptionalValue<?> optionalValue) {
    if (optionalValue.isEmpty()) {
      textBox.emptyText();
    } else {
      textBox.setText(optionalValue.getStoredValue().toString());
    }
  }
}
