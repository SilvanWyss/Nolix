//package declaration
package ch.nolix.system.databaseapplication.propertybinder;

import ch.nolix.core.container.singlecontainer.SingleContainer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.webgui.control.Label;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.webguiapi.controlapi.ILabel;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public final class PropertyBinding {
	
	//attributes
	private final IProperty property;
	private final IControl<?, ?> control;
	private final ILabel<?, ?> errorText = new Label().setInvisible();
	
	//optional attribute
	private Throwable currentError;
	
	//constructors
	public PropertyBinding(final IProperty property, final IControl<?, ?> control) {
		
		GlobalValidator.assertThat(property).thatIsNamed(IProperty.class).isNotNull();
		GlobalValidator.assertThat(control).thatIsNamed(IControl.class).isNotNull();
		
		this.property = property;
		this.control = control;
	}
	
	//method
	public SingleContainer<Throwable> getOptionalCurrentError() {
		
		if (!hasCurrentError()) {
			return new SingleContainer<>();
		}
		
		return new SingleContainer<>(currentError);
	}
	
	//method
	public ILabel<?, ?> getRefErrorLabel() {
		return errorText;
	}
	
	//method
	public IProperty getRefProperty() {
		return property;
	}
	
	//method
	public IControl<?, ?> getRefControl() {
		return control;
	}
	
	//method
	public boolean hasCurrentError() {
		return (currentError != null);
	}
	
	//method
	void removeCurrentError() {
		
		currentError = null;
		
		errorText
		.setInvisible()
		.setText(Label.DEFAULT_TEXT);
	}
	
	//method
	void setCurrentError(final Throwable currentError) {
		
		GlobalValidator.assertThat(currentError).thatIsNamed("current error").isNotNull();
		
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
		errorText
		.setVisible()
		.setText(getErrorMessageFrom(error));
	}
}
