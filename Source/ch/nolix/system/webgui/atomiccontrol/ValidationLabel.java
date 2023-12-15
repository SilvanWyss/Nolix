//package declaration
package ch.nolix.system.webgui.atomiccontrol;

//Java imports
import java.util.Optional;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.coreapi.commontypeapi.stringutilapi.StringCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.variablenameapi.PascalCaseCatalogue;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IValidationLabel;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IValidationLabelStyle;
import ch.nolix.systemapi.webguiapi.controlserviceapi.IControlCssBuilder;
import ch.nolix.systemapi.webguiapi.controlserviceapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

//class
public final class ValidationLabel
extends Control<IValidationLabel, IValidationLabelStyle>
implements IValidationLabel {

  //constant
  private static final String ERROR_HEADER = PascalCaseCatalogue.ERROR;

  //attribute
  private final MutableOptionalValue<Throwable> error = new MutableOptionalValue<>(
    ERROR_HEADER,
    this::showError,
    s -> GeneralException.withErrorMessage(s.getHeader()),
    e -> Node.withHeader(e.getMessage()));

  //constructor
  public ValidationLabel() {

    //Info: Reset is technically optional, but required to achieve a well-defined
    //initial state.
    reset();
  }

  //method
  @Override
  public void clear() {
    error.clear();
  }

  //method
  @Override
  public Throwable getError() {
    return error.getValue();
  }

  //method
  @Override
  public Optional<String> getOptionalJavaScriptUserInputFunction() {
    return Optional.empty();
  }

  //method
  @Override
  public IContainer<IControl<?, ?>> getStoredChildControls() {
    return new ImmutableList<>();
  }

  //method
  @Override
  public String getUserInput() {
    return StringCatalogue.EMPTY_STRING;
  }

  //method
  @Override
  public boolean hasRole(final String role) {
    return false;
  }

  //method
  @Override
  public boolean isEmpty() {
    return error.isEmpty();
  }

  //method
  @Override
  public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    //Does nothing.
  }

  //method
  @Override
  public void runHtmlEvent(String htmlEvent) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "runHtmlEvent");
  }

  //method
  @Override
  public ValidationLabel setUserInput(final String userInput) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "setUserInput");
  }

  //method
  @Override
  public void showError(final Throwable error) {
    this.error.setValue(error);
  }

  //method
  @Override
  protected ValidationLabelStyle createStyle() {
    return new ValidationLabelStyle();
  }

  //method
  @Override
  protected IControlCssBuilder<IValidationLabel, IValidationLabelStyle> getCssBuilder() {
    return new ValidationLabelCssBuilder();
  }

  //method
  @Override
  protected IControlHtmlBuilder<IValidationLabel> getHtmlBuilder() {
    return new ValidationLabelHtmlBuilder();
  }

  //method
  @Override
  protected void resetControl() {

    clear();

    getStoredStyle().setTextColorForState(ControlState.BASE, Color.RED);
  }
}
