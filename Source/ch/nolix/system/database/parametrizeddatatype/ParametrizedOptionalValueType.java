//package declaration
package ch.nolix.system.database.parametrizeddatatype;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.techapi.databasecommonapi.propertytypeapi.PropertyType;

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
	public ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedOptionalValueType<C> toSchemaDataType(
		final IContainer<ch.nolix.system.databaseschema.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		return
		new ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedOptionalValueType<>(
			getRefContentClass()
		);
	}
}
