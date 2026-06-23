/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.button;

import java.util.function.Consumer;

import ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute.FluentMutableTextHolder;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public interface IButton extends Control<IButton, IButtonStyle>, FluentMutableTextHolder<IButton> {
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
