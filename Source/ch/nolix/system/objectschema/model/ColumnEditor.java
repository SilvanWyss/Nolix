package ch.nolix.system.objectschema.model;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.system.objectschema.modelmutationvalidator.ColumnMutationValidator;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.midschema.structure.ColumnIdentification;
import ch.nolix.systemapi.midschema.structure.TableIdentification;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modeleditor.IColumnEditor;
import ch.nolix.systemapi.objectschema.modelmutationvalidator.IColumnMutationValidator;

/**
 * @author Silvan Wyss
 */
public final class ColumnEditor implements IColumnEditor<Column> {
  private static final IColumnMutationValidator COLUMN_MUTATION_VALIDATOR = new ColumnMutationValidator();

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteColumn(final Column column) {
    COLUMN_MUTATION_VALIDATOR.assertCanBeDeleted(column);

    final var table = column.getStoredParentTable();
    final var tableId = table.getId();
    final var tableName = table.getName();

    column.getStoredMidSchemaAdapter().deleteColumn(new TableIdentification(tableId, tableName), column.getName());
    column.getStoredParentTable().removeColumnAttribute(column);
    column.setDeleted();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setContentModelToColumn(
    final Column column,
    final FieldType fieldType,
    final DataType dataType,
    final IContainer<? extends ITable> referenceableTables,
    IContainer<? extends IColumn> backReferenceableColumns) {
    COLUMN_MUTATION_VALIDATOR.assertCanSetContentModel(
      column,
      fieldType,
      dataType,
      referenceableTables,
      backReferenceableColumns);

    column.setContentModelAttribute(fieldType, dataType, referenceableTables, backReferenceableColumns);
    column.setEdited();

    if (column.isConnectedWithRealDatabase()) {
      final var table = column.getStoredParentTable();
      final var tableId = table.getId();
      final var tableName = table.getName();
      final var tableIdentification = new TableIdentification(tableId, tableName);
      final var columnId = column.getId();
      final var columnName = column.getName();
      final var columnIdentification = new ColumnIdentification(columnId, columnName);
      final var referenceableTableIds = referenceableTables.to(ITable::getId);
      final var backReferenceableColumnIds = backReferenceableColumns.to(IColumn::getId);
      final var midSchemaAdapter = column.getStoredMidSchemaAdapter();

      midSchemaAdapter.setColumnModel(
        tableIdentification,
        columnIdentification,
        fieldType,
        dataType,
        referenceableTableIds,
        backReferenceableColumnIds);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setNameToColumn(final Column column, final String name) {
    COLUMN_MUTATION_VALIDATOR.assertCanSetName(column, name);

    final var oldName = column.getName();

    column.setNameAttribute(name);
    column.setEdited();

    if (column.isConnectedWithRealDatabase()) {
      final var tableName = column.getStoredParentTable().getName();
      final var midSchemaAdapter = column.getStoredMidSchemaAdapter();

      midSchemaAdapter.renameColumn(tableName, oldName, name);
    }
  }
}
