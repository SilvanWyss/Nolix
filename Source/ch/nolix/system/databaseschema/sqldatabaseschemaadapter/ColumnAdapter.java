//package declaration
package ch.nolix.system.databaseschema.sqldatabaseschemaadapter;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.databaseschema.databaseschemaadapter.Column;
import ch.nolix.system.databaseschema.databaseschemaadapter.IColumnAdapter;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedSchemaDataType;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedSchemaValueType;

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
		.thatIsNamed(LowerCaseCatalogue.HEADER)
		.isNotBlank();
		
		this.header = header;
	}
	
	//method
	@Override
	public ParametrizedSchemaDataType<?> getDataType() {
		
		//TODO: return SchemaDataTypeFactory.fromSpecification(...)
		return new ParametrizedSchemaValueType<>(Object.class);
	}
	
	//method
	@Override
	public String getHeader() {
		return header;
	}
}
