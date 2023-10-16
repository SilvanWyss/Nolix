//package declaration
package ch.nolix.systemapi.webguiapi.atomiccontrolapi;

//own imports
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface ILabel extends IControl<ILabel, ILabelStyle> {

  //method declaration
  LabelRole getRole();

  //method declaration
  String getText();

  //method declaration
  boolean hasRole();

  //method declaration
  void removeRole();

  //method declaration
  ILabel setRole(LabelRole role);

  //method declaration
  ILabel setText(String text);
}
