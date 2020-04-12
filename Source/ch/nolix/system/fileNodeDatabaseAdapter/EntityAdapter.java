//package declaration
package ch.nolix.system.fileNodeDatabaseAdapter;

//own imports
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
	
	//constructor
	EntityAdapter(final BaseNode entitySpecification) {
		
		Validator
		.assertThat(entitySpecification)
		.thatIsNamed("entity specification")
		.isNotNull();
		
		this.entitySpecification = entitySpecification;
	}
	
	//method
	public E createPersistedEntity(final EntityType<E> entityType, final ValueCreator<BaseNode> valueCreator) {
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
