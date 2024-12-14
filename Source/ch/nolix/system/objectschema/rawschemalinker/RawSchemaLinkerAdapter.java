package ch.nolix.system.objectschema.rawschemalinker;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectschemaapi.rawschemalinkerapi.IRawSchemaLinkerAdapter;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDto;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class RawSchemaLinkerAdapter implements IRawSchemaLinkerAdapter {

  private final ISchemaAdapter internalRawSchemaAdapter;

  public RawSchemaLinkerAdapter(final ISchemaAdapter internalRawSchemaAdapter) {

    GlobalValidator.assertThat(internalRawSchemaAdapter).thatIsNamed("internal raw schema adapter").isNotNull();

    this.internalRawSchemaAdapter = internalRawSchemaAdapter;
  }

  @Override
  public void addColumnToTable(final ITable table, final IColumn column) {
    internalRawSchemaAdapter.addColumn(table.getName(), column.toDto());
  }

  @Override
  public void addTable(final ITable table) {
    internalRawSchemaAdapter.addTable(table.toDto());
  }

  @Override
  public boolean columnIsEmpty(final IColumn column) {
    return internalRawSchemaAdapter.columnIsEmpty(column.getStoredParentTable().getName(), column.getName());
  }

  @Override
  public void deleteColumn(final IColumn column) {
    internalRawSchemaAdapter.deleteColumn(column.getStoredParentTable().getName(), column.getName());
  }

  @Override
  public void deleteTable(final ITable table) {
    internalRawSchemaAdapter.deleteTable(table.getName());
  }

  @Override
  public int getTableCount() {
    return internalRawSchemaAdapter.getTableCount();
  }

  @Override
  public IContainer<IColumnDto> loadColumnsOfTable(final ITable table) {
    return internalRawSchemaAdapter.loadColumnsByTableId(table.getId());
  }

  @Override
  public IContainer<IFlatTableDto> loadFlatTables() {
    return internalRawSchemaAdapter.loadFlatTables();
  }

  @Override
  public ITime loadSchemaTimestamp() {
    return internalRawSchemaAdapter.loadSchemaTimestamp();
  }

  @Override
  public void saveChangesAndReset() {
    internalRawSchemaAdapter.saveChanges();
  }

  @Override
  public void setColumnName(
    final IColumn column,
    final String columnName,
    final String newColumnName) {
    internalRawSchemaAdapter.setColumnName(column.getStoredParentTable().getName(), columnName, newColumnName);
  }

  @Override
  public void setColumnParameterizedFieldType(
    final IColumn column,
    final IContentModel contentModel) {
    internalRawSchemaAdapter.setColumnParameterizedFieldType(
      column.getId(),
      contentModel.toDto());
  }

  @Override
  public void setTableName(final String tableName, final String newTableName) {
    internalRawSchemaAdapter.setTableName(tableName, newTableName);
  }
}
