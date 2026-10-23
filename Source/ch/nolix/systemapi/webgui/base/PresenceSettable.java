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
  /**
   * Sets the current {@link PresenceSettable} collapsed. A collapsed object is
   * not visible and does not take any space.
   * 
   * @return the current {@link PresenceSettable}
   */
  S setCollapsed();

  /**
   * Sets the current {@link PresenceSettable} invisible. An invisible object is
   * not visible, but takes its space.
   * 
   * @return the current {@link PresenceSettable}
   */
  S setInvisible();

  /**
   * Sets the current {@link PresenceSettable} visible if the given visible is
   * true, invisible otherwise.
   * 
   * @param visible
   * @return the current {@link PresenceSettable}
   */
  S setVisibility(boolean visible);

  /**
   * Sets the current {@link PresenceSettable} visible.
   * 
   * @return the current {@link PresenceSettable}
   */
  S setVisible();
}
