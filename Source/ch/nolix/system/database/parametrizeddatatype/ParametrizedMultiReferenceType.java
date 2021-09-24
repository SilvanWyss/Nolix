//package declaration
package ch.nolix.system.database.parametrizeddatatype;

import ch.nolix.common.container.IContainer;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.techapi.databasecommonapi.propertytypeapi.PropertyType;

//class
public final class ParametrizedMultiReferenceType<E extends Entity> extends BaseParametrizedReferenceType<E> {
	
	//constructor
	public ParametrizedMultiReferenceType(final Class<E> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public PropertyType getPropertyKind() {
		return PropertyType.MULTI_REFERENCE;
	}
	
	//method
	@Override
	public ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedMultiReferenceType toSchemaDataType(
		final IContainer<ch.nolix.system.objectschema.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		//TODO: Refactor.
		/*
		final var referencedEntitiesName = getReferencedEntitiesName();
		
		return
		new ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedMultiReferenceType(
			schemaEntitySets.getRefFirst(ses -> ses.hasName(referencedEntitiesName))
		);
		*/
		return null;
	}
}
