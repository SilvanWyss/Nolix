//package declaration
package ch.nolix.system.databaseapplication.fieldbinder;

//Java imports
import java.util.Optional;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ILabel;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public final class FieldBinding {

  //attribute
  private final IField field;

  //attribute
  private final IControl<?, ?> control;

  //attribute
  private final ILabel errorText = new Label().setInvisible();

  //optional attribute
  private Throwable currentError;

  //constructors
  public FieldBinding(final IField field, final IControl<?, ?> control) {

    GlobalValidator.assertThat(field).thatIsNamed(IField.class).isNotNull();
    GlobalValidator.assertThat(control).thatIsNamed(IControl.class).isNotNull();

    this.field = field;
    this.control = control;
  }

  //method
  public Optional<Throwable> getOptionalCurrentError() {

    if (!hasCurrentError()) {
      return Optional.empty();
    }

    return Optional.of(currentError);
  }

  //method
  public IControl<?, ?> getStoredControl() {
    return control;
  }

  //method
  public ILabel getStoredErrorLabel() {
    return errorText;
  }

  //method
  public IField getStoredField() {
    return field;
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
    return ("The " + field.getName() + " is not valid.");
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
