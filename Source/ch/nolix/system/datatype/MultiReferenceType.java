//package declaration
package ch.nolix.system.datatype;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.PropertyKind;
import ch.nolix.system.schemadatatype.SchemaMultiReferenceType;

//class
public final class MultiReferenceType<E extends Entity> extends BaseReferenceType<E> {
	
	//constructor
	public MultiReferenceType(final Class<E> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.MULTI_REFERENCE;
	}
	
	//method
	@Override
	public SchemaMultiReferenceType toSchemaDataType(
		final IContainer<ch.nolix.system.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		
		final var referencedEntitiesName = getReferencedEntitiesName();
		
		return new SchemaMultiReferenceType(schemaEntitySets.getRefFirst(ses -> ses.hasName(referencedEntitiesName)));
	}
}
