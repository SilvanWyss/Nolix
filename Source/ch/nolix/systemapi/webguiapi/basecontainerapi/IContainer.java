//package declaration
package ch.nolix.systemapi.webguiapi.basecontainerapi;

//own imports
import ch.nolix.coreapi.methodapi.mutationapi.Clearable;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IContainer<C extends IContainer<C, ECS>, ECS extends IControlStyle<ECS>>
extends Clearable, IControl<C, ECS> {

  //method declaration
  ContainerRole getRole();

  //method declaration
  boolean hasRole();

  //method declaration
  void removeRole();

  //method declaration
  C setRole(ContainerRole role);
}
