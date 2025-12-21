package ch.nolix.systemapi.objectdata.model;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the {@link IEntity} a
 *            {@link IOptionalBackReference} can reference back.
 */
public interface IOptionalBackReference<E extends IEntity> extends IBaseBackReference {
  String getBackReferencedEntityId();

  String getBackReferencedTableId();

  String getBackReferencedTableName();

  E getStoredBackReferencedEntity();

  ITable<E> getStoredBackReferencedTable();
}
