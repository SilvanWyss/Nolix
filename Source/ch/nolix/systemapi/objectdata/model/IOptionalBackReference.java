package ch.nolix.systemapi.objectdata.model;

/**
 * @author Silvan Wyss
 */
public interface IOptionalBackReference<E extends IEntity> extends IBaseBackReference {
  String getBackReferencedEntityId();

  String getBackReferencedTableId();

  String getBackReferencedTableName();

  E getStoredBackReferencedEntity();

  ITable<E> getStoredBackReferencedTable();
}
