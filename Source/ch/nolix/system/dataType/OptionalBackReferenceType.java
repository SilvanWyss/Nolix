//package declaration
package ch.nolix.system.dataType;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.PropertyKind;
import ch.nolix.system.schemaDataType.SchemaOptionalBackReferenceType;

//class
public final class OptionalBackReferenceType<E extends Entity> extends BaseBackReferenceType<E> {
	
	//constructor
	public OptionalBackReferenceType(final Class<E> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.OPTIONAL_BACK_REFERENCE;
	}
	
	//method
	@Override
	public SchemaOptionalBackReferenceType toSchemaDataType(
		final IContainer<ch.nolix.system.databaseSchemaAdapter.EntitySet> schemaEntitySets
	) {
		
		final var backReferencedEntitiesName = getBackReferencedEntitiesName();
		
		return
		new SchemaOptionalBackReferenceType(schemaEntitySets.getRefFirst(ses -> ses.hasName(backReferencedEntitiesName)));
	}
}
