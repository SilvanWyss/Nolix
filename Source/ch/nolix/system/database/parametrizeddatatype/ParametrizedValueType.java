//package declaration
package ch.nolix.system.database.parametrizeddatatype;

import ch.nolix.core.container.IContainer;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

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
	public ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedValueType<C> toSchemaDataType(
		final IContainer<ch.nolix.system.objectschema.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		return new ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedValueType<>(getRefContentClass());
	}
}
