//package declaration
package ch.nolix.system.database.databaseadapter;

import ch.nolix.core.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.valuecreator.ValueCreator;
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
