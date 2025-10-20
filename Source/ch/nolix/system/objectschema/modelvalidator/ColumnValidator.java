package ch.nolix.system.objectschema.modelvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.system.objectschema.modelexaminer.ColumnExaminer;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modelexaminer.IColumnExaminer;
import ch.nolix.systemapi.objectschema.modelvalidator.IColumnValidator;

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
  public void assertCanSetContentModel(
    final IColumn column,
    final FieldType fieldType,
    final DataType dataType,
    final IContainer<? extends ITable> referenceableTables,
    final IContainer<? extends IColumn> backReferenceableColumns) {
    final var canSetContentModel = //
    COLUMN_EXAMINER.canSetContentModel(
      column,
      fieldType,
      dataType,
      referenceableTables,
      backReferenceableColumns);

    if (!canSetContentModel) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(column, "cannot set the given content model");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertIsBaseReferenceColumn(final IColumn column) {
    if (!COLUMN_EXAMINER.isBaseReferenceColumn(column)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(column, "is not an abstract refence column");
    }
  }
}
