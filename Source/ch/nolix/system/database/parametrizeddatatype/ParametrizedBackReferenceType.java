//package declaration
package ch.nolix.system.database.parametrizeddatatype;

import ch.nolix.businessapi.databaseapi.datatypeapi.DataType;
//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.database.parametrizedschemadatatype.SchemaBackReferenceType;

//class
public final class ParametrizedBackReferenceType<E extends Entity> extends BaseParametrizedBackReferenceType<E> {
	
	//constructor
	public ParametrizedBackReferenceType(final Class<E> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public DataType getPropertyKind() {
		return DataType.BACK_REFERENCE;
	}
	
	//method
	@Override
	public SchemaBackReferenceType toSchemaDataType(
		final IContainer<ch.nolix.system.database.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		
		final var backReferencedEntitiesName = getBackReferencedEntitiesName();
		
		return
		new SchemaBackReferenceType(schemaEntitySets.getRefFirst(ses -> ses.hasName(backReferencedEntitiesName)));
	}
}
