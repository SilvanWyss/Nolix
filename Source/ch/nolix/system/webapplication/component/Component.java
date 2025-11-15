package ch.nolix.system.webapplication.component;

import java.util.Optional;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.commontypetool.stringtool.StringCatalog;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.system.webapplication.main.WebClientSession;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.webapplication.component.IComponent;
import ch.nolix.systemapi.webapplication.component.IComponentStyle;
import ch.nolix.systemapi.webgui.controltool.IControlCssBuilder;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.IHtmlElementEvent;

public abstract class Component<C extends Controller<S>, S> //NOSONAR: A component class is expected to be abstract.
extends Control<IComponent, IComponentStyle>
implements IComponent {
  private static final BaseComponentHtmlBuilder HTML_BUILDER = new BaseComponentHtmlBuilder();

  private static final BaseComponentCssBuilder CSS_BUILDER = new BaseComponentCssBuilder();

  private final C memberController;

  private IControl<?, ?> childControl;

  protected Component(final C controller, final WebClientSession<S> webClientSession) {
    Validator.assertThat(controller).thatIsNamed(Controller.class).isNotNull();

    memberController = controller;
    memberController.setWebClientSession(webClientSession);

    rebuild();
  }

  @Override
  public Optional<String> getOptionalJavaScriptUserInputFunction() {
    return Optional.empty();
  }

  @Override
  public final IContainer<IControl<?, ?>> getStoredChildControls() {
    if (childControl == null) {
      return ImmutableList.createEmpty();
    }
  
    return ImmutableList.withElements(childControl);
  }

  @Override
  public final String getUserInput() {
    return StringCatalog.EMPTY_STRING;
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
  public final void rebuild() {
    childControl = createControl(getStoredController());
    childControl.internalSetParentControl(this);
    childControl.linkTo(this);
  }

  @Override
  public final void refresh() {
    final var refreshBehavior = getRefreshBehavior();

    switch (refreshBehavior) {
      case REFRESH_GUI:
        rebuild();
        getStoredWebClientSession().refresh();
        break;
      case REFRESH_COMPONENT:
        rebuild();
        getStoredWebClientSession().updateControlOnCounterpart(this, true);
        break;
      case DO_NOT_REFRESH:
        break;
      default:
        throw InvalidArgumentException.forArgument(refreshBehavior);
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

  protected abstract IControl<?, ?> createControl(C controller);

  @Override
  protected final IComponentStyle createStyle() {
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

  protected final S getStoredApplicationService() {
    return getStoredController().getStoredApplicationService();
  }

  protected final C getStoredController() {
    return memberController;
  }

  protected final WebClientSession<S> getStoredWebClientSession() {
    return getStoredController().getStoredWebClientSession();
  }

  @Override
  protected final void resetControl() {
    rebuild();
  }
}
