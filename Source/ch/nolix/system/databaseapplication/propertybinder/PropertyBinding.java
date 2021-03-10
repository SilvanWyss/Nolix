//package declaration
package ch.nolix.system.databaseapplication.propertybinder;

//own imports
import ch.nolix.common.container.SingleContainer;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.widget.Label;
import ch.nolix.element.gui.widget.LabelRole;
import ch.nolix.system.database.entity.Property;

//class
public final class PropertyBinding {
	
	//attributes
	private final Property<?> property;
	private final Widget<?, ?> widget;
	private final Label errorLabel = new Label().setRole(LabelRole.ERROR_TEXT);
	
	//optional attribute
	private Throwable currentError;
	
	//constructors
	public PropertyBinding(final Property<?> property, final Widget<?, ?> widget) {
		
		Validator.assertThat(property).thatIsNamed(Property.class).isNotNull();
		Validator.assertThat(widget).thatIsNamed(Widget.class).isNotNull();
		
		this.property = property;
		this.widget = widget;
		
		errorLabel.setCollapsed();
	}
	
	//method
	public SingleContainer<Throwable> getCurrentErrorOptionally() {
		
		if (!hasCurrentError()) {
			return new SingleContainer<>();
		}
		
		return new SingleContainer<>(currentError);
	}
	
	//method
	public Label getRefErrorLabel() {
		return errorLabel;
	}
	
	//method
	public Property<?> getRefProperty() {
		return property;
	}
	
	//method
	public Widget<?, ?> getRefWidget() {
		return widget;
	}
	
	//method
	public boolean hasCurrentError() {
		return (currentError != null);
	}
	
	//visibility-reduced method
	void removeCurrentError() {
		currentError = null;
		errorLabel.emptyText();
		errorLabel.setCollapsed();
	}
	
	//visibility-reduced method
	void setCurrentError(final Throwable currentError) {
		
		Validator.assertThat(currentError).thatIsNamed("current error").isNotNull();
		
		this.currentError = currentError;
		updateErrorLabelFrom(currentError);
	}
	
	//method
	private String getDefaultErrorMessage() {
		return ("The " + property.getHeader() + " is not valid.");
	}
	
	//method
	private String getErrorMessageFrom(final Throwable error) {
		
		final var errorMessage = error.getMessage();
		
		if (errorMessage == null || errorMessage.isBlank()) {
			return getDefaultErrorMessage();
		}
		
		return errorMessage;
	}
	
	//method
	private void updateErrorLabelFrom(final Throwable error) {
		errorLabel
		.setExpanded()
		.setText(getErrorMessageFrom(error));
	}
}
