//package declaration
package ch.nolix.system.database.databaseadapter;

import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.valuecreator.ValueCreator;
import ch.nolix.system.database.entity.Entity;

//class
public abstract class BaseEntitySetAdapter<E extends Entity> implements Named {
	
	//attributes
	private final EntityType<E> entityType;
	private final ValueCreator<BaseNode> valueCreator;
	
	//constructor
	public BaseEntitySetAdapter(final EntityType<E> entityType, final ValueCreator<BaseNode> valueCreator) {
		
		Validator.assertThat(entityType).thatIsNamed(EntityType.class).isNotNull();
		Validator.assertThat(valueCreator).thatIsNamed(ValueCreator.class).isNotNull();
		
		this.entityType = entityType;
		this.valueCreator = valueCreator;
	}
	
	//method declaration
	public abstract boolean containsEntity(long id);
	
	//method declaration
	public abstract LinkedList<E> getEntities();
	
	//method declaration
	public abstract E getEntity(long ide);
	
	//method
	protected final EntityType<E> getEntityType() {
		return entityType;
	}
	
	//method
	protected final ValueCreator<BaseNode> getValueCreator() {
		return valueCreator;
	}
}
