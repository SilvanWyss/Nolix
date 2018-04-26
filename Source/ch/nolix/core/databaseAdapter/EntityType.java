//package declaration
package ch.nolix.core.databaseAdapter;

//Java import
import java.lang.reflect.InvocationTargetException;

//own imports
import ch.nolix.core.bases.NamedElement;
import ch.nolix.core.container.List;
import ch.nolix.core.specification.Specification;

//class
public final class EntityType<E extends Entity>
extends NamedElement {

	//attribute
	private final Class<E> entityClass;
	
	//constructor
	public EntityType(final Class<E> entityClass) {
		
		super(entityClass.getSimpleName());
		
		this.entityClass = entityClass;
	}
	
	//method
	@SuppressWarnings("unchecked")
	public E createDefaultEntity() {
		try {
			
			final var constructor = getEntityClass().getDeclaredConstructors()[0];
			constructor.setAccessible(true);
			
			return (E)constructor.newInstance();
		} catch (
			final
			InstantiationException
			| IllegalAccessException
			| IllegalArgumentException
			| InvocationTargetException
			| SecurityException exception
		) {
			throw new RuntimeException(exception);
		}
	}
	
	//method
	public E createEntity(final int id, final Iterable<Specification> allPropertiesInOrder) {
		
		final var entity = createDefaultEntity();
		entity.setId(id);
		entity.set(allPropertiesInOrder);
		entity.setPersisted(); //TODO: program order is important because set makes state = updated.
		
		return entity;
	}
	
	//TODO: Make that the entity type stores its columns.
	//method
	public List<Column<?>> getColumns() {
		return createDefaultEntity().getColumns();
	}
	
	//method
	public Class<E> getEntityClass() {
		return entityClass;
	}
}
