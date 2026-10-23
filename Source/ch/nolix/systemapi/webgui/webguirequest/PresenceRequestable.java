/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.webguirequest;

import ch.nolix.systemapi.webgui.webguiproperty.Presence;

/**
 * @author Silvan Wyss
 */
public interface PresenceRequestable {
  /**
   * @return the {@link Presence} of the current {@link PresenceRequestable}
   */
  Presence getPresence();

  /**
   * @return true if the current {@link PresenceRequestable} is collapsed, false
   *         otherwise
   */
  boolean isCollapsed();

  /**
   * @return true if the current {@link PresenceRequestable} is invisible, false
   *         otherwise
   */
  boolean isInvisible();

  /**
   * @return true if the current {@link PresenceRequestable} is visible, false
   *         otherwise
   */
  boolean isVisible();
}
