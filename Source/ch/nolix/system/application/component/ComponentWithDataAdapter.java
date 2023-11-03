//package declaration
package ch.nolix.system.application.component;

//own imports
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.system.webgui.container.SingleContainer;
import ch.nolix.systemapi.applicationapi.applicationcontextapi.IDataAdapterFactory;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public abstract class ComponentWithDataAdapter<C extends Controller<AC>, AC extends IDataAdapterFactory<DA>, DA>
extends BaseComponent<C, AC> {

  //attribute
  private final SingleContainer rootControl = new SingleContainer();

  //constructor
  protected ComponentWithDataAdapter(
    final C controller,
    final DA initialDataAdapter,
    final WebClientSession<AC> webClientSession) {

    super(controller, webClientSession);

    rootControl.linkTo(this);

    fillUpRootControl(initialDataAdapter);

    doRegistrations(controller);
  }

  //method
  @Override
  public final IControl<?, ?> getStoredControl() {
    return rootControl;
  }

  //method
  @Override
  public final void refresh() {

    fillUpRootControl();

    switch (getRefreshBehavior()) {
      case REFRESH_GUI:
        fillUpRootControl();
        getStoredWebClientSession().refresh();
        break;
      case REFRESH_SELF:
        fillUpRootControl();
        getStoredWebClientSession().updateControlOnCounterpart(rootControl);
        break;
      case DO_NOT_REFRESH_ANYTHING:
        break;
    }
  }

  //method declaration
  protected abstract IControl<?, ?> createControl(C controller, DA dataAdapter);

  //method declaration
  protected abstract void doRegistrations(C controller);

  //method
  private DA createDataAdapter() {
    return getStoredApplicationContext().createDataAdapter();
  }

  //method
  private void fillUpRootControl() {

    final var dataAdapter = createDataAdapter();

    fillUpRootControl(dataAdapter);
  }

  //method
  private void fillUpRootControl(final DA dataAdapter) {

    final var control = createControl(getStoredController(), dataAdapter);

    rootControl.setControl(control);
  }
}
