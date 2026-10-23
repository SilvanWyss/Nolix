/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.label;

import ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute.FluentMutableTextHolder;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public interface ILabel extends Control<ILabel, ILabelStyle>, FluentMutableTextHolder<ILabel> {
  /**
   * @return the {@link LabelRole} of the current {@link ILabel}
   * @throws RuntimeException if the current {@link ILabel} does not have a role
   */
  LabelRole getRole();

  /**
   * @return true if the current {@link ILabel} has a role, false otherwise
   */
  boolean hasRole();

  /**
   * Removes the role of the current {@link ILabel}.
   */
  void removeRole();

  /**
   * Sets the given role to the current {@link ILabel}.
   * 
   * @param role
   * @return the current {@link ILabel}
   */
  ILabel setRole(LabelRole role);
}
