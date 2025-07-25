package ch.nolix.system.webapplication.component;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.system.webapplication.main.WebClientSession;
import ch.nolix.systemapi.webgui.main.IControl;

public abstract class Component //NOSONAR: A component class is expected to be abstract.
<C extends Controller<S>, S> extends AbstractComponent<C, S> {

  private IControl<?, ?> childControl;

  protected Component(final C controller, final WebClientSession<S> webClientSession) {

    super(controller, webClientSession);

    rebuild();
  }

  @Override
  public final IContainer<IControl<?, ?>> getStoredChildControls() {

    if (childControl == null) {
      return ImmutableList.createEmpty();
    }

    return ImmutableList.withElement(childControl);
  }

  @Override
  public final void rebuild() {
    childControl = createControl(getStoredController());
    childControl.internalSetParentControl(this);
    childControl.linkTo(this);
  }

  protected abstract IControl<?, ?> createControl(C controller);
}
