package ch.nolix.system.objectschema.schematool;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.schematool.IDatabaseTool;
import ch.nolix.systemapi.objectschema.schematool.ITableTool;

public final class DatabaseTool extends DatabaseObjectExaminer implements IDatabaseTool {

  private static final ITableTool TABLE_TOOL = new TableTool();

  @Override
  public void deleteTableWithGivenName(final IDatabase database, final String name) {
    getStoredTableWithGivenName(database, name).delete();
  }

  @Override
  public IContainer<? extends IColumn> getStoredAllBackReferenceColumns(final IDatabase database) {
    return database.getStoredTables().toMultiples(TABLE_TOOL::getStoredBackReferenceColumns);
  }

  @Override
  public ITable getStoredTableWithGivenName(final IDatabase database, final String name) {
    return database.getStoredTables().getStoredFirst(t -> t.hasName(name));
  }

  @Override
  public int getTableCount(final IDatabase database) {
    return database.getStoredTables().getCount();
  }
}
