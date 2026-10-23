/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import ch.nolix.baseapi.generalstate.statemutation.Clearable;

/**
 * @author Silvan Wyss
 * @param <E> the type of the {@link Entity} a {@link IOptionalReference} can
 *            reference.
 */
public interface IOptionalReference<E extends Entity> extends Clearable, BaseReference {
  /**
   * @return the id of the {@link Entity} the current {@link IOptionalReference}
   *         references
   * @throws RuntimeException if the current {@link IOptionalReference} is empty
   */
  String getReferencedEntityId();

  /**
   * @return the id of the {@link ITable} of the {@link Entity} the current
   *         {@link IOptionalReference} references
   * @throws RuntimeException if the current {@link IOptionalReference} is empty
   */
  String getReferencedTableId();

  /**
   * @return the name of the {@link ITable} of the {@link Entity} the current
   *         {@link IOptionalReference} references
   * @throws RuntimeException if the current {@link IOptionalReference} is empty
   */
  String getReferencedTableName();

  /**
   * @return the {@link Entity} the current {@link IOptionalReference} references
   * @throws RuntimeException if the current {@link IOptionalReference} is empty
   */
  E getStoredReferencedEntity();

  /**
   * @return the {@link ITable} of the {@link Entity} the current
   *         {@link IOptionalReference} references
   * @throws RuntimeException if the current {@link IOptionalReference} is empty
   */
  ITable<E> getStoredReferencedTable();

  /**
   * Lets the current {@link IOptionalReference} reference the given entity.
   *
   * @param entity
   * @throws RuntimeException if the given entity is null
   * @throws RuntimeException if the given entity is not a {@link Entity} the
   *                          current {@link IOptionalReference} can reference
   */
  void setEntity(Object entity);

  /**
   * Lets the current {@link IOptionalReference} reference the {@link Entity} with
   * the given id.
   *
   * @param id
   * @throws RuntimeException if the referenced {@link ITable} of the current
   *                          {@link IOptionalReference} does not contain a
   *                          {@link Entity} with the given id
   */
  void setEntityById(String id);
}
