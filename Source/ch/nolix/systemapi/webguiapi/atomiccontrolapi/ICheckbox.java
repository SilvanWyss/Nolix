//package declaration
package ch.nolix.systemapi.webguiapi.atomiccontrolapi;

//own imports
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface ICheckbox extends IControl<ICheckbox, ICheckboxStyle> {

  //method declaration
  ICheckbox check();

  //method declaration
  boolean isChecked();

  //method declaration
  ICheckbox unCheck();
}
