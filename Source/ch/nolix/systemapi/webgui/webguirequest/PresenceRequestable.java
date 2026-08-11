/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.webguirequest;

import ch.nolix.systemapi.webgui.webguiproperty.Presence;

/**
 * @author Silvan Wyss
 */
public interface PresenceRequestable {
  Presence getPresence();

  boolean isCollapsed();

  boolean isInvisible();

  boolean isVisible();
}
