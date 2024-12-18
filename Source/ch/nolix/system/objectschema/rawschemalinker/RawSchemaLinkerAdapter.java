package ch.nolix.system.objectschema.rawschemalinker;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.rawschemadtomapper.ColumnDtoMapper;
import ch.nolix.system.objectschema.rawschemadtomapper.TableDtoMapper;
import ch.nolix.systemapi.objectschemaapi.rawschemadtomapperapi.IColumnDtoMapper;
import ch.nolix.systemapi.objectschemaapi.rawschemadtomapperapi.ITableDtoMapper;
import ch.nolix.systemapi.objectschemaapi.rawschemalinkerapi.IRawSchemaLinkerAdapter;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.flatschemadto.FlatTableDto;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.systemapi.rawschemaapi.schemadto.ColumnDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class RawSchemaLinkerAdapter implements IRawSchemaLinkerAdapter {

  private static final ITableDtoMapper TABLE_DTO_MAPPER = new TableDtoMapper();

  private static final IColumnDtoMapper COLUMN_DTO_MAPPER = new ColumnDtoMapper();

  private final ISchemaAdapter internalRawSchemaAdapter;

  public RawSchemaLinkerAdapter(final ISchemaAdapter internalRawSchemaAdapter) {

    GlobalValidator.assertThat(internalRawSchemaAdapter).thatIsNamed("internal raw schema adapter").isNotNull();

    this.internalRawSchemaAdapter = internalRawSchemaAdapter;
  }

  @Override
  public void addColumnToTable(final ITable table, final IColumn column) {

    final var tableName = table.getName();
    final var columnDto = COLUMN_DTO_MAPPER.mapColumnToColumnDto(column);

    internalRawSchemaAdapter.addColumn(tableName, columnDto);
  }

  @Override
  public void addTable(final ITable table) {

    final var tableDto = TABLE_DTO_MAPPER.mapTableToTableDto(table);

    internalRawSchemaAdapter.addTable(tableDto);
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
  public IContainer<ColumnDto> loadColumnsOfTable(final ITable table) {
    return internalRawSchemaAdapter.loadColumnsByTableId(table.getId());
  }

  @Override
  public IContainer<FlatTableDto> loadFlatTables() {
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
