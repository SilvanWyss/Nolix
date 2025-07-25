package ch.nolix.systemapi.webgui.basecontainer;

import ch.nolix.coreapi.state.statemutation.Clearable;
import ch.nolix.systemapi.webgui.controlstyle.IControlStyle;
import ch.nolix.systemapi.webgui.main.IControl;

public interface IContainer<C extends IContainer<C, S>, S extends IControlStyle<S>>
extends Clearable, IControl<C, S> {

  ContainerRole getRole();

  boolean hasRole();

  void removeRole();

  C setRole(ContainerRole role);
}
