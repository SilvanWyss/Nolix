package ch.nolix.system.objectschema.modelsearcher;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.system.objectschema.modeltool.TableTool;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modelsearcher.IDatabaseSearcher;
import ch.nolix.systemapi.objectschema.modeltool.ITableTool;

/**
 * @author Silvan Wyss
 */
public final class DatabaseSearcher implements IDatabaseSearcher {

  private static final ITableTool TABLE_TOOL = new TableTool();

  @Override
  public IContainer<? extends IColumn> getStoredBaseBackReferenceColumns(final IDatabase database) {

    final var tables = database.getStoredTables();

    return tables.toMultiples(TABLE_TOOL::getStoredBaseBackReferenceColumns);
  }

  @Override
  public ITable getStoredTableByName(final IDatabase database, final String tableName) {

    final var tables = database.getStoredTables();

    return tables.getStoredFirst(t -> t.hasName(tableName));
  }
}
