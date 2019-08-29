//package declaration
package ch.nolix.system.fileNodeDatabaseAdapter;

import ch.nolix.core.attributeAPI.Identified;
import ch.nolix.core.node.BaseNode;
import ch.nolix.core.validator.Validator;
import ch.nolix.system.databaseAdapter.Entity;
import ch.nolix.system.databaseAdapter.EntityType;

//class
public final class EntityAdapter<E extends Entity> implements Identified {

	//attribute
	private final BaseNode entitySpecification;
	
	//package-visible constructor
	EntityAdapter(final BaseNode entitySpecification) {
		
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
			entitySpecification.getRefAttributes().withoutFirst()
		);
	}
	
	//method
	@Override
	public long getId() {
		return entitySpecification.getFirstAttributeAsInt();
	}
	
	//method
	public void updateFrom(final E entity) {
		entitySpecification.resetAttributes(
			entity.getRowSpecification().getRefAttributes()
		);
	}
}
