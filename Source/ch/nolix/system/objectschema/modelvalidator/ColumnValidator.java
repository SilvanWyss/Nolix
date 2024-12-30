package ch.nolix.system.objectschema.modelvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectschema.modelexaminer.ColumnExaminer;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelexaminerapi.IColumnExaminer;
import ch.nolix.systemapi.objectschemaapi.modelvalidatorapi.IColumnValidator;

/**
 * @author Silvan Wyss
 * @version 2024-12-30
 */
public final class ColumnValidator implements IColumnValidator {

  private static final IColumnExaminer COLUMN_EXAMINER = new ColumnExaminer();

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertIsAbstractReferenceColumn(final IColumn column) {
    if (!COLUMN_EXAMINER.isAbstractReferenceColumn(column)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(column, "is not an abstract refence column");
    }
  }
}
