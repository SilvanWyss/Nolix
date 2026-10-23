/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.guicontrol;

import ch.nolix.systemapi.gui.guirequest.SelectionRequestable;

/**
 * @author Silvan Wyss
 */
public interface Selectable extends SelectionRequestable {
  /**
   * Selects the current {@link Selectable}.
   */
  void select();

  /**
   * Unselects the current {@link Selectable}.
   */
  void unselect();
}
