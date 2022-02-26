//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.element.time.base.Time;
import ch.nolix.system.objectschema.parametrizedpropertytype.SchemaImplementation;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParametrizedPropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;

//class
final class RawSchemaAdapter {
	
	//attribute
	private final ISchemaAdapter rawSchemaAdapter;
	
	//constructor
	public RawSchemaAdapter(final ISchemaAdapter rawSchemaAdapter) {
		
		Validator.assertThat(rawSchemaAdapter).thatIsNamed(ISchemaAdapter.class).isNotNull();
		
		this.rawSchemaAdapter = rawSchemaAdapter;
	}
	
	//method
	public void addColumnToTable(final ITable<SchemaImplementation> table, final IColumn<SchemaImplementation> column) {
		rawSchemaAdapter.addColumn(table.getName(), column.toDTO());
	}
	
	//method
	public void addTable(final ITable<SchemaImplementation> table) {
		rawSchemaAdapter.addTable(table.toDTO());
	}
	
	//method
	public boolean columnIsEmpty(final IColumn<SchemaImplementation> column) {
		return rawSchemaAdapter.columnIsEmpty(column.getParentTable().getName(), column.getName());
	}
	
	//method
	public void deleteColumn(final IColumn<SchemaImplementation> column) {
		rawSchemaAdapter.deleteColumn(column.getParentTable().getName(), column.getName());
	}
	
	//method
	public void deleteTable(final ITable<SchemaImplementation> table) {
		rawSchemaAdapter.deleteTable(table.getName());
	}
	
	//method
	public LinkedList<IColumnDTO> loadColumnsOfTable(final ITable<SchemaImplementation> table) {
		return rawSchemaAdapter.loadColumnsByTableId(table.getId());
	}
	
	//method
	public LinkedList<IFlatTableDTO> loadFlatTables() {
		return rawSchemaAdapter.loadFlatTables();
	}
	
	//method
	public Time loadSchemaTimestamp() {
		return rawSchemaAdapter.loadSchemaTimestamp();
	}
	
	//method
	public void saveChangesAndReset() {
		rawSchemaAdapter.saveChangesAndReset();
	}
	
	//method
	public void setColumnName(
		final IColumn<SchemaImplementation> column,
		final String columnName,
		final String newColumnName
	) {
		rawSchemaAdapter.setColumnName(column.getParentTable().getName(), columnName, newColumnName);
	}
	
	public void setColumnParametrizedPropertyType(
		final IColumn<SchemaImplementation> column,
		final IParametrizedPropertyType<SchemaImplementation, ?> parametrizedPropertyType
	) {
		rawSchemaAdapter.setColumnParametrizedPropertyType(
			column.getId(),
			parametrizedPropertyType.toDTO()
		);
	}
	
	//method
	public void setSchemaTimestamp(final Time schemaTimestamp) {
		rawSchemaAdapter.setSchemaTimestamp(schemaTimestamp);
	}
	
	//method
	public void setTableName(final String tableName, final String newTableName) {
		rawSchemaAdapter.setTableName(tableName, newTableName);
	}
}
