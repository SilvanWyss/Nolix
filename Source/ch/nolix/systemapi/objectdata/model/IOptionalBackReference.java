/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

/**
 * @author Silvan Wyss
 * @param <E> the type of the {@link IEntity} a {@link IOptionalBackReference}
 *            can reference back.
 */
public interface IOptionalBackReference<E extends IEntity> extends BaseBackReference {
  String getBackReferencedEntityId();

  String getBackReferencedTableId();

  String getBackReferencedTableName();

  E getStoredBackReferencedEntity();

  ITable<E> getStoredBackReferencedTable();
}
