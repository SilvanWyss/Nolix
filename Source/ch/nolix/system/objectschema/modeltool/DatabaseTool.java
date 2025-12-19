package ch.nolix.system.objectschema.modeltool;

import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.system.objectschema.modelsearcher.DatabaseSearcher;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.modelsearcher.IDatabaseSearcher;
import ch.nolix.systemapi.objectschema.modeltool.IDatabaseTool;

/**
 * @author Silvan Wyss
 */
public final class DatabaseTool extends DatabaseObjectExaminer implements IDatabaseTool {
  private static final IDatabaseSearcher DATABASE_SEARCHER = new DatabaseSearcher();

  @Override
  public void deleteTableWithGivenName(final IDatabase database, final String name) {
    DATABASE_SEARCHER.getStoredTableByName(database, name).delete();
  }

  @Override
  public int getTableCount(final IDatabase database) {
    return database.getStoredTables().getCount();
  }
}
