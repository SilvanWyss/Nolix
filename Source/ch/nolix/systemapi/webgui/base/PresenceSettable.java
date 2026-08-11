/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.base;

import ch.nolix.systemapi.webgui.webguirequest.PresenceRequestable;

/**
 * @author Silvan Wyss
 * @param <S> the type of a {@link PresenceSettable}.
 */
public interface PresenceSettable<S extends PresenceSettable<S>> extends PresenceRequestable {
  S setCollapsed();

  S setInvisible();

  S setVisibility(boolean visible);

  S setVisible();
}
