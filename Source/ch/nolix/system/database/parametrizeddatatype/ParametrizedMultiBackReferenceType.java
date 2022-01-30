//package declaration
package ch.nolix.system.database.parametrizeddatatype;

import ch.nolix.core.container.IContainer;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

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
	public ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedMultiBackReferenceType toSchemaDataType(
		final IContainer<ch.nolix.system.objectschema.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		//TODO: Refactor.
		/*
		final var backReferencedEntitiesName = getBackReferencedEntitiesName();
		
		return
		new ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedMultiBackReferenceType(
			schemaEntitySets.getRefFirst(ses -> ses.hasName(backReferencedEntitiesName))
		);
		*/
		return null;
	}
}
