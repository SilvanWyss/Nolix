//package declaration
package ch.nolix.system.database.parametrizeddatatype;

import ch.nolix.businessapi.databaseapi.datatypeapi.DataType;
//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.database.parametrizedschemadatatype.SchemaOptionalReferenceType;

//class
public final class ParametrizedOptionalReferenceType<E extends Entity> extends BaseParametrizedReferenceType<E> {
	
	//constructor
	public ParametrizedOptionalReferenceType(final Class<E> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public DataType getPropertyKind() {
		return DataType.OPTIONAL_REFERENCE;
	}
	
	//method
	@Override
	public SchemaOptionalReferenceType toSchemaDataType(
		final IContainer<ch.nolix.system.database.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		
		final var referencedEntitiesName = getReferencedEntitiesName();
		
		return new SchemaOptionalReferenceType(schemaEntitySets.getRefFirst(ses -> ses.hasName(referencedEntitiesName)));
	}
}
