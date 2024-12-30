package ch.nolix.system.objectschema.modelvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentContainsElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ReferencedArgumentException;
import ch.nolix.system.objectschema.schematool.TableTool;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;
import ch.nolix.systemapi.objectschemaapi.modelvalidatorapi.ITableValidator;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.ITableTool;

/**
 * @author Silvan Wyss
 * @version 2024-12-30
 */
public final class TableValidator implements ITableValidator {

  private static final ITableTool TABLE_TOOL = new TableTool();

  @Override
  public void assertContainsColumn(final ITable table, final IColumn column) {
    if (!TABLE_TOOL.containsGivenColumn(table, column)) {
      throw ArgumentDoesNotContainElementException.forArgumentAndElement(table, column);
    }
  }

  @Override
  public void assertDoesNotBelongToDatabase(final ITable table) {
    if (table.belongsToDatabase()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(table, table.getStoredParentDatabase());
    }
  }

  @Override
  public void assertDoesNotContainColumn(final ITable table, final IColumn column) {
    if (TABLE_TOOL.containsGivenColumn(table, column)) {
      throw ArgumentContainsElementException.forArgumentAndElement(table, column);
    }
  }

  @Override
  public void assertDoesNotContainColumnWithName(final ITable table, final String name) {
    if (TABLE_TOOL.containsColumnWithGivenName(table, name)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        table,
        "contains already a column with the given name '" + name + "'");
    }
  }

  @Override
  public void assertIsNotReferenced(final ITable table) {
    if (TABLE_TOOL.isReferenced(table)) {
      throw ReferencedArgumentException.forArgument(table);
    }
  }
}
