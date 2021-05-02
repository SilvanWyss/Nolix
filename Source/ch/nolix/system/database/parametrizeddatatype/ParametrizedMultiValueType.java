//package declaration
package ch.nolix.system.database.parametrizeddatatype;

import ch.nolix.businessapi.databaseapi.propertytypeapi.PropertyType;
//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.database.parametrizedschemadatatype.ParametrizedSchemaMultiValueType;

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
	public ParametrizedSchemaMultiValueType<C> toSchemaDataType(
		final IContainer<ch.nolix.system.database.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		return new ParametrizedSchemaMultiValueType<>(getRefContentClass());
	}
}
