/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.modelmutationvalidator;

import ch.nolix.baseapi.datamodel.fieldproperty.DataType;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectschema.modelmutationexaminer.ColumnMutationExaminer;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modelmutationvalidator.IColumnMutationValidator;

/**
 * @author Silvan Wyss
 */
public final class ColumnMutationValidator implements IColumnMutationValidator {
  private static final ColumnMutationExaminer COLUMN_MUTATION_EXAMINER = new ColumnMutationExaminer();

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertCanBeDeleted(final IColumn column) {
    if (!COLUMN_MUTATION_EXAMINER.canBeDeleted(column)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(column, "cannot be deleted");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertCanSetName(final IColumn column, final String name) {
    if (!COLUMN_MUTATION_EXAMINER.canSetName(column, name)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(column, "cannot set the name '" + name + "'");
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
      throw InvalidArgumentException.forArgumentAndErrorPredicate(column, "cannot set the given content model'");
    }
  }
}
