//package declaration
package ch.nolix.systemapi.webguiapi.atomiccontrolapi;

//Java imports
import java.util.function.Consumer;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableTextHolder;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IButton extends IControl<IButton, IButtonStyle>, IFluentMutableTextHolder<IButton> {

  //method declaration
  ButtonRole getRole();

  //method declaration
  boolean hasRole();

  //method declaration
  void pressLeftMouseButton();

  //method declaration
  void releaseLeftMouseButton();

  //method declaration
  void removeLeftMouseButtonPressAction();

  //method declaration
  void removeLeftMouseButtonReleaseAction();

  //method declaration
  void removeRole();

  //method declaration
  IButton setLeftMouseButtonPressAction(Runnable leftMouseButtonPressAction);

  //method declaration
  IButton setLeftMouseButtonPressAction(Consumer<IButton> leftMouseButtonPressAction);

  //method declaration
  IButton setLeftMouseButtonRelaseAction(Runnable leftMouseButtonReleaseAction);

  //method declaration
  IButton setLeftMouseButtonRelaseAction(Consumer<IButton> leftMouseButtonReleaseAction);

  //method declaration
  IButton setRole(ButtonRole role);
}
