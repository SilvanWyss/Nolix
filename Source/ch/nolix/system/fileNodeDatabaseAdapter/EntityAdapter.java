//package declaration
package ch.nolix.system.fileNodeDatabaseAdapter;

import ch.nolix.common.attributeAPI.Identified;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.valueCreator.ValueCreator;
import ch.nolix.system.databaseAdapter.EntityType;
import ch.nolix.system.entity.Entity;

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
	public E createPersistedEntity(final EntityType<E> entityType, final ValueCreator valueCreator) {
		return entityType.createPersistedEntity(
			getId(),
			entitySpecification.getRefAttributes().withoutFirst(),
			valueCreator
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
