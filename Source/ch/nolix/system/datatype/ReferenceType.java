//package declaration
package ch.nolix.system.datatype;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.PropertyKind;
import ch.nolix.system.schemadatatype.SchemaReferenceType;

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
		final IContainer<ch.nolix.system.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		
		final var referencedEntitiesName = getReferencedEntitiesName();
		
		return new SchemaReferenceType(schemaEntitySets.getRefFirst(ses -> ses.hasName(referencedEntitiesName)));
	}
}
