/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

/**
 * @author Silvan Wyss
 * @param <E> the type of the {@link Entity} a {@link IReference} references.
 */
public interface IReference<E extends Entity> extends BaseReference {
  /**
   * @return the id of the {@link Entity} the current {@link IReference}
   *         references
   * @throws RuntimeException if the current {@link IReference} is empty
   */
  String getReferencedEntityId();

  /**
   * @return the id of the {@link ITable} of the {@link Entity} the current
   *         {@link IReference} references
   * @throws RuntimeException if the current {@link IReference} is empty
   */
  String getReferencedTableId();

  /**
   * @return the name of the {@link ITable} of the {@link Entity} the current
   *         {@link IReference} references
   * @throws RuntimeException if the current {@link IReference} is empty
   */
  String getReferencedTableName();

  /**
   * @return the {@link Entity} the current {@link IReference} references
   * @throws RuntimeException if the current {@link IReference} is empty
   */
  E getStoredReferencedEntity();

  /**
   * @return the {@link ITable} of the {@link Entity} the current
   *         {@link IReference} references
   * @throws RuntimeException if the current {@link IReference} is empty
   */
  ITable<E> getStoredReferencedTable();

  /**
   * Lets the current {@link IReference} reference the given entity.
   *
   * @param entity
   * @throws RuntimeException if the given entity is null
   * @throws RuntimeException if the given entity is not a {@link Entity} the
   *                          current {@link IReference} can reference
   */
  void setEntity(Object entity);

  /**
   * Lets the current {@link IReference} reference the {@link Entity} with the
   * given id.
   *
   * @param id
   * @throws RuntimeException if the referenced {@link ITable} of the current
   *                          {@link IReference} does not contain a {@link Entity}
   *                          with the given id
   */
  void setEntityById(String id);
}
