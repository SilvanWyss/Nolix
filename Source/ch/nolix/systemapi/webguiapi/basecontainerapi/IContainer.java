package ch.nolix.systemapi.webguiapi.basecontainerapi;

import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public interface IContainer<C extends IContainer<C, ECS>, ECS extends IControlStyle<ECS>>
extends Clearable, IControl<C, ECS> {

  ContainerRole getRole();

  boolean hasRole();

  void removeRole();

  C setRole(ContainerRole role);
}
