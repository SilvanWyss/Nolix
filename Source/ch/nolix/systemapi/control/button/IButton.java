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
  /**
   * @return the {@link ButtonRole} of the current {@link IButton}
   * @throws RuntimeException if the current {@link IButton} does not have a role
   */
  ButtonRole getRole();

  /**
   * @return true if the current {@link IButton} has a role, false otherwise
   */
  boolean hasRole();

  /**
   * Lets the current {@link IButton} note a left mouse button press.
   */
  void pressLeftMouseButton();

  /**
   * Lets the current {@link IButton} note a left mouse button release.
   */
  void releaseLeftMouseButton();

  /**
   * Removes the left mouse button press action of the current {@link IButton}.
   */
  void removeLeftMouseButtonPressAction();

  /**
   * Removes the left mouse button release action of the current {@link IButton}.
   */
  void removeLeftMouseButtonReleaseAction();

  /**
   * Removes the role of the current {@link IButton}.
   */
  void removeRole();

  /**
   * Sets the given leftMouseButtonPressAction to the current {@link IButton}.
   * 
   * @param leftMouseButtonPressAction
   * @return the current {@link IButton}
   */
  IButton setLeftMouseButtonPressAction(Runnable leftMouseButtonPressAction);

  /**
   * Sets the given leftMouseButtonPressAction to the current {@link IButton}.
   * 
   * @param leftMouseButtonPressAction
   * @return the current {@link IButton}
   */
  IButton setLeftMouseButtonPressAction(Consumer<IButton> leftMouseButtonPressAction);

  /**
   * Sets the given leftMouseButtonReleaseAction to the current {@link IButton}.
   * 
   * @param leftMouseButtonReleaseAction
   * @return the current {@link IButton}
   */
  IButton setLeftMouseButtonRelaseAction(Runnable leftMouseButtonReleaseAction);

  /**
   * Sets the given leftMouseButtonReleaseAction to the current {@link IButton}.
   * 
   * @param leftMouseButtonReleaseAction
   * @return the current {@link IButton}
   */
  IButton setLeftMouseButtonRelaseAction(Consumer<IButton> leftMouseButtonReleaseAction);

  /**
   * Sets the given role to the current {@link IButton}.
   * 
   * @param role
   * @return the current {@link IButton}
   */
  IButton setRole(ButtonRole role);
}
