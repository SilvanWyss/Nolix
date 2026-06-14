/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.manager.guimanager;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;

/**
 * @author Silvan Wyss
 * @param <C> is the type of the controls of a {@link IControlManager}.
 */
public interface IControlManager<C> {
  /**
   * @return the controls of the current {@link IControlManager}.
   */
  IWellOrderContainer<? extends C> getStoredControls();
}
