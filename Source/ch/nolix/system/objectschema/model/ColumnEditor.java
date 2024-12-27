package ch.nolix.system.objectschema.model;

import ch.nolix.system.objectschema.modelmutationvalidator.ColumnMutationValidator;
import ch.nolix.systemapi.objectschemaapi.modelapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.modeleditorapi.IColumnEditor;

/**
 * @author Silvan Wyss
 * @version 2021-07-11
 */
public final class ColumnEditor implements IColumnEditor<Column> {

  private static final ColumnMutationValidator COLUMN_MUTATION_VALIDATOR = new ColumnMutationValidator();

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteColumn(final Column column) {

    COLUMN_MUTATION_VALIDATOR.assertCanBeDeleted(column);

    if (column.belongsToTable()) {
      column.getStoredParentTable().removeColumnAttribute(column);
    }

    column.internalGetRefRawSchemaAdapter().deleteColumn(column);

    column.internalSetDeleted();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setContentModelToColumn(final Column column, final IContentModel contentModel) {

    COLUMN_MUTATION_VALIDATOR.assertCanSetContentModel(column, contentModel);

    column.setParameterizedFieldTypeAttribute(contentModel);

    if (column.isConnectedWithRealDatabase()) {
      column
        .internalGetRefRawSchemaAdapter()
        .setColumnContentModel(column, contentModel);
    }

    column.internalSetEdited();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setNameToColumn(final Column column, final String name) {

    COLUMN_MUTATION_VALIDATOR.assertCanSetName(column, name);

    final var oldName = column.getName();
    final var backReferencingColumns = column.getStoredBackReferencingColumns();

    column.setNameAttribute(name);

    if (column.isConnectedWithRealDatabase()) {
      column.internalGetRefRawSchemaAdapter().setColumnName(column, oldName, name);
    }

    for (final var brc : backReferencingColumns) {
      ((Column) brc).setParameterizedFieldTypeToDatabase();
    }

    column.internalSetEdited();
  }
}
