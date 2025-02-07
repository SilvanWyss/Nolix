package ch.nolix.system.application.component;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programcontrolapi.adapterapi.IAdapterFactory;
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

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
