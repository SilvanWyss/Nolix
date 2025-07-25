package ch.nolix.coreapi.manager.guimanager;

import ch.nolix.coreapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 * @version 2025-07-14
 * @param <C> is the type of the controls of a {@link IControlManager}.
 */
public interface IControlManager<C> {

  /**
   * @return the controls of the current {@link IControlManager}.
   */
  IContainer<? extends C> getStoredControls();
}
