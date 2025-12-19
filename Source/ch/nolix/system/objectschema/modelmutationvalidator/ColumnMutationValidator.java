package ch.nolix.system.objectschema.modelmutationvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.system.objectschema.modelmutationexaminer.ColumnMutationExaminer;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modelmutationexaminer.IColumnMutationExaminer;
import ch.nolix.systemapi.objectschema.modelmutationvalidator.IColumnMutationValidator;

/**
 * @author Silvan Wyss
 */
public final class ColumnMutationValidator implements IColumnMutationValidator {
  private static final IColumnMutationExaminer COLUMN_MUTATION_EXAMINER = new ColumnMutationExaminer();

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
    final IContainer<? extends ITable> referenceableTables,
    final IContainer<? extends IColumn> backReferenceableColumns) {
    final var canSetContentModel = //
    COLUMN_MUTATION_EXAMINER.canSetContentModel(
      column,
      fieldType,
      dataType,
      referenceableTables,
      backReferenceableColumns);

    if (!canSetContentModel) {
      throw //
      InvalidArgumentException.forArgumentAndErrorPredicate(
        column,
        "cannot set the given content model'");
    }
  }
}
