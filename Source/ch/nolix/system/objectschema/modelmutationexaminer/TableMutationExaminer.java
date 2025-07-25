package ch.nolix.system.objectschema.modelmutationexaminer;

import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modelmutationexaminer.ITableMutationExaminer;

/**
 * @author Silvan Wyss
 * @version 2024-12-30
 */
public final class TableMutationExaminer implements ITableMutationExaminer {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canBeAddedToDatabase(final ITable table) {
    return //
    table != null
    && table.isOpen()
    && !table.belongsToDatabase();
  }
}
