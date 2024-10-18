package ch.nolix.system.application.component;

import java.util.Optional;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.applicationapi.componentapi.IComponent;
import ch.nolix.systemapi.applicationapi.componentapi.IComponentStyle;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlCssBuilder;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

public abstract class BaseComponent<C extends Controller<AC>, AC>
extends Control<IComponent, IComponentStyle>
implements IComponent {

  private static final BaseComponentHtmlBuilder HTML_BUILDER = new BaseComponentHtmlBuilder();

  private static final BaseComponentCssBuilder CSS_BUILDER = new BaseComponentCssBuilder();

  private final C controller;

  protected BaseComponent(final C controller, final WebClientSession<AC> webClientSession) {

    GlobalValidator.assertThat(controller).thatIsNamed(Controller.class).isNotNull();

    this.controller = controller;
    this.controller.internalSetSession(webClientSession);
  }

  @Override
  public Optional<String> getOptionalJavaScriptUserInputFunction() {
    return Optional.empty();
  }

  @Override
  public final String getUserInput() {
    return StringCatalogue.EMPTY_STRING;
  }

  @Override
  public final boolean hasRole(final String role) {
    return false;
  }

  @Override
  public final boolean isAlive() {
    return getStoredWebClientSession().isAlive();
  }

  @Override
  public final void refresh() {
    switch (getRefreshBehavior()) {
      case REFRESH_GUI:
        rebuild();
        getStoredWebClientSession().refresh();
        break;
      case REFRESH_SELF:
        rebuild();
        getStoredWebClientSession().updateControlOnCounterpart(this, true);
        break;
      case DO_NOT_REFRESH_ANYTHING:
        break;
    }
  }

  @Override
  public final void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    //Does nothing.
  }

  @Override
  public final void runHtmlEvent(final String htmlEvent) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "runHtmlEvent");
  }

  @Override
  public final IComponent setUserInput(final String userInput) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "setUserInput");
  }

  @Override
  protected IComponentStyle createStyle() {
    return new BaseComponentStyle();
  }

  @Override
  protected final IControlCssBuilder<IComponent, IComponentStyle> getCssBuilder() {
    return CSS_BUILDER;
  }

  @Override
  protected final IControlHtmlBuilder<IComponent> getHtmlBuilder() {
    return HTML_BUILDER;
  }

  protected final AC getStoredApplicationContext() {
    return getStoredController().getStoredApplicationContext();
  }

  protected final C getStoredController() {
    return controller;
  }

  protected final WebClientSession<AC> getStoredWebClientSession() {
    return getStoredController().getStoredWebClientSession();
  }

  @Override
  protected final void resetControl() {
    rebuild();
  }
}
