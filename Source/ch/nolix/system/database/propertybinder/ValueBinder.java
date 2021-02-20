//package declaration
package ch.nolix.system.database.propertybinder;

//own imports
import ch.nolix.common.functionapi.IAction;
import ch.nolix.element.widget.TextBox;
import ch.nolix.system.entity.Value;

//class
public final class ValueBinder extends PropertyBinder<Value<?>, TextBox> {
	
	@Override
	protected void addSelectionOptionsToWidgetForProperty(final TextBox widget, final Value<?> property) {}
	
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
	protected void updatePropertyFromWidget(final Value<?> value, final TextBox textBox) {
		value.setValueFromString(textBox.getText());
	}
	
	//method
	@Override
	protected void updateWidgetFromProperty(final TextBox textBox, final Value<?> value) {
		if (value.hasValue()) {
			textBox.setText(value.getValue().toString());
		}
	}
}
