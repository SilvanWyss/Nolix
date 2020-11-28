//package declaration
package ch.nolix.system.datatype;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.PropertyKind;
import ch.nolix.system.schemadatatype.SchemaOptionalReferenceType;

//class
public final class OptionalReferenceType<E extends Entity> extends BaseReferenceType<E> {
	
	//constructor
	public OptionalReferenceType(final Class<E> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.OPTIONAL_REFERENCE;
	}
	
	//method
	@Override
	public SchemaOptionalReferenceType toSchemaDataType(
		final IContainer<ch.nolix.system.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		
		final var referencedEntitiesName = getReferencedEntitiesName();
		
		return new SchemaOptionalReferenceType(schemaEntitySets.getRefFirst(ses -> ses.hasName(referencedEntitiesName)));
	}
}
