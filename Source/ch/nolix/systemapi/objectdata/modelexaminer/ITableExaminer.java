package ch.nolix.systemapi.objectdata.modelexaminer;

import ch.nolix.systemapi.databaseobject.modelexaminer.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;

/**
 * @author Silvan Wyss
 * @version 2024-12-29
 */
public interface ITableExaminer extends IDatabaseObjectExaminer {

  /**
   * @param table
   * @return true if all new and edited mandatory fields of the {@link IEntity}s
   *         of the given table are set, false otherwise.
   */
  boolean allNewAndEditedMandatoryFieldsAreSet(ITable<?> table);

  /**
   * @param table
   * @return true if the given table can insert a {@link IEntity}, false
   *         otherwise.
   */
  boolean canInsertEntity(ITable<?> table);

  /**
   * @param table
   * @param entity
   * @return true if the given table can insert the given entity, false otherwise.
   */
  boolean canInsertGivenEntity(ITable<?> table, IEntity entity);

  /**
   * @param table
   * @param id
   * @return true if the given table contains a {@link IEntity} with the given id
   *         in the local data, false otherwise.
   */
  boolean containsEntityWithGivenIdInLocalData(ITable<?> table, String id);

  /**
   * @param table
   * @param entity
   * @return true if the the given table has inserted the given entity in the
   *         local data, false otherwise.
   */
  boolean hasInsertedGivenEntityInLocalData(ITable<?> table, IEntity entity);
}
