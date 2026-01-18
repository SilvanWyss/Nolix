/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.presence;

/**
 * @author Silvan Wyss
 */
public interface PresenceRequestable {
  Presence getPresence();

  boolean isCollapsed();

  boolean isInvisible();

  boolean isVisible();
}
