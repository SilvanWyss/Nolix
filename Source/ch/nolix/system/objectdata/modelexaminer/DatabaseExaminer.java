package ch.nolix.system.objectdata.modelexaminer;

import ch.nolix.system.objectdata.datatool.TableTool;
import ch.nolix.systemapi.objectdataapi.datatoolapi.ITableTool;
import ch.nolix.systemapi.objectdataapi.modelapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.modelexaminerapi.IDatabaseExaminer;

/**
 * @author Silvan Wyss
 * @version 2024-12-29
 */
public final class DatabaseExaminer implements IDatabaseExaminer {

  private static final ITableTool TABLE_TOOL = new TableTool();

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean allNewAndEditedMandatoryFieldsAreSet(final IDatabase database) {
    return database.getStoredTables().containsOnly(TABLE_TOOL::allNewAndEditedMandatoryFieldsAreSet);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canSaveChanges(final IDatabase database) {
    return database.isOpen()
    && database.isConnectedWithRealDatabase()
    && allNewAndEditedMandatoryFieldsAreSet(database);
  }
}
