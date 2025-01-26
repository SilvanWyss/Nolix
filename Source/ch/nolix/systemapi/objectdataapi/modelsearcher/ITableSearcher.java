package ch.nolix.systemapi.objectdataapi.modelsearcher;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.modelexaminerapi.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IColumnView;

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
   * @return the {@link IColumnView}s that references the given table.
   */
  <E extends IEntity> IContainer<IColumnView> getStoredColumsThatReferencesTable(ITable<E> table);
}
