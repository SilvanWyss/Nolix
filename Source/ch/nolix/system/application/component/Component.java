//package declaration
package ch.nolix.system.application.component;

//own imports
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.system.webgui.container.SingleContainer;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public abstract class Component<C extends Controller<AC>, AC> extends BaseComponent<C, AC> {

  //attribute
  private final SingleContainer rootControl = new SingleContainer();

  //constructor
  protected Component(final C controller, final WebClientSession<AC> webClientSession) {

    super(controller, webClientSession);

    rootControl.linkTo(this);

    fillUpRootControl();

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
  protected abstract IControl<?, ?> createControl(C controller);

  //method declaration
  protected abstract void doRegistrations(C controller);

  //method
  private void fillUpRootControl() {

    final var control = createControl(getStoredController());

    rootControl.setControl(control);
  }
}
