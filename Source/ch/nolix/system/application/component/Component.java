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

    rebuild();
  }

  //method
  @Override
  public final IControl<?, ?> getStoredControl() {
    return rootControl;
  }

  //method
  @Override
  public final void rebuild() {

    final var control = createControl(getStoredController());

    rootControl.setControl(control);
  }

  //method declaration
  protected abstract IControl<?, ?> createControl(C controller);
}
