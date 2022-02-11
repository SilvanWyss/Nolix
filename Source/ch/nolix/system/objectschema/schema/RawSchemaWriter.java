//package declaration
package ch.nolix.system.objectschema.schema;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.element.time.base.Time;
import ch.nolix.system.objectschema.parametrizedpropertytype.SchemaImplementation;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParametrizedPropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawobjectschemaapi.schemaadapterapi.ISchemaWriter;

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
	public void addColumnToTable(final ITable<SchemaImplementation> table, final IColumn<SchemaImplementation> column) {
		internalRawSchemaWriter.addColumn(table.getName(), column.toDTO());
	}
	
	//method
	public void addTable(final ITable<SchemaImplementation> table) {
		internalRawSchemaWriter.addTable(table.toDTO());
	}
	
	//method
	public void deleteColumn(final IColumn<SchemaImplementation> column) {
		internalRawSchemaWriter.deleteColumn(column.getParentTable().getName(), column.getName());
	}
	
	//method
	public void deleteTable(final ITable<SchemaImplementation> table) {
		internalRawSchemaWriter.deleteTable(table.getName());
	}
	
	//method
	public void saveChangesAndReset() {
		internalRawSchemaWriter.saveChangesAndReset();
	}
	
	//method
	public void setColumnName(
		final IColumn<SchemaImplementation> column,
		final String columnName,
		final String newColumnName
	) {
		internalRawSchemaWriter.setColumnName(column.getParentTable().getName(), columnName, newColumnName);
	}
	
	public void setColumnParametrizedPropertyType(
		final IColumn<SchemaImplementation> column,
		final IParametrizedPropertyType<SchemaImplementation, ?> parametrizedPropertyType
	) {
		internalRawSchemaWriter.setColumnParametrizedPropertyType(
			column.getId(),
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
