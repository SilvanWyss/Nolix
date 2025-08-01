package ch.nolix.systemapi.webgui.atomiccontrol.buttonapi;

import java.util.function.Consumer;

import ch.nolix.coreapi.attribute.fluentmutablemandatoryattribute.IFluentMutableTextHolder;
import ch.nolix.systemapi.webgui.main.IControl;

public interface IButton extends IControl<IButton, IButtonStyle>, IFluentMutableTextHolder<IButton> {

  ButtonRole getRole();

  boolean hasRole();

  void pressLeftMouseButton();

  void releaseLeftMouseButton();

  void removeLeftMouseButtonPressAction();

  void removeLeftMouseButtonReleaseAction();

  void removeRole();

  IButton setLeftMouseButtonPressAction(Runnable leftMouseButtonPressAction);

  IButton setLeftMouseButtonPressAction(Consumer<IButton> leftMouseButtonPressAction);

  IButton setLeftMouseButtonRelaseAction(Runnable leftMouseButtonReleaseAction);

  IButton setLeftMouseButtonRelaseAction(Consumer<IButton> leftMouseButtonReleaseAction);

  IButton setRole(ButtonRole role);
}
