//package declaration
package ch.nolix.systemapi.webguiapi.containerapi;

//own imports
import ch.nolix.systemapi.webguiapi.basecontainerapi.IContainer;
import ch.nolix.systemapi.webguiapi.basecontainerapi.IControlGetter;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface ISingleContainer extends IContainer<ISingleContainer, ISingleContainerStyle> {

  // method declaration
  IControl<?, ?> getStoredControl();

  // method declaration
  ISingleContainer setComponent(IControlGetter component);

  // method declaration
  ISingleContainer setControl(IControl<?, ?> control);
}
