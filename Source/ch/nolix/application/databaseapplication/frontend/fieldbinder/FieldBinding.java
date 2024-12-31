package ch.nolix.application.databaseapplication.frontend.fieldbinder;

import java.util.Optional;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.webgui.atomiccontrol.label.Label;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.labelapi.ILabel;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public final class FieldBinding {

  private final IField field;

  private final IControl<?, ?> control;

  private final ILabel errorText = new Label().setInvisible();

  private Throwable currentError;

  public FieldBinding(final IField field, final IControl<?, ?> control) {

    GlobalValidator.assertThat(field).thatIsNamed(IField.class).isNotNull();
    GlobalValidator.assertThat(control).thatIsNamed(IControl.class).isNotNull();

    this.field = field;
    this.control = control;
  }

  public Optional<Throwable> getOptionalCurrentError() {

    if (!hasCurrentError()) {
      return Optional.empty();
    }

    return Optional.of(currentError);
  }

  public IControl<?, ?> getStoredControl() {
    return control;
  }

  public ILabel getStoredErrorLabel() {
    return errorText;
  }

  public IField getStoredField() {
    return field;
  }

  public boolean hasCurrentError() {
    return (currentError != null);
  }

  void removeCurrentError() {

    currentError = null;

    errorText
      .setInvisible()
      .setText(Label.DEFAULT_TEXT);
  }

  void setCurrentError(final Throwable currentError) {

    GlobalValidator.assertThat(currentError).thatIsNamed("current error").isNotNull();

    this.currentError = currentError;
    updateErrorLabelFrom(currentError);
  }

  private String getDefaultErrorMessage() {
    return ("The " + field.getName() + " is not valid.");
  }

  private String getErrorMessageFrom(final Throwable error) {

    final var errorMessage = error.getMessage();

    if (errorMessage == null || errorMessage.isBlank()) {
      return getDefaultErrorMessage();
    }

    return errorMessage;
  }

  private void updateErrorLabelFrom(final Throwable error) {
    errorText
      .setVisible()
      .setText(getErrorMessageFrom(error));
  }
}
