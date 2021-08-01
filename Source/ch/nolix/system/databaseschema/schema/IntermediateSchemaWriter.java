//package declaration
package ch.nolix.system.databaseschema.schema;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.time.base.Time;
import ch.nolix.techapi.databaseschemaapi.intermediateschemaapi.IIntermediateSchemaWriter;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IParametrizedPropertyType;
import ch.nolix.techapi.databaseschemaapi.schemaapi.ITable;

//class
final class IntermediateSchemaWriter {
	
	//attribute
	private final IIntermediateSchemaWriter internalIntermediateSchemaWriter;
	
	//constructor
	public IntermediateSchemaWriter(final IIntermediateSchemaWriter internalIntermediateSchemaWriter) {
		
		Validator
		.assertThat(internalIntermediateSchemaWriter)
		.thatIsNamed("internal intermediate schema writer")
		.isNotNull();
		
		this.internalIntermediateSchemaWriter = internalIntermediateSchemaWriter;
	}
	
	//method
	public void addColumnToTable(final ITable<?, ?, ?> table, final IColumn<?, ?> column) {
		internalIntermediateSchemaWriter.addColumn(table.getName(), column.toDTO());
	}
	
	//method
	public void addTable(final ITable<?, ?, ?> table) {
		internalIntermediateSchemaWriter.addTable(table.toDTO());
	}
	
	//method
	public void deleteColumn(final IColumn<?, ?> column) {
		internalIntermediateSchemaWriter.deleteColumn(column.getParentTable().getName(), column.getHeader());
	}
	
	//method
	public void deleteTable(final ITable<?, ?, ?> table) {
		internalIntermediateSchemaWriter.deleteTable(table.getName());
	}
	
	//method
	public void saveChanges() {
		internalIntermediateSchemaWriter.saveChanges();
	}
	
	//method
	public void setColumnHeader(final IColumn<?, ?> column, final String columnHeader, final String newColumnHeader) {
		internalIntermediateSchemaWriter.setColumnHeader(column.getParentTable().getName(), columnHeader, newColumnHeader);
	}
	
	public void setColumnParametrizedPropertyType(
		final IColumn<?, ?> column,
		final IParametrizedPropertyType<?> parametrizedPropertyType
	) {
		internalIntermediateSchemaWriter.setColumnParametrizedPropertyType(
			column.getParentTable().getName(),
			column.getHeader(),
			parametrizedPropertyType.toDTO()
		);
	}
	
	//method
	public void setSchemaTimestamp(final Time schemaTimestamp) {
		internalIntermediateSchemaWriter.setSchemaTimestamp(schemaTimestamp);
	}
	
	//method
	public void setTableName(final String tableName, final String newTableName) {
		internalIntermediateSchemaWriter.setTableName(tableName, newTableName);
	}
}
