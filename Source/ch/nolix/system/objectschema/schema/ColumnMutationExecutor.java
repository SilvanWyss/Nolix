package ch.nolix.system.objectschema.schema;

import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedFieldType;

final class ColumnMutationExecutor {

  public void deleteColumn(final Column column) {

    if (column.belongsToTable()) {
      column.getParentTable().removeColumnAttribute(column);
    }

    column.internalGetRefRawSchemaAdapter().deleteColumn(column);

    column.internalSetDeleted();
  }

  public void setHeaderToColumn(final Column column, final String name) {

    final var oldName = column.getName();
    final var backReferencingColumns = column.getStoredBackReferencingColumns();

    column.setNameAttribute(name);

    if (column.isLinkedWithRealDatabase()) {
      column.internalGetRefRawSchemaAdapter().setColumnName(column, oldName, name);
    }

    for (final var brc : backReferencingColumns) {
      ((Column) brc).setParameterizedFieldTypeToDatabase();
    }

    column.internalSetEdited();
  }

  public void setParameterizedFieldTypeToColumn(
    final Column column,
    final IParameterizedFieldType parameterizedFieldType) {

    column.setParameterizedFieldTypeAttribute(parameterizedFieldType);

    if (column.isLinkedWithRealDatabase()) {
      column
        .internalGetRefRawSchemaAdapter()
        .setColumnParameterizedFieldType(column, parameterizedFieldType);
    }

    column.internalSetEdited();
  }
}
