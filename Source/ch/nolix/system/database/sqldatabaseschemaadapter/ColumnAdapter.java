//package declaration
package ch.nolix.system.database.sqldatabaseschemaadapter;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.validator.Validator;
import ch.nolix.system.database.databaseschemaadapter.Column;
import ch.nolix.system.database.databaseschemaadapter.IColumnAdapter;
import ch.nolix.system.database.schemadatatype.SchemaDataType;
import ch.nolix.system.database.schemadatatype.SchemaValueType;

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
