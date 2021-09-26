//class
package ch.nolix.system.database.parametrizeddatatype;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.objectschema.parametrizedpropertytype.IdType;
import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;

//class
public final class ParametrizedIdType extends BaseParametrizedControlType<Long> {
	
	//constructor
	public ParametrizedIdType() {
		super(Long.class);
	}
	
	//method
	@Override
	public PropertyType getPropertyKind() {
		return PropertyType.ID;
	}
	
	//method
	@Override
	public IdType toSchemaDataType(
		final IContainer<ch.nolix.system.objectschema.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		return new IdType();
	}
}
