/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.modelvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentContainsElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ReferencedArgumentException;
import ch.nolix.system.objectschema.modelexaminer.TableExaminer;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modelexaminer.ITableExaminer;
import ch.nolix.systemapi.objectschema.modelvalidator.ITableValidator;

/**
 * @author Silvan Wyss
 */
public final class TableValidator implements ITableValidator {
  private static final ITableExaminer TABLE_EXAMINER = new TableExaminer();

  @Override
  public void assertContainsColumn(final ITable table, final IColumn column) {
    if (!TABLE_EXAMINER.containsColumn(table, column)) {
      throw ArgumentDoesNotContainElementException.forArgumentAndElement(table, column);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertDoesNotBelongToDatabase(final ITable table) {
    if (table.belongsToDatabase()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(table, table.getStoredParentDatabase());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertDoesNotContainColumn(final ITable table, final IColumn column) {
    if (TABLE_EXAMINER.containsColumn(table, column)) {
      throw ArgumentContainsElementException.forArgumentAndElement(table, column);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertDoesNotContainColumnWithName(final ITable table, final String name) {
    if (TABLE_EXAMINER.containsColumnWithName(table, name)) {
      throw //
      InvalidArgumentException.forArgumentAndErrorPredicate(
        table,
        "contains already a column with the given name '" + name + "'");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertIsNotReferenced(final ITable table) {
    if (TABLE_EXAMINER.isReferenced(table)) {
      throw ReferencedArgumentException.forArgument(table);
    }
  }
}
