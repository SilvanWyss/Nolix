//package declaration
package ch.nolix.system.database.parametrizeddatatype;

import ch.nolix.businessapi.databaseapi.propertytypeapi.PropertyType;
//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.database.parametrizedschemadatatype.ParametrizedSchemaReferenceType;

//class
public final class ParametrizedReferenceType<E extends Entity> extends BaseParametrizedReferenceType<E> {
	
	//constructor
	public ParametrizedReferenceType(final Class<E> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public PropertyType getPropertyKind() {
		return PropertyType.REFERENCE;
	}
	
	//method
	@Override
	public ParametrizedSchemaReferenceType toSchemaDataType(
		final IContainer<ch.nolix.system.database.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		
		final var referencedEntitiesName = getReferencedEntitiesName();
		
		return new ParametrizedSchemaReferenceType(schemaEntitySets.getRefFirst(ses -> ses.hasName(referencedEntitiesName)));
	}
}
