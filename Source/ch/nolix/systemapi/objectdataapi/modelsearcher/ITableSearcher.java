package ch.nolix.systemapi.objectdataapi.modelsearcher;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.modelexaminerapi.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectdataapi.modelapi.IColumn;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;

/**
 * @author Silvan Wyss
 * @version 2024-12-29
 */
public interface ITableSearcher extends IDatabaseObjectExaminer {

  /**
   * @param table
   * @return the ids of the locally deleted {@link IEntity}s of the given table.
   */
  IContainer<String> getLocallyDeletedEntityIds(final ITable<?> table);

  /**
   * @param <E>
   * @param table
   * @return the {@link IColumn}s that references the given table.
   */
  <E extends IEntity> IContainer<IColumn> getStoredColumsThatReferencesTable(ITable<E> table);
}
