//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.objectschema.parametrizedpropertytype.SchemaImplementation;
import ch.nolix.system.time.base.Time;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParametrizedPropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;

//class
final class RawSchemaAdapter {
	
	//attribute
	private final ISchemaAdapter internalRawSchemaAdapter;
	
	//constructor
	public RawSchemaAdapter(final ISchemaAdapter internalRawSchemaAdapter) {
		
		Validator.assertThat(internalRawSchemaAdapter).thatIsNamed("internal raw schema adapter").isNotNull();
		
		this.internalRawSchemaAdapter = internalRawSchemaAdapter;
	}
	
	//method
	public void addColumnToTable(final ITable<SchemaImplementation> table, final IColumn<SchemaImplementation> column) {
		internalRawSchemaAdapter.addColumn(table.getName(), column.toDTO());
	}
	
	//method
	public void addTable(final ITable<SchemaImplementation> table) {
		internalRawSchemaAdapter.addTable(table.toDTO());
	}
	
	//method
	public boolean columnIsEmpty(final IColumn<SchemaImplementation> column) {
		return internalRawSchemaAdapter.columnIsEmpty(column.getParentTable().getName(), column.getName());
	}
	
	//method
	public void deleteColumn(final IColumn<SchemaImplementation> column) {
		internalRawSchemaAdapter.deleteColumn(column.getParentTable().getName(), column.getName());
	}
	
	//method
	public void deleteTable(final ITable<SchemaImplementation> table) {
		internalRawSchemaAdapter.deleteTable(table.getName());
	}
	
	//method
	public int getTableCount() {
		return internalRawSchemaAdapter.getTableCount();
	}
	
	//method
	public LinkedList<IColumnDTO> loadColumnsOfTable(final ITable<SchemaImplementation> table) {
		return internalRawSchemaAdapter.loadColumnsByTableId(table.getId());
	}
	
	//method
	public LinkedList<IFlatTableDTO> loadFlatTables() {
		return internalRawSchemaAdapter.loadFlatTables();
	}
	
	//method
	public Time loadSchemaTimestamp() {
		return internalRawSchemaAdapter.loadSchemaTimestamp();
	}
	
	//method
	public void saveChangesAndReset() {
		internalRawSchemaAdapter.saveChangesAndReset();
	}
	
	//method
	public void setColumnName(
		final IColumn<SchemaImplementation> column,
		final String columnName,
		final String newColumnName
	) {
		internalRawSchemaAdapter.setColumnName(column.getParentTable().getName(), columnName, newColumnName);
	}
	
	public void setColumnParametrizedPropertyType(
		final IColumn<SchemaImplementation> column,
		final IParametrizedPropertyType<SchemaImplementation, ?> parametrizedPropertyType
	) {
		internalRawSchemaAdapter.setColumnParametrizedPropertyType(
			column.getId(),
			parametrizedPropertyType.toDTO()
		);
	}
	
	//method
	public void setSchemaTimestamp(final Time schemaTimestamp) {
		internalRawSchemaAdapter.setSchemaTimestamp(schemaTimestamp);
	}
	
	//method
	public void setTableName(final String tableName, final String newTableName) {
		internalRawSchemaAdapter.setTableName(tableName, newTableName);
	}
}
