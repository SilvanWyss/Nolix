//package declaration
package ch.nolix.system.database.nodedatabaseadapter;

import ch.nolix.core.attributeapi.mandatoryattributeapi.Identified;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.valuecreator.ValueCreator;
import ch.nolix.system.database.databaseadapter.EntityType;
import ch.nolix.system.database.entity.Entity;

//class
public final class EntityAdapter<E extends Entity> implements Identified {

	//attribute
	private final BaseNode entitySpecification;
	
	//constructor
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
