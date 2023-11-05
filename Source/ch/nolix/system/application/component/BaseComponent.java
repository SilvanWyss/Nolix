//package declaration
package ch.nolix.system.application.component;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.systemapi.applicationapi.componentapi.IComponent;

//class
public abstract class BaseComponent<C extends Controller<AC>, AC> implements IComponent {

  //attribute
  private final C controller;

  //constructor
  protected BaseComponent(final C controller, final WebClientSession<AC> webClientSession) {

    GlobalValidator.assertThat(controller).thatIsNamed(Controller.class).isNotNull();

    this.controller = controller;
    this.controller.internalSetSession(webClientSession);
  }

  //method
  @Override
  public final boolean isAlive() {
    return getStoredWebClientSession().isAlive();
  }

  //method
  @Override
  public final void refresh() {
    switch (getRefreshBehavior()) {
      case REFRESH_GUI:
        rebuild();
        getStoredWebClientSession().refresh();
        break;
      case REFRESH_SELF:
        rebuild();
        getStoredWebClientSession().updateControlOnCounterpart(getStoredControl());
        break;
      case DO_NOT_REFRESH_ANYTHING:
        break;
    }
  }

  //method
  protected final AC getStoredApplicationContext() {
    return getStoredController().getStoredApplicationContext();
  }

  //method
  protected final C getStoredController() {
    return controller;
  }

  //method
  protected final WebClientSession<AC> getStoredWebClientSession() {
    return getStoredController().getStoredWebClientSession();
  }
}
