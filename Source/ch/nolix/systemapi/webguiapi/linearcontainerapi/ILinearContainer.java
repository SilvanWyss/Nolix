package ch.nolix.systemapi.webguiapi.linearcontainerapi;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public interface ILinearContainer<C extends ILinearContainer<C, S>, S extends ILinearContainerStyle<S>>
extends ch.nolix.systemapi.webguiapi.basecontainerapi.IContainer<C, S> {

  C addControl(IControl<?, ?> control, IControl<?, ?>... controls);

  C addControls(IContainer<? extends IControl<?, ?>> controls);

  void removeControl(IControl<?, ?> control);
}
