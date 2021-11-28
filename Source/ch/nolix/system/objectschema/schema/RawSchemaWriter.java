//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.time.base.Time;
import ch.nolix.techapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.objectschemaapi.schemaapi.IParametrizedPropertyType;
import ch.nolix.techapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.techapi.rawobjectschemaapi.schemaadapterapi.ISchemaWriter;

//class
final class RawSchemaWriter {
	
	//attribute
	private final ISchemaWriter internalRawSchemaWriter;
	
	//constructor
	public RawSchemaWriter(final ISchemaWriter internalRawSchemaWriter) {
		
		Validator.assertThat(internalRawSchemaWriter).thatIsNamed("internal RawSchemaWriter").isNotNull();
		
		this.internalRawSchemaWriter = internalRawSchemaWriter;
	}
	
	//method
	public void addColumnToTable(final ITable table, final IColumn column) {
		internalRawSchemaWriter.addColumn(table.getName(), column.toDTO());
	}
	
	//method
	public void addTable(final ITable table) {
		internalRawSchemaWriter.addTable(table.toDTO());
	}
	
	//method
	public void deleteColumn(final IColumn column) {
		internalRawSchemaWriter.deleteColumn(column.getParentTable().getName(), column.getHeader());
	}
	
	//method
	public void deleteTable(final ITable table) {
		internalRawSchemaWriter.deleteTable(table.getName());
	}
	
	//method
	public void saveChanges() {
		internalRawSchemaWriter.saveChanges();
	}
	
	//method
	public void setColumnHeader(final IColumn column, final String columnHeader, final String newColumnHeader) {
		internalRawSchemaWriter.setColumnHeader(column.getParentTable().getName(), columnHeader, newColumnHeader);
	}
	
	public void setColumnParametrizedPropertyType(
		final IColumn column,
		final IParametrizedPropertyType<?> parametrizedPropertyType
	) {
		internalRawSchemaWriter.setColumnParametrizedPropertyType(
			column.getParentTable().getName(),
			column.getHeader(),
			parametrizedPropertyType.toDTO()
		);
	}
	
	//method
	public void setSchemaTimestamp(final Time schemaTimestamp) {
		internalRawSchemaWriter.setSchemaTimestamp(schemaTimestamp);
	}
	
	//method
	public void setTableName(final String tableName, final String newTableName) {
		internalRawSchemaWriter.setTableName(tableName, newTableName);
	}
}
