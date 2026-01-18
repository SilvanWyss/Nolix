/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.component.applicationcomponent;

/**
 * A {@link IClientComponent} can belong to a client.
 * 
 * @author Silvan Wyss
 * @param <C> is the type of the client a {@link IClientComponent} can belong
 *            to.
 */
public interface IClientComponent<C> {
  /**
   * @return true if the current {@link IClientComponent} belongs to a client,
   *         false otherwise.
   */
  boolean belongsToClient();

  /**
   * @return the client of the current {@link IClientComponent}.
   * @throws RuntimeException if the current {@link IClientComponent} does not
   *                          belong to a client.
   */
  C getStoredParentClient();
}
