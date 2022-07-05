//package declaration
package ch.nolix.system.databaseapplication.propertybinder;

import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.system.gui.textbox.TextBox;
import ch.nolix.systemapi.objectdataapi.dataapi.IValue;

//class
public final class ValueBinder extends PropertyBinder<IValue<?, ?>, TextBox> {
	
	//attribute
	@Override
	protected void addSelectionOptionsToWidgetForProperty(final TextBox widget, final IValue<?, ?> property) {
		//Does nothing.
	}
	
	//method
	@Override
	protected TextBox createWidget() {
		return new TextBox();
	}
	
	//method
	@Override
	protected void setNoteUpdateActionToWidget(final TextBox textBox, final IAction noteUpdateAction) {
		textBox.setNoteTextUpdateAction(noteUpdateAction);
	}
	
	//method
	@Override
	protected void updatePropertyFromWidget(final IValue<?, ?> value, final TextBox textBox) {
		value.setValueFromStringRepresentation(textBox.getText());
	}
	
	//method
	@Override
	protected void updateWidgetFromProperty(final TextBox textBox, final IValue<?, ?> value) {
		if (value.containsAny()) {
			textBox.setText(value.getRefValue().toString());
		}
	}
}
