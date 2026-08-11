/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.objectcomposition.datamodelcomponent;

/**
 * A {@link EntityComponent} can belong to an entity.
 * 
 * @author Silvan Wyss
 * @param <E> the type of the entity a {@link EntityComponent} can belong to.
 */
public interface EntityComponent<E> {
  /**
   * @return true if the current {@link EntityComponent} belongs to an entity,
   *         false otherwise
   */
  boolean belongsToEntity();

  /**
   * @return the entity of the current {@link EntityComponent}
   * @throws RuntimeException if the current {@link EntityComponent} does not
   *                          belong to an entity
   */
  E getStoredParentEntity();
}
