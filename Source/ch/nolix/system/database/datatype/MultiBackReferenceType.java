//package declaration
package ch.nolix.system.database.datatype;

import ch.nolix.businessapi.databaseapi.datatypeapi.DataType;
//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.database.schemadatatype.SchemaMultiBackReferenceType;

//class
public final class MultiBackReferenceType<E extends Entity> extends BaseBackReferenceType<E> {
	
	//constructor
	public MultiBackReferenceType(final Class<E> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public DataType getPropertyKind() {
		return DataType.MULTI_BACK_REFERENCE;
	}
	
	//method
	@Override
	public SchemaMultiBackReferenceType toSchemaDataType(
		final IContainer<ch.nolix.system.database.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		
		final var backReferencedEntitiesName = getBackReferencedEntitiesName();
		
		return
		new SchemaMultiBackReferenceType(schemaEntitySets.getRefFirst(ses -> ses.hasName(backReferencedEntitiesName)));
	}
}
