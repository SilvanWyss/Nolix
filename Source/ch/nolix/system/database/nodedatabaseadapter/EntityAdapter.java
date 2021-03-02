//package declaration
package ch.nolix.system.database.nodedatabaseadapter;

//own imports
import ch.nolix.common.attributeapi.Identified;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.valuecreator.ValueCreator;
import ch.nolix.system.database.databaseadapter.EntityType;
import ch.nolix.system.database.entity.Entity;

//class
public final class EntityAdapter<E extends Entity> implements Identified {

	//attribute
	private final BaseNode entitySpecification;
	
	//visibility-reduced constructor
	EntityAdapter(final BaseNode entitySpecification) {
		
		Validator.assertThat(entitySpecification).thatIsNamed("entity specification").isNotNull();
		
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
		return entitySpecification.getFirstAttributeAsLong();
	}
	
	//method
	public void updateFrom(final E entity) {
		entitySpecification.resetAttributes(
			entity.getRowSpecification().getRefAttributes()
		);
	}
}
