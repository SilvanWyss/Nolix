//package declaration
package ch.nolix.system.application.component;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.system.webgui.container.SingleContainer;
import ch.nolix.systemapi.applicationapi.applicationcontextapi.IDataAdapterFactory;
import ch.nolix.systemapi.applicationapi.componentapi.IComponent;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public abstract class ComponentWithDataAdapter<C extends Controller<AC>, AC extends IDataAdapterFactory<DA>, DA>
    implements IComponent {

  // attribute
  private final SingleContainer rootControl = new SingleContainer();

  // attribute
  private final C controller;

  // constructor
  protected ComponentWithDataAdapter(
      final C controller,
      final DA initialDataAdapter,
      final WebClientSession<AC> session) {

    GlobalValidator.assertThat(controller).thatIsNamed(Controller.class).isNotNull();

    this.controller = controller;
    this.controller.internalSetSession(session);

    rootControl.linkTo(this);

    fillUpRootControl(initialDataAdapter);

    doRegistrations(controller);
  }

  // method
  @Override
  public final IControl<?, ?> getStoredControl() {
    return rootControl;
  }

  // method
  @Override
  public final boolean isAlive() {
    return getStoredSession().isAlive();
  }

  // method
  @Override
  public final void refresh() {

    fillUpRootControl();

    // TODO: Lets a Component update the web client with the required CSS.
    // getStoredSession().updateControlOnCounterpart(rootControl);
  }

  // method declaration
  protected abstract IControl<?, ?> createControl(C controller, DA dataAdapter);

  // method declaration
  protected abstract void doRegistrations(C controller);

  // method
  private DA createDataAdapter() {
    return getStoredApplicationContext().createDataAdapter();
  }

  // method
  private void fillUpRootControl() {

    final var dataAdapter = createDataAdapter();

    fillUpRootControl(dataAdapter);
  }

  // method
  private void fillUpRootControl(final DA dataAdapter) {

    final var control = createControl(controller, dataAdapter);

    rootControl.setControl(control);
  }

  // method
  private AC getStoredApplicationContext() {
    return controller.getStoredApplicationContext();
  }

  // method
  private WebClientSession<AC> getStoredSession() {
    return controller.getStoredSession();
  }
}
