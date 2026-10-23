/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.guirequest;

/**
 * @author Silvan Wyss
 */
public interface VisibilityRequestable {
  /**
   * @return true if the current {@link VisibilityRequestable} is invisible, false
   *         otherwise
   */
  boolean isInvisible();

  /**
   * @return true if the current {@link VisibilityRequestable} is visible, false
   *         otherwise
   */
  boolean isVisible();
}
