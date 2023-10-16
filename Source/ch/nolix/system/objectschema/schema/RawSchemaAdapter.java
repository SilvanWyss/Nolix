//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedPropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDto;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
final class RawSchemaAdapter {

  //attribute
  private final ISchemaAdapter internalRawSchemaAdapter;

  //constructor
  public RawSchemaAdapter(final ISchemaAdapter internalRawSchemaAdapter) {

    GlobalValidator.assertThat(internalRawSchemaAdapter).thatIsNamed("internal raw schema adapter").isNotNull();

    this.internalRawSchemaAdapter = internalRawSchemaAdapter;
  }

  //method
  public void addColumnToTable(final ITable table, final IColumn column) {
    internalRawSchemaAdapter.addColumn(table.getName(), column.toDto());
  }

  //method
  public void addTable(final ITable table) {
    internalRawSchemaAdapter.addTable(table.toDto());
  }

  //method
  public boolean columnIsEmpty(final IColumn column) {
    return internalRawSchemaAdapter.columnIsEmpty(column.getParentTable().getName(), column.getName());
  }

  //method
  public void deleteColumn(final IColumn column) {
    internalRawSchemaAdapter.deleteColumn(column.getParentTable().getName(), column.getName());
  }

  //method
  public void deleteTable(final ITable table) {
    internalRawSchemaAdapter.deleteTable(table.getName());
  }

  //method
  public int getTableCount() {
    return internalRawSchemaAdapter.getTableCount();
  }

  //method
  public IContainer<IColumnDto> loadColumnsOfTable(final ITable table) {
    return internalRawSchemaAdapter.loadColumnsByTableId(table.getId());
  }

  //method
  public IContainer<IFlatTableDto> loadFlatTables() {
    return internalRawSchemaAdapter.loadFlatTables();
  }

  //method
  public ITime loadSchemaTimestamp() {
    return internalRawSchemaAdapter.loadSchemaTimestamp();
  }

  //method
  public void saveChangesAndReset() {
    internalRawSchemaAdapter.saveChanges();
  }

  //method
  public void setColumnName(
      final IColumn column,
      final String columnName,
      final String newColumnName) {
    internalRawSchemaAdapter.setColumnName(column.getParentTable().getName(), columnName, newColumnName);
  }

  public void setColumnParameterizedPropertyType(
      final IColumn column,
      final IParameterizedPropertyType parameterizedPropertyType) {
    internalRawSchemaAdapter.setColumnParameterizedPropertyType(
        column.getId(),
        parameterizedPropertyType.toDto());
  }

  //method
  public void setTableName(final String tableName, final String newTableName) {
    internalRawSchemaAdapter.setTableName(tableName, newTableName);
  }
}
