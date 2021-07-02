//package declaration
package ch.nolix.system.database.parametrizeddatatype;

import ch.nolix.common.container.IContainer;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedSchemaMultiBackReferenceType;
import ch.nolix.techapi.databaseschemaapi.propertytypeapi.PropertyType;

//class
public final class ParametrizedMultiBackReferenceType<E extends Entity> extends BaseParametrizedBackReferenceType<E> {
	
	//constructor
	public ParametrizedMultiBackReferenceType(final Class<E> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public PropertyType getPropertyKind() {
		return PropertyType.MULTI_BACK_REFERENCE;
	}
	
	//method
	@Override
	public ParametrizedSchemaMultiBackReferenceType toSchemaDataType(
		final IContainer<ch.nolix.system.databaseschema.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		
		final var backReferencedEntitiesName = getBackReferencedEntitiesName();
		
		return
		new ParametrizedSchemaMultiBackReferenceType(
			schemaEntitySets.getRefFirst(ses -> ses.hasName(backReferencedEntitiesName))
		);
	}
}
