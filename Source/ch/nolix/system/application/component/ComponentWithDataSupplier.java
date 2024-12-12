package ch.nolix.system.application.component;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programstructureapi.dataapi.IDataSupplierFactory;
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public abstract class ComponentWithDataSupplier<C extends Controller<S>, S extends IDataSupplierFactory<T>, T>
extends BaseComponent<C, S> {

  private IControl<?, ?> childControl;

  protected ComponentWithDataSupplier(
    final C controller,
    final T initialDataSupplier,
    final WebClientSession<S> webClientSession) {

    super(controller, webClientSession);

    rebuild(initialDataSupplier);
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

    final T dataSupplier = createDataSupplier();

    rebuild(dataSupplier);
  }

  protected abstract IControl<?, ?> createControl(C controller, T dataAdapter);

  private T createDataSupplier() {
    return getStoredApplicationContext().createDataSupplier();
  }

  private void rebuild(final T dataSupplier) {
    childControl = createControl(getStoredController(), dataSupplier);
    childControl.internalSetParentControl(this);
    childControl.linkTo(this);
  }
}
