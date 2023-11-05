//package declaration
package ch.nolix.system.application.component;

//own imports
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.system.webgui.container.SingleContainer;
import ch.nolix.systemapi.applicationapi.applicationcontextapi.IDataSupplierFactory;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public abstract class ComponentWithDataSupplier<C extends Controller<AC>, AC extends IDataSupplierFactory<DS>, DS>
extends BaseComponent<C, AC> {

  //attribute
  private final SingleContainer rootControl = new SingleContainer();

  //constructor
  protected ComponentWithDataSupplier(
    final C controller,
    final DS initialDataSupplier,
    final WebClientSession<AC> webClientSession) {

    super(controller, webClientSession);

    rootControl.linkTo(this);

    rebuild(initialDataSupplier);

    doRegistrations(controller, initialDataSupplier);
  }

  //method
  @Override
  public final IControl<?, ?> getStoredControl() {
    return rootControl;
  }

  //method
  @Override
  public final void rebuild() {

    final var dataSupplier = createDataSupplier();

    rebuild(dataSupplier);
  }

  //method declaration
  protected abstract IControl<?, ?> createControl(C controller, DS dataAdapter);

  //method declaration
  protected abstract void doRegistrations(C controller, DS dataSupplier);

  //method
  private DS createDataSupplier() {
    return getStoredApplicationContext().createDataSupplier();
  }

  //method
  private void rebuild(final DS dataSupplier) {

    final var control = createControl(getStoredController(), dataSupplier);

    rootControl.setControl(control);
  }
}
