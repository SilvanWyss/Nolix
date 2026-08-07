/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webapplication.component;

import java.util.Optional;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;
import ch.nolix.baseapi.net.sessionserver.IApplication;
import ch.nolix.system.webapplication.main.WebClientSession;
import ch.nolix.system.webgui.main.AbstractControl;
import ch.nolix.system.webgui.main.ControlParent;
import ch.nolix.systemapi.webapplication.component.IComponent;
import ch.nolix.systemapi.webapplication.component.IComponentStyle;
import ch.nolix.systemapi.webgui.controltool.IControlCssBuilder;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;
import ch.nolix.systemapi.webgui.html.IHtmlElementEvent;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 * @param <C> the type of the {@link Controller} of a {@link Component}.
 * @param <S> the type of the application service of the parent
 *            {@link IApplication} of the parent web client of the parent web
 *            client session of the parent root GUI of a {@link Component}.
 * 
 */
public abstract class Component<C extends Controller<S>, S> // NOSONAR: A component class is expected to be abstract.
extends AbstractControl<IComponent, IComponentStyle>
implements IComponent {
  private static final ComponentHtmlBuilder HTML_BUILDER = new ComponentHtmlBuilder();

  private static final ComponentCssBuilder CSS_BUILDER = new ComponentCssBuilder();

  private final C memberController;

  private Control<?, ?> childControl;

  protected Component(final C controller, final WebClientSession<S> webClientSession) {
    Validator.assertThat(controller).thatIsNamed(Controller.class).isNotNull();

    memberController = controller;
    memberController.setWebClientSession(webClientSession);

    rebuild();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<String> getOptionalJavaScriptUserInputFunction() {
    return Optional.empty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<Control<?, ?>> getStoredChildControls() {
    if (childControl == null) {
      return ImmutableList.createEmpty();
    }

    return ImmutableList.withElements(childControl);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getUserInput() {
    return StringCatalog.EMPTY_STRING;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasRole(final String role) {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isAlive() {
    return getStoredWebClientSession().isAlive();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void rebuild() {
    final var controlParent = ControlParent.forControl(this);

    childControl = createControl(getStoredController());
    childControl.internalSetControlParent(controlParent);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void refresh() {
    final var refreshBehavior = getRefreshTrigger();

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

  /**
   * {@inheritDoc}
   */
  @Override
  public final void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void runHtmlEvent(final String htmlEvent) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "runHtmlEvent");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IComponent setUserInput(final String userInput) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "setUserInput");
  }

  protected abstract Control<?, ?> createControl(C controller);

  @Override
  protected final IComponentStyle createStyle() {
    return new ComponentStyle();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected final IControlCssBuilder<IComponent, IComponentStyle> getCssBuilder() {
    return CSS_BUILDER;
  }

  /**
   * {@inheritDoc}
   */
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

  /**
   * {@inheritDoc}
   */
  @Override
  protected final void resetControl() {
    rebuild();
  }
}
