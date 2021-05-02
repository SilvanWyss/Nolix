//package declaration
package ch.nolix.system.database.parametrizeddatatype;

import ch.nolix.businessapi.databaseapi.propertytypeapi.PropertyType;
//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.database.parametrizedschemadatatype.ParametrizedSchemaValueType;

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
		final IContainer<ch.nolix.system.database.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		return new ParametrizedSchemaValueType<>(getRefContentClass());
	}
}
