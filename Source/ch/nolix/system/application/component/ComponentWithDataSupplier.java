//package declaration
package ch.nolix.system.application.component;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programstructureapi.dataapi.IDataSupplierFactory;
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public abstract class ComponentWithDataSupplier<C extends Controller<AC>, AC extends IDataSupplierFactory<DS>, DS>
extends BaseComponent<C, AC> {

  //optional attribute
  private IControl<?, ?> childControl;

  //constructor
  protected ComponentWithDataSupplier(
    final C controller,
    final DS initialDataSupplier,
    final WebClientSession<AC> webClientSession) {

    super(controller, webClientSession);

    rebuild(initialDataSupplier);
  }

  //method
  @Override
  public final IContainer<IControl<?, ?>> getStoredChildControls() {

    if (childControl == null) {
      return new ImmutableList<>();
    }

    return ImmutableList.withElement(childControl);
  }

  //method
  @Override
  public final void rebuild() {

    final var dataSupplier = createDataSupplier();

    rebuild(dataSupplier);
  }

  //method declaration
  protected abstract IControl<?, ?> createControl(C controller, DS dataAdapter);

  //method
  private DS createDataSupplier() {
    return getStoredApplicationContext().createDataSupplier();
  }

  //method
  private void rebuild(final DS dataSupplier) {
    childControl = createControl(getStoredController(), dataSupplier);
    childControl.technicalSetParentControl(this);
    childControl.linkTo(this);
  }
}
