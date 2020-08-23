//package declaration
package ch.nolix.system.SQLDatabaseSchemaAdapter;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.validator.Validator;
import ch.nolix.system.databaseSchemaAdapter.Column;
import ch.nolix.system.databaseSchemaAdapter.IColumnAdapter;
import ch.nolix.system.schemaDataType.SchemaDataType;
import ch.nolix.system.schemaDataType.SchemaValueType;

//class
public final class ColumnAdapter implements IColumnAdapter {
	
	//attribute
	private String header;
	
	//constructor
	public ColumnAdapter(final Column column) {
		this(column.getHeader());
	}
	
	//constructor
	public ColumnAdapter(final String header) {
		
		Validator
		.assertThat(header)
		.thatIsNamed(VariableNameCatalogue.HEADER)
		.isNotBlank();
		
		this.header = header;
	}
	
	//method
	@Override
	public SchemaDataType<?> getDataType() {
		
		//TODO: return SchemaDataTypeFactory.fromSpecification(...)
		return new SchemaValueType<>(Object.class);
	}
	
	//method
	@Override
	public String getHeader() {
		return header;
	}
}
