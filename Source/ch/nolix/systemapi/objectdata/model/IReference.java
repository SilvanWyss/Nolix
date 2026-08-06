/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

/**
 * @author Silvan Wyss
 * @param <E> the type of the {@link IEntity} a {@link IReference} references.
 */
public interface IReference<E extends IEntity> extends BaseReference {
  String getReferencedEntityId();

  String getReferencedTableId();

  String getReferencedTableName();

  E getStoredReferencedEntity();

  ITable<E> getStoredReferencedTable();

  void setEntity(Object entity);

  void setEntityById(String id);
}
