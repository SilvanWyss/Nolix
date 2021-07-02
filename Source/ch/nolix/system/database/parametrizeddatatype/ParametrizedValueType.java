//package declaration
package ch.nolix.system.database.parametrizeddatatype;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedSchemaValueType;
import ch.nolix.techapi.databaseschemaapi.propertytypeapi.PropertyType;

//class
public final class ParametrizedValueType<C> extends BaseParametrizedValueType<C> {
	
	//constructor
	public ParametrizedValueType(final Class<C> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public PropertyType getPropertyKind() {
		return PropertyType.VALUE;
	}
	
	//method
	@Override
	public ParametrizedSchemaValueType<C> toSchemaDataType(
		final IContainer<ch.nolix.system.databaseschema.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		return new ParametrizedSchemaValueType<>(getRefContentClass());
	}
}
