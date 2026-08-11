/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.objectcomposition.guimanager;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 * @param <C> the type of the controls of a {@link ControlManager}
 */
public interface ControlManager<C> {
  /**
   * @return the controls of the current {@link ControlManager}
   */
  ExtendedIterable<? extends C> getStoredControls();
}
