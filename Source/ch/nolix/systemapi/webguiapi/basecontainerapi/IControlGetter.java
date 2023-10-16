//package declaration
package ch.nolix.systemapi.webguiapi.basecontainerapi;

//own imports
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IControlGetter {

  //method decleration
  IControl<?, ?> getStoredControl();
}
