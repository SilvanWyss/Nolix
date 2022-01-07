//package declaration
package ch.nolix.system.objectschema.sqldatabaseschemaadapter;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.objectschema.databaseschemaadapter.Column;
import ch.nolix.system.objectschema.databaseschemaadapter.IColumnAdapter;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedPropertyType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedValueType;

//class
public final class ColumnAdapter implements IColumnAdapter {
	
	//attribute
	private String name;
	
	//constructor
	public ColumnAdapter(final Column column) {
		this(column.getName());
	}
	
	//constructor
	public ColumnAdapter(final String name) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		
		this.name = name;
	}
	
	//method
	@Override
	public ParametrizedPropertyType<?> getDataType() {
		
		//TODO: return SchemaDataTypeFactory.fromSpecification(...)
		return new ParametrizedValueType<>(Object.class);
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
}
