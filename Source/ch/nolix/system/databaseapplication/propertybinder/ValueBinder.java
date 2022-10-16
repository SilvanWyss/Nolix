//package declaration
package ch.nolix.system.databaseapplication.propertybinder;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.system.webgui.control.Textbox;
import ch.nolix.systemapi.objectdataapi.dataapi.IValue;
import ch.nolix.systemapi.webguiapi.controlapi.ITextbox;

//class
public final class ValueBinder extends PropertyBinder<IValue<?, ?>, ITextbox<?, ?>> {
	
	//attribute
	@Override
	protected void addSelectionOptionsToWidgetForProperty(final ITextbox<?, ?> widget, final IValue<?, ?> property) {
		//Does nothing.
	}
	
	//method
	@Override
	protected ITextbox<?, ?> createWidget() {
		return new Textbox();
	}
	
	//method
	@Override
	protected void setNoteUpdateActionToWidget(final ITextbox<?, ?> textBox, final IAction noteUpdateAction) {
		textBox.setUpdateTextAction(noteUpdateAction);
	}
	
	//method
	@Override
	protected void updatePropertyFromWidget(final IValue<?, ?> value, final ITextbox<?, ?> textBox) {
		value.setValueFromStringRepresentation(textBox.getText());
	}
	
	//method
	@Override
	protected void updateWidgetFromProperty(final ITextbox<?, ?> textBox, final IValue<?, ?> value) {
		if (value.containsAny()) {
			textBox.setText(value.getRefValue().toString());
		}
	}
}
