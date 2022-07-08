//package declaration
package ch.nolix.system.databaseapplication.propertybinder;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.system.gui.textbox.TextBox;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalValue;

//class
public class OptionalValueBinder extends PropertyBinder<IOptionalValue<?, ?>, TextBox> {
	
	//method
	@Override
	protected void addSelectionOptionsToWidgetForProperty(
		final TextBox widget,
		final IOptionalValue<?, ?> optionalValue
	) {
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
	protected void updatePropertyFromWidget(final IOptionalValue<?, ?> optionalValue, final TextBox textBox) {
		if (textBox.getText().isEmpty()) {
			optionalValue.clear();
		} else {
			optionalValue.setValueFromStringRepresentation(textBox.getText());
		}
	}
	
	//method
	@Override
	protected void updateWidgetFromProperty(final TextBox textBox, final IOptionalValue<?, ?> optionalValue) {
		if (optionalValue.isEmpty()) {
			textBox.emptyText();
		} else {
			textBox.setText(optionalValue.getRefValue().toString());
		}
	}
}
