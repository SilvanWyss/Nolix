//package declaration
package ch.nolix.system.database.parametrizeddatatype;

import ch.nolix.businessapi.databaseapi.propertytypeapi.PropertyType;
//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.database.parametrizedschemadatatype.ParametrizedSchemaOptionalValueType;

//class
public final class ParametrizedOptionalValueType<C> extends BaseParametrizedValueType<C> {
	
	//constructor
	public ParametrizedOptionalValueType(final Class<C> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public PropertyType getPropertyKind() {
		return PropertyType.OPTIONAL_VALUE;
	}
	
	//method
	@Override
	public ParametrizedSchemaOptionalValueType<C> toSchemaDataType(
		final IContainer<ch.nolix.system.database.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		return new ParametrizedSchemaOptionalValueType<>(getRefContentClass());
	}
}
