/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.guirequest;

/**
 * @author Silvan Wyss
 */
public interface SelectionRequestable {
  /**
   * @return true if the current {@link SelectionRequestable} is selected, false
   *         otherwise
   */
  boolean isSelected();
}
