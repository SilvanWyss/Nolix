//package declaration
package ch.nolix.system.database.parametrizeddatatype;

import ch.nolix.core.container.IContainer;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

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
	public ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedOptionalReferenceType toSchemaDataType(
		final IContainer<ch.nolix.system.objectschema.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		//TODO: Refactor.
		/*
		final var referencedEntitiesName = getReferencedEntitiesName();
		
		return
		new ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedOptionalReferenceType(
			schemaEntitySets.getRefFirst(ses -> ses.hasName(referencedEntitiesName))
		);
		*/
		return null;
	}
}
