package ch.nolix.systemapi.objectdataapi.datatoolapi;

import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;

/**
 * @author Silvan Wyss
 * @version 2024-12-20
 */
public interface IEntityCreator {

  /**
   * @param table
   * @param <E>   is the type of the created {@link IEntity}.
   * @return a new empty {@link IEntity} for the given table.
   * @throws RuntimeException if the given table is null.
   */
  <E extends IEntity> E createEmptyEntityForTable(ITable<E> table);

  /**
   * @param entityType
   * @param <E>        is the type of the created {@link IEntity}.
   * @return a new empty {@link IEntity} of the given entityType.
   * @throws RuntimeException if the given entityType is null.
   */
  <E extends IEntity> E createEmptyEntityForEntityType(Class<E> entityType);
}
