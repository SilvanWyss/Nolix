/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.objectcomposition.applicationcomponent;

/**
 * A {@link ClientComponent} can belong to a client.
 * 
 * @author Silvan Wyss
 * @param <C> the type of the client a {@link ClientComponent} can belong to
 */
public interface ClientComponent<C> {
  /**
   * @return true if the current {@link ClientComponent} belongs to a client,
   *         false otherwise
   */
  boolean belongsToClient();

  /**
   * @return the client of the current {@link ClientComponent}
   * @throws RuntimeException if the current {@link ClientComponent} does not
   *                          belong to a client
   */
  C getStoredParentClient();
}
