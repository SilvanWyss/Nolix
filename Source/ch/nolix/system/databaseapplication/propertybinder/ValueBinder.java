//package declaration
package ch.nolix.system.databaseapplication.propertybinder;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.system.webgui.atomiccontrol.Textbox;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IValue;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ITextbox;

//class
public final class ValueBinder extends PropertyBinder<IValue<?>, ITextbox> {

  // attribute
  @Override
  protected void addSelectionOptionsToControlForProperty(final ITextbox control, final IValue<?> property) {
    // Does nothing.
  }

  // method
  @Override
  protected ITextbox createControl() {
    return new Textbox();
  }

  // method
  @Override
  protected void setNoteUpdateActionToControl(final ITextbox textBox, final IAction noteUpdateAction) {
    textBox.setUpdateTextAction(noteUpdateAction);
  }

  // method
  @Override
  protected void updatePropertyFromControl(final IValue<?> value, final ITextbox textBox) {
    value.setValueFromString(textBox.getText());
  }

  // method
  @Override
  protected void updateControlFromProperty(final ITextbox textBox, final IValue<?> value) {
    if (value.containsAny()) {
      textBox.setText(value.getStoredValue().toString());
    }
  }
}
