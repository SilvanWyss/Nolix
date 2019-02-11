//package declaration
package ch.nolix.system.documentNodeDatabaseAdapter;

import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.skillAPI.Identified;
import ch.nolix.core.validator.Validator;
import ch.nolix.system.databaseAdapter.Entity;
import ch.nolix.system.databaseAdapter.EntityType;

//class
public final class EntityAdapter<E extends Entity> implements Identified {

	//attribute
	private final DocumentNodeoid entitySpecification;
	
	//package-visible constructor
	EntityAdapter(final DocumentNodeoid entitySpecification) {
		
		Validator
		.suppose(entitySpecification)
		.thatIsNamed("entity specification")
		.isNotNull();
		
		this.entitySpecification = entitySpecification;
	}
	
	//method
	public E createPersistedEntity(final EntityType<E> entityType) {
		return entityType.createPersistedEntity(
			getId(),
			entitySpecification.getRefAttributes().getContainerWithoutFirst()
		);
	}
	
	//method
	@Override
	public int getId() {
		return entitySpecification.getFirstAttributeAsInt();
	}
	
	//method
	public void updateFrom(final E entity) {
		entitySpecification.resetAttributes(
			entity.getRowSpecification().getRefAttributes()
		);
	}
}
