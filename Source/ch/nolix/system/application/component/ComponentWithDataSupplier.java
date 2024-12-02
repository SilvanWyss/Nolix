package ch.nolix.system.application.component;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programstructureapi.dataapi.IDataSupplierFactory;
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public abstract class ComponentWithDataSupplier<C extends Controller<AS>, AS extends IDataSupplierFactory<DS>, DS>
extends BaseComponent<C, AS> {

  private IControl<?, ?> childControl;

  protected ComponentWithDataSupplier(
    final C controller,
    final DS initialDataSupplier,
    final WebClientSession<AS> webClientSession) {

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

    final var dataSupplier = createDataSupplier();

    rebuild(dataSupplier);
  }

  protected abstract IControl<?, ?> createControl(C controller, DS dataAdapter);

  private DS createDataSupplier() {
    return getStoredApplicationContext().createDataSupplier();
  }

  private void rebuild(final DS dataSupplier) {
    childControl = createControl(getStoredController(), dataSupplier);
    childControl.internalSetParentControl(this);
    childControl.linkTo(this);
  }
}
