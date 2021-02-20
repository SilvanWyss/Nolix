//package declaration
package ch.nolix.system.database.datatype;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.database.entity.PropertyKind;
import ch.nolix.system.database.schemadatatype.SchemaReferenceType;

//class
public final class ReferenceType<E extends Entity> extends BaseReferenceType<E> {
	
	//constructor
	public ReferenceType(final Class<E> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.REFERENCE;
	}
	
	//method
	@Override
	public SchemaReferenceType toSchemaDataType(
		final IContainer<ch.nolix.system.database.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		
		final var referencedEntitiesName = getReferencedEntitiesName();
		
		return new SchemaReferenceType(schemaEntitySets.getRefFirst(ses -> ses.hasName(referencedEntitiesName)));
	}
}
