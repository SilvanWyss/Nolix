//package declaration
package ch.nolix.system.database.parametrizeddatatype;

import ch.nolix.common.container.IContainer;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedSchemaOptionalReferenceType;
import ch.nolix.techapi.databaseschemaapi.propertytypeapi.PropertyType;

//class
public final class ParametrizedOptionalReferenceType<E extends Entity> extends BaseParametrizedReferenceType<E> {
	
	//constructor
	public ParametrizedOptionalReferenceType(final Class<E> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public PropertyType getPropertyKind() {
		return PropertyType.OPTIONAL_REFERENCE;
	}
	
	//method
	@Override
	public ParametrizedSchemaOptionalReferenceType toSchemaDataType(
		final IContainer<ch.nolix.system.databaseschema.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		
		final var referencedEntitiesName = getReferencedEntitiesName();
		
		return
		new ParametrizedSchemaOptionalReferenceType(
			schemaEntitySets.getRefFirst(ses -> ses.hasName(referencedEntitiesName))
		);
	}
}
