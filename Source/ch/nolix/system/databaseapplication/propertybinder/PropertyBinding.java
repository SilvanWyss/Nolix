//package declaration
package ch.nolix.system.databaseapplication.propertybinder;

//own imports
import ch.nolix.core.container.SingleContainer;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.element.gui.widget.Label;
import ch.nolix.element.gui.widget.LabelRole;
import ch.nolix.element.gui.widget.Widget;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;

//class
public final class PropertyBinding {
	
	//attributes
	private final IProperty<?> property;
	private final Widget<?, ?> widget;
	private final Label errorLabel = new Label().setRole(LabelRole.ERROR_TEXT);
	
	//optional attribute
	private Throwable currentError;
	
	//constructors
	public PropertyBinding(final IProperty<?> property, final Widget<?, ?> widget) {
		
		Validator.assertThat(property).thatIsNamed(IProperty.class).isNotNull();
		Validator.assertThat(widget).thatIsNamed(Widget.class).isNotNull();
		
		this.property = property;
		this.widget = widget;
		
		errorLabel.setCollapsed();
	}
	
	//method
	public SingleContainer<Throwable> getOptionalCurrentError() {
		
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
	public IProperty<?> getRefProperty() {
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
	
	//method
	void removeCurrentError() {
		currentError = null;
		errorLabel.emptyText();
		errorLabel.setCollapsed();
	}
	
	//method
	void setCurrentError(final Throwable currentError) {
		
		Validator.assertThat(currentError).thatIsNamed("current error").isNotNull();
		
		this.currentError = currentError;
		updateErrorLabelFrom(currentError);
	}
	
	//method
	private String getDefaultErrorMessage() {
		return ("The " + property.getName() + " is not valid.");
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
