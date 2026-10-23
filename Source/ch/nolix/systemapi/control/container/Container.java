/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.container;

import ch.nolix.baseapi.generalstate.statemutation.Clearable;
import ch.nolix.systemapi.webgui.controlstyle.ControlStyle;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 * @param <C> the type of a {@link Container}.
 * @param <S> the type of the {@link ControlStyle} of a {@link Container}.
 */
public interface Container<C extends Container<C, S>, S extends ControlStyle<S>>
extends Clearable, Control<C, S> {
  /**
   * @return the {@link ContainerRole} of the current {@link Container}
   * @throws RuntimeException if the current {@link Container} does not have a
   *                          role
   */
  ContainerRole getRole();

  /**
   * @return true if the current {@link Container} has a role, false otherwise
   */
  boolean hasRole();

  /**
   * Removes the role of the current {@link Container}.
   */
  void removeRole();

  /**
   * Sets the given role to the current {@link Container}.
   * 
   * @param role
   * @return the current {@link Container}
   */
  C setRole(ContainerRole role);
}
