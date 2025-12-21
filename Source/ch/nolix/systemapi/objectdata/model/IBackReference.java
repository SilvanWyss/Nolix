package ch.nolix.systemapi.objectdata.model;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the {@link IEntity} a {@link IBackReference}
 *            references back.
 */
public interface IBackReference<E extends IEntity> extends IBaseBackReference {
  String getBackReferencedEntityId();

  String getBackReferencedTableId();

  String getBackReferencedTableName();

  E getStoredBackReferencedEntity();

  ITable<E> getStoredBackReferencedTable();

  boolean referencesBackEntity();
}
