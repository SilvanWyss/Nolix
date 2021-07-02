//package declaration
package ch.nolix.system.database.parametrizeddatatype;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.techapi.databaseschemaapi.propertytypeapi.PropertyType;

//class
public final class ParametrizedMultiValueType<C> extends BaseParametrizedValueType<C> {
	
	//constructor
	public ParametrizedMultiValueType(final Class<C> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public PropertyType getPropertyKind() {
		return PropertyType.MULTI_VALUE;
	}
	
	//method
	@Override
	public ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedMultiValueType<C> toSchemaDataType(
		final IContainer<ch.nolix.system.databaseschema.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		return
		new ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedMultiValueType<>(getRefContentClass());
	}
}
