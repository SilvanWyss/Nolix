//package declaration
package ch.nolix.system.application.component;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public abstract class Component<C extends Controller<AC>, AC> extends BaseComponent<C, AC> {

  //optional attribute
  private IControl<?, ?> childControl;

  //constructor
  protected Component(final C controller, final WebClientSession<AC> webClientSession) {

    super(controller, webClientSession);

    rebuild();
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
    childControl = createControl(getStoredController());
    childControl.technicalSetParentControl(this);
    childControl.linkTo(this);
  }

  //method declaration
  protected abstract IControl<?, ?> createControl(C controller);
}
