package ch.nolix.coreapi.managerapi.applicationmanagerapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

/**
 * @author Silvan Wyss
 * @version 2025-07-11
 * @param <C> is the type of the clients of a {@link IClientManager}.
 */
public interface IClientManager<C> {

  /**
   * @return the clients of the current {@link IClientManager}.
   */
  IContainer<? extends C> getStoredClients();
}
