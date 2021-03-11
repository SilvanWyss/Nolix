//package declaration
package ch.nolix.system.database.parametrizeddatatype;

import ch.nolix.businessapi.databaseapi.datatypeapi.DataType;
//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.database.parametrizedschemadatatype.ParametrizedSchemaOptionalBackReferenceType;

//class
public final class ParametrizedOptionalBackReferenceType<E extends Entity> extends BaseParametrizedBackReferenceType<E> {
	
	//constructor
	public ParametrizedOptionalBackReferenceType(final Class<E> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public DataType getPropertyKind() {
		return DataType.OPTIONAL_BACK_REFERENCE;
	}
	
	//method
	@Override
	public ParametrizedSchemaOptionalBackReferenceType toSchemaDataType(
		final IContainer<ch.nolix.system.database.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		
		final var backReferencedEntitiesName = getBackReferencedEntitiesName();
		
		return
		new ParametrizedSchemaOptionalBackReferenceType(schemaEntitySets.getRefFirst(ses -> ses.hasName(backReferencedEntitiesName)));
	}
}
