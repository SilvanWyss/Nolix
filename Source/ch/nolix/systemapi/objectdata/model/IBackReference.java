/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

/**
 * @author Silvan Wyss
 * @param <E> the type of the {@link Entity} a {@link IBackReference} references
 *            back.
 */
public interface IBackReference<E extends Entity> extends BaseBackReference {
  /**
   * @return the id of the {@link Entity} the current {@link IBackReference}
   *         references back
   * @throws RuntimeException if the current {@link IBackReference} is empty
   */
  String getBackReferencedEntityId();

  /**
   * @return the id of the {@link ITable} of the {@link Entity} the current
   *         {@link IBackReference} references back
   */
  String getBackReferencedTableId();

  /**
   * @return the name of the {@link ITable} of the {@link Entity} the current
   *         {@link IBackReference} references back
   */
  String getBackReferencedTableName();

  /**
   * @return the {@link Entity} the current {@link IBackReference} references back
   * @throws RuntimeException if the current {@link IBackReference} is empty
   */
  E getStoredBackReferencedEntity();

  /**
   * @return the {@link ITable} of the {@link Entity} the current
   *         {@link IBackReference} references back
   */
  ITable<E> getStoredBackReferencedTable();

  /**
   * @return true if the current {@link IBackReference} references back a
   *         {@link Entity}, false otherwise
   */
  boolean referencesBackEntity();
}
