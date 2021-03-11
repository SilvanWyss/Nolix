//package declaration
package ch.nolix.system.database.parametrizeddatatype;

import ch.nolix.businessapi.databaseapi.datatypeapi.DataType;
//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.database.parametrizedschemadatatype.ParametrizedSchemaMultiReferenceType;

//class
public final class ParametrizedMultiReferenceType<E extends Entity> extends BaseParametrizedReferenceType<E> {
	
	//constructor
	public ParametrizedMultiReferenceType(final Class<E> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public DataType getPropertyKind() {
		return DataType.MULTI_REFERENCE;
	}
	
	//method
	@Override
	public ParametrizedSchemaMultiReferenceType toSchemaDataType(
		final IContainer<ch.nolix.system.database.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		
		final var referencedEntitiesName = getReferencedEntitiesName();
		
		return new ParametrizedSchemaMultiReferenceType(schemaEntitySets.getRefFirst(ses -> ses.hasName(referencedEntitiesName)));
	}
}
