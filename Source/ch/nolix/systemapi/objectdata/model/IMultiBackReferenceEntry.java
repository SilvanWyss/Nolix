package ch.nolix.systemapi.objectdata.model;

import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;

public interface IMultiBackReferenceEntry<E extends IEntity> extends IDatabaseObject {
  String getBackReferencedEntityId();

  String getBackReferencedTableId();

  E getStoredBackReferencedEntity();

  ITable<E> getStoredBackReferencedTable();

  IMultiBackReference<E> getStoredParentMultiBackReference();
}
