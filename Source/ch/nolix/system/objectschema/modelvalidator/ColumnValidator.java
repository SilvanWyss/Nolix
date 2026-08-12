/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.modelvalidator;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectschema.modelexaminer.ColumnExaminer;
import ch.nolix.system.objectschema.modelmutationexaminer.ColumnMutationExaminer;
import ch.nolix.systemapi.database.databaseproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modelvalidator.IColumnValidator;

/**
 * @author Silvan Wyss
 */
public final class ColumnValidator implements IColumnValidator {
  private static final ColumnExaminer COLUMN_EXAMINER = new ColumnExaminer();

  private static final ColumnMutationExaminer COLUMN_MUTATION_EXAMINER = new ColumnMutationExaminer();

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertBelongsToTable(final IColumn column) {
    if (!column.belongsToTable()) {
      throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(column, ITable.class);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertCanSetContentModel(
    final IColumn column,
    final FieldType fieldType,
    final DataType dataType,
    final ExtendedIterable<? extends ITable> referenceableTables,
    final ExtendedIterable<? extends IColumn> backReferenceableColumns) {
    final var canSetContentModel = //
    COLUMN_MUTATION_EXAMINER.canSetContentModel(
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
