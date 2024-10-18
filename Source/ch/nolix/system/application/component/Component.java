package ch.nolix.system.application.component;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public abstract class Component<C extends Controller<AC>, AC> extends BaseComponent<C, AC> {

  private IControl<?, ?> childControl;

  protected Component(final C controller, final WebClientSession<AC> webClientSession) {

    super(controller, webClientSession);

    rebuild();
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
    childControl = createControl(getStoredController());
    childControl.internalSetParentControl(this);
    childControl.linkTo(this);
  }

  protected abstract IControl<?, ?> createControl(C controller);
}
