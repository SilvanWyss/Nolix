package ch.nolix.systemapi.objectdata.model;

/**
 * @author Silvan Wyss
 */
public interface IBackReference<E extends IEntity> extends IBaseBackReference {
  String getBackReferencedEntityId();

  String getBackReferencedTableId();

  String getBackReferencedTableName();

  E getStoredBackReferencedEntity();

  ITable<E> getStoredBackReferencedTable();

  boolean referencesBackEntity();
}
