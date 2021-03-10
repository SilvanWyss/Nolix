//package declaration
package ch.nolix.system.databaseapplication.propertybinder;

//own imports
import ch.nolix.common.functionapi.IAction;
import ch.nolix.element.gui.widget.TextBox;
import ch.nolix.system.database.entity.OptionalValue;

//class
public class OptionalValueBinder extends PropertyBinder<OptionalValue<?>, TextBox> {
	
	//method
	@Override
	protected void addSelectionOptionsToWidgetForProperty(
		final TextBox widget,
		final OptionalValue<?> optionalValue
	) {}
	
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
	protected void updatePropertyFromWidget(final OptionalValue<?> optionalValue, final TextBox textBox) {
		if (textBox.getText().isEmpty()) {
			optionalValue.clear();
		} else {
			optionalValue.setValueFromString(textBox.getText());
		}
	}
	
	//method
	@Override
	protected void updateWidgetFromProperty(final TextBox textBox, final OptionalValue<?> optionalValue) {
		if (optionalValue.isEmpty()) {
			textBox.emptyText();
		} else {
			textBox.setText(optionalValue.getValue().toString());
		}
	}
}
