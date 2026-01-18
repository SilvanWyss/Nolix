/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.manager.guimanager;

import ch.nolix.coreapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 * @param <C> is the type of the controls of a {@link IControlManager}.
 */
public interface IControlManager<C> {
  /**
   * @return the controls of the current {@link IControlManager}.
   */
  IContainer<? extends C> getStoredControls();
}
