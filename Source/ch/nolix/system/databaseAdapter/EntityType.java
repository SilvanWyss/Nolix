//package declaration
package ch.nolix.system.databaseAdapter;

//Java import
import java.lang.reflect.InvocationTargetException;

//own imports
import ch.nolix.common.attributeAPI.Named;
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.valueCreator.ValueCreator;
import ch.nolix.system.dataTypes.DataTypeHelper;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.EntityAccessor;

//class
public final class EntityType<E extends Entity> implements Named {
	
	//attribute
	private final Class<E> entityClass;
	
	//multi-attribute for caching
	private final LinkedList<Column<?>> columns = new LinkedList<>();
	
	//constructor
	public EntityType(final Class<E> entityClass) {	
		
		Validator.suppose(entityClass).thatIsNamed("Entity class").isNotNull();
		
		this.entityClass = entityClass;
		extractColumns();
	}
	
	//method
	@SuppressWarnings("unchecked")
	public E createEmptyEntity() {
		try {
			
			final var constructor = entityClass.getDeclaredConstructors()[0];
			constructor.setAccessible(true);
			
			return (E)constructor.newInstance();
		} catch (
			final
			InstantiationException
			| IllegalAccessException
			| IllegalArgumentException
			| InvocationTargetException
			| SecurityException
			exception
		) {
			throw new RuntimeException(exception);
		}
	}
	
	//method
	public E createPersistedEntity(
		final long id,
		final IContainer<BaseNode> valueSpecificationsInOrder,
		final ValueCreator<BaseNode> valueCreator
	) {
		
		final var entity = createEmptyEntity();
		EntityAccessor.setId(entity, id);
		EntityAccessor.setPersisted(entity);
		EntityAccessor.setValues(entity, valueSpecificationsInOrder, valueCreator);
		
		return entity;
	}
	
	//method
	public LinkedList<Column<?>> getColumns() {
		return columns;
	}
	
	//method
	public Class<E> getRefEntityClass() {
		return entityClass;
	}
	
	//method
	@Override
	public String getName() {	
		return entityClass.getSimpleName();
	}
	
	//method
	private void extractColumns() {
		
		columns.clear();
		
		for (final var p : createEmptyEntity().getRefProperties()) {
			columns.addAtBegin(new Column<>(p.getHeader(), DataTypeHelper.createDatatypeFor(p)));
		}
	}
}
