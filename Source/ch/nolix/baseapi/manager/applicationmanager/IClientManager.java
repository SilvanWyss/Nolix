/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.manager.applicationmanager;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 * @param <C> is the type of the clients of a {@link IClientManager}.
 */
public interface IClientManager<C> {
  /**
   * @return the clients of the current {@link IClientManager}.
   */
  ExtendedIterable<? extends C> getStoredClients();
}
