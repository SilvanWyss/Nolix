//package declaration
package ch.nolix.system.databaseAdapter;

//own imports
import ch.nolix.common.attributeAPI.Named;
import ch.nolix.common.containers.List;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.valueCreator.ValueCreator;
import ch.nolix.system.entity.Entity;

//abstract class
public abstract class BaseEntitySetAdapter<E extends Entity> implements Named {
	
	//attributes
	private final EntityType<E> entityType;
	private final ValueCreator valueCreator;
	
	//constructor
	public BaseEntitySetAdapter(final EntityType<E> entityType, final ValueCreator valueCreator) {
		
		Validator.suppose(entityType).thatIsNamed(EntityType.class).isNotNull();
		Validator.suppose(valueCreator).thatIsNamed(ValueCreator.class).isNotNull();
		
		this.entityType = entityType;
		this.valueCreator = valueCreator;
	}
	
	//method declaration
	public abstract boolean containsEntity(long id);
	
	//method declaration
	public abstract List<E> getEntities();
	
	//method declaration
	public abstract E getEntity(long ide);
	
	//method
	protected final EntityType<E> getEntityType() {
		return entityType;
	}
	
	//method
	protected final ValueCreator getValueCreator() {
		return valueCreator;
	}
}
