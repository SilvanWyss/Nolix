package ch.nolix.systemapi.objectdataapi.modelapi;

/**
 * @author Silvan Wyss
 * @version 2021-11-13
 * @param <E> is the type of the {@link IEntity}s a {@link IAbstractReference}
 *            can reference.
 */
public interface IAbstractReference<E extends IEntity> extends IField {

  /**
   * @return the name of the referenced {@link ITable} of the current
   *         {@link IAbstractReference}.
   */
  String getReferencedTableName();

  /**
   * @return the referenced {@link ITable} of the current
   *         {@link IAbstractReference}.
   */
  ITable<E> getStoredReferencedTable();
}
