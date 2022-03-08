//package declaration
package ch.nolix.system.database.parametrizeddatatype;

import ch.nolix.core.container.IContainer;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

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
	public ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedOptionalValueType<C> toSchemaDataType(
		final IContainer<ch.nolix.system.objectschema.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		//TODO: Refactor.
		return null;
		/*
		return
		new ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedOptionalValueType<>(
			getRefContentClass()
		);
		*/
	}
}
