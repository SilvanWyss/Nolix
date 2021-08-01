//package declaration
package ch.nolix.system.databaseschema.schema;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.time.base.Time;
import ch.nolix.techapi.databaseschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.techapi.databaseschemaapi.intermediateschemaapi.IIntermediateSchemaAdapter;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IParametrizedPropertyType;
import ch.nolix.techapi.databaseschemaapi.schemaapi.ITable;
import ch.nolix.techapi.databaseschemaapi.schemadtoapi.IColumnDTO;

//class
final class RealSchemaAdapter {
	
	//attribute
	private final IIntermediateSchemaAdapter internalRealSchemaAdapter;
	
	//constructor
	public RealSchemaAdapter(final IIntermediateSchemaAdapter internalIRealSchemaAdapter) {
		
		Validator.assertThat(internalIRealSchemaAdapter).thatIsNamed("internal real schema adapter").isNotNull();
		
		this.internalRealSchemaAdapter = internalIRealSchemaAdapter;
	}
	
	//method
	public void addColumnToTable(final ITable<?, ?, ?> table, final IColumn<?, ?> column) {
		internalRealSchemaAdapter.addColumn(table.getName(), column.toDTO());
	}
	
	//method
	public void addTable(final ITable<?, ?, ?> table) {
		internalRealSchemaAdapter.addTable(table.toDTO());
	}
	
	//method
	public boolean columnIsEmpty(final IColumn<?, ?> column) {
		return internalRealSchemaAdapter.columnIsEmpty(column.getParentTable().getName(), column.getHeader());
	}
	
	//method
	public void deleteColumn(final IColumn<?, ?> column) {
		internalRealSchemaAdapter.deleteColumn(column.getParentTable().getName(), column.getHeader());
	}
	
	//method
	public void deleteTable(final ITable<?, ?, ?> table) {
		internalRealSchemaAdapter.deleteTable(table.getName());
	}
	
	//method
	public boolean hasChanges() {
		return internalRealSchemaAdapter.hasChanges();
	}
	
	//method
	public LinkedList<IColumnDTO> loadColumnsOfTable(final ITable<?, ?, ?> table) {
		return internalRealSchemaAdapter.loadColumnsOfTable(table.getName());
	}
	
	//method
	public LinkedList<IFlatTableDTO> loadFlatTables() {
		return internalRealSchemaAdapter.loadFlatTables();
	}
	
	//method
	public Time loadSchemaTimestamp() {
		return internalRealSchemaAdapter.loadSchemaTimestamp();
	}
	
	//method
	public void saveChanges() {
		internalRealSchemaAdapter.saveChanges();
	}
	
	//method
	public void setColumnHeader(final IColumn<?, ?> column, final String columnHeader, final String newColumnHeader) {
		internalRealSchemaAdapter.setColumnHeader(column.getParentTable().getName(), columnHeader, newColumnHeader);
	}
	
	public void setColumnParametrizedPropertyType(
		final IColumn<?, ?> column,
		final IParametrizedPropertyType<?> parametrizedPropertyType
	) {
		internalRealSchemaAdapter.setColumnParametrizedPropertyType(
			column.getParentTable().getName(),
			column.getHeader(),
			parametrizedPropertyType.toDTO()
		);
	}
	
	//method
	public void setSchemaTimestamp(final Time schemaTimestamp) {
		internalRealSchemaAdapter.setSchemaTimestamp(schemaTimestamp);
	}
	
	//method
	public void setTableName(final String tableName, final String newTableName) {
		internalRealSchemaAdapter.setTableName(tableName, newTableName);
	}
}
