package ch.nolix.system.webgui.atomiccontrol.validationlabel;

import java.util.Optional;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalogue;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.system.graphic.color.X11ColorCatalogue;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.validationlabelapi.IValidationLabel;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.validationlabelapi.IValidationLabelStyle;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlCssBuilder;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

public final class ValidationLabel
extends Control<IValidationLabel, IValidationLabelStyle>
implements IValidationLabel {

  private static final String ERROR_HEADER = PascalCaseVariableCatalogue.ERROR;

  private final MutableOptionalValue<Throwable> error = new MutableOptionalValue<>(
    ERROR_HEADER,
    this::showError,
    s -> GeneralException.withErrorMessage(s.getHeader()),
    e -> Node.withHeader(e.getMessage()));

  public ValidationLabel() {

    //A reset is required to achieve a well-defined initial state, although everything would work without a reset.
    reset();
  }

  @Override
  public void clear() {
    error.clear();
  }

  @Override
  public Throwable getError() {
    return error.getValue();
  }

  @Override
  public Optional<String> getOptionalJavaScriptUserInputFunction() {
    return Optional.empty();
  }

  @Override
  public IContainer<IControl<?, ?>> getStoredChildControls() {
    return ImmutableList.createEmpty();
  }

  @Override
  public String getUserInput() {
    return StringCatalogue.EMPTY_STRING;
  }

  @Override
  public boolean hasRole(final String role) {
    return false;
  }

  @Override
  public boolean isEmpty() {
    return error.isEmpty();
  }

  @Override
  public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    //Does nothing.
  }

  @Override
  public void runHtmlEvent(String htmlEvent) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "runHtmlEvent");
  }

  @Override
  public ValidationLabel setUserInput(final String userInput) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "setUserInput");
  }

  @Override
  public void showError(final Throwable error) {
    this.error.setValue(error);
  }

  @Override
  protected ValidationLabelStyle createStyle() {
    return new ValidationLabelStyle();
  }

  @Override
  protected IControlCssBuilder<IValidationLabel, IValidationLabelStyle> getCssBuilder() {
    return new ValidationLabelCssBuilder();
  }

  @Override
  protected IControlHtmlBuilder<IValidationLabel> getHtmlBuilder() {
    return new ValidationLabelHtmlBuilder();
  }

  @Override
  protected void resetControl() {

    clear();

    getStoredStyle().setTextColorForState(ControlState.BASE, X11ColorCatalogue.RED);
  }
}
