//package declaration
package ch.nolix.system.databaseapplication.propertybinder;

//own imports
import ch.nolix.core.container.main.SingleContainer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.webgui.control.Text;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;
import ch.nolix.systemapi.webguiapi.controlapi.IText;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public final class PropertyBinding {
	
	//attributes
	private final IProperty<?> property;
	private final IControl<?, ?> control;
	private final IText<?, ?> errorText = new Text().setInvisible();
	
	//optional attribute
	private Throwable currentError;
	
	//constructors
	public PropertyBinding(final IProperty<?> property, final IControl<?, ?> control) {
		
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
	public IText<?, ?> getRefErrorLabel() {
		return errorText;
	}
	
	//method
	public IProperty<?> getRefProperty() {
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
		.setText(Text.DEFAULT_TEXT);
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
