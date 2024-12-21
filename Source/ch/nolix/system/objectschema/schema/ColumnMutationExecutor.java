package ch.nolix.system.objectschema.schema;

import ch.nolix.systemapi.objectschemaapi.schemaapi.IContentModel;

final class ColumnMutationExecutor {

  public void deleteColumn(final Column column) {

    if (column.belongsToTable()) {
      column.getStoredParentTable().removeColumnAttribute(column);
    }

    column.internalGetRefRawSchemaAdapter().deleteColumn(column);

    column.internalSetDeleted();
  }

  public void setHeaderToColumn(final Column column, final String name) {

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

  public void setParameterizedFieldTypeToColumn(
    final Column column,
    final IContentModel contentModel) {

    column.setParameterizedFieldTypeAttribute(contentModel);

    if (column.isConnectedWithRealDatabase()) {
      column
        .internalGetRefRawSchemaAdapter()
        .setColumnParameterizedFieldType(column, contentModel);
    }

    column.internalSetEdited();
  }
}
