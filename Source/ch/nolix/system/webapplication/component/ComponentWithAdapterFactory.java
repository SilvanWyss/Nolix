package ch.nolix.system.webapplication.component;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.programcontrol.adapter.IAdapterFactory;
import ch.nolix.system.webapplication.main.WebClientSession;
import ch.nolix.systemapi.webgui.main.IControl;

public abstract class ComponentWithAdapterFactory //NOSONAR: A component class is expected to be abstract.
<C extends Controller<S>, S extends IAdapterFactory<A>, A>
extends AbstractComponent<C, S> {

  private IControl<?, ?> childControl;

  protected ComponentWithAdapterFactory(
    final C controller,
    final A initialAdapter,
    final WebClientSession<S> webClientSession) {

    super(controller, webClientSession);

    rebuild(initialAdapter);
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

    final var adapter = createAdapter();

    rebuild(adapter);
  }

  protected abstract IControl<?, ?> createControl(C controller, A adapter);

  private A createAdapter() {
    return getStoredApplicationService().createAdapter();
  }

  private void rebuild(final A adapter) {
    childControl = createControl(getStoredController(), adapter);
    childControl.internalSetParentControl(this);
    childControl.linkTo(this);
  }
}
