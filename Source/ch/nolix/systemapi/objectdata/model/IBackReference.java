/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

/**
 * @author Silvan Wyss
 * @param <E> the type of the {@link Entity} a {@link IBackReference}
 *            references back.
 */
public interface IBackReference<E extends Entity> extends BaseBackReference {
  String getBackReferencedEntityId();

  String getBackReferencedTableId();

  String getBackReferencedTableName();

  E getStoredBackReferencedEntity();

  ITable<E> getStoredBackReferencedTable();

  boolean referencesBackEntity();
}
