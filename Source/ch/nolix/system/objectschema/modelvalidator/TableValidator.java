package ch.nolix.system.objectschema.modelvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentContainsElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ReferencedArgumentException;
import ch.nolix.system.objectschema.modelexaminer.TableExaminer;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;
import ch.nolix.systemapi.objectschemaapi.modelexaminerapi.ITableExaminer;
import ch.nolix.systemapi.objectschemaapi.modelvalidatorapi.ITableValidator;

/**
 * @author Silvan Wyss
 * @version 2024-12-30
 */
public final class TableValidator implements ITableValidator {

  private static final ITableExaminer TABLE_EXAMINER = new TableExaminer();

  @Override
  public void assertContainsColumn(final ITable table, final IColumn column) {
    if (!TABLE_EXAMINER.containsColumn(table, column)) {
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
    if (TABLE_EXAMINER.containsColumn(table, column)) {
      throw ArgumentContainsElementException.forArgumentAndElement(table, column);
    }
  }

  @Override
  public void assertDoesNotContainColumnWithName(final ITable table, final String name) {
    if (TABLE_EXAMINER.containsColumnWithName(table, name)) {
      throw //
      InvalidArgumentException.forArgumentAndErrorPredicate(
        table,
        "contains already a column with the given name '" + name + "'");
    }
  }

  @Override
  public void assertIsNotReferenced(final ITable table) {
    if (TABLE_EXAMINER.isReferenced(table)) {
      throw ReferencedArgumentException.forArgument(table);
    }
  }
}
