/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.objectcomposition.applicationmanager;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 * @param <C> the type of the clients of a {@link ClientManager}
 */
public interface ClientManager<C> {
  /**
   * @return the clients of the current {@link ClientManager}
   */
  ExtendedIterable<? extends C> getStoredClients();
}
