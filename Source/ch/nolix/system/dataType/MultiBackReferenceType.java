//package declaration
package ch.nolix.system.dataType;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.PropertyKind;
import ch.nolix.system.schemaDataType.SchemaMultiBackReferenceType;

//class
public final class MultiBackReferenceType<E extends Entity> extends BaseBackReferenceType<E> {
	
	//constructor
	public MultiBackReferenceType(final Class<E> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.MULTI_BACK_REFERENCE;
	}
	
	//method
	@Override
	public SchemaMultiBackReferenceType toSchemaDataType(
		final IContainer<ch.nolix.system.databaseSchemaAdapter.EntitySet> schemaEntitySets
	) {
		
		final var backReferencedEntitiesName = getBackReferencedEntitiesName();
		
		return
		new SchemaMultiBackReferenceType(schemaEntitySets.getRefFirst(ses -> ses.hasName(backReferencedEntitiesName)));
	}
}
