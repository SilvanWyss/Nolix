package ch.nolix.systemapi.objectdata.modelsearcher;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.databaseobject.modelexaminer.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.schemaview.IColumnView;

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
  <E extends IEntity> IContainer<IColumnView<ITable<IEntity>>> getStoredColumsThatReferencesTable(ITable<E> table);
}
