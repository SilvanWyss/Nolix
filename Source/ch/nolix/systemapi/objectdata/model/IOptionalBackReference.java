/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

/**
 * @author Silvan Wyss
 * @param <E> the type of the {@link Entity} a {@link IOptionalBackReference}
 *            can reference back.
 */
public interface IOptionalBackReference<E extends Entity> extends BaseBackReference {
  /**
   * @return the id of the {@link Entity} the current
   *         {@link IOptionalBackReference} references back
   * @throws RuntimeException if the current {@link IOptionalBackReference} is
   *                          empty
   */
  String getBackReferencedEntityId();

  /**
   * @return the id of the {@link ITable} of the {@link Entity} the current
   *         {@link IOptionalBackReference} can reference back
   */
  String getBackReferencedTableId();

  /**
   * @return the name of the {@link ITable} of the {@link Entity} the current
   *         {@link IOptionalBackReference} can reference back
   */
  String getBackReferencedTableName();

  /**
   * @return the {@link Entity} the current {@link IOptionalBackReference}
   *         references back
   * @throws RuntimeException if the current {@link IOptionalBackReference} is
   *                          empty
   */
  E getStoredBackReferencedEntity();

  /**
   * @return the {@link ITable} of the {@link Entity} the current
   *         {@link IOptionalBackReference} can reference back
   */
  ITable<E> getStoredBackReferencedTable();
}
