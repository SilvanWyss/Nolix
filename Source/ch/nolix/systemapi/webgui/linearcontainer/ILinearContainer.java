package ch.nolix.systemapi.webgui.linearcontainer;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.webgui.main.IControl;

public interface ILinearContainer<C extends ILinearContainer<C, S>, S extends ILinearContainerStyle<S>>
extends ch.nolix.systemapi.webgui.basecontainer.IContainer<C, S> {

  C addControl(IControl<?, ?> control, IControl<?, ?>... controls);

  C addControls(IContainer<? extends IControl<?, ?>> controls);

  void removeControl(IControl<?, ?> control);
}
