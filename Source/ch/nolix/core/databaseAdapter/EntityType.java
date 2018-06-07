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
	
	//multi-attribute
	private final List<Column<?>> columns;
	
	//constructor
	public EntityType(final Class<E> entityClass) {
		
		super(entityClass.getSimpleName());
		
		this.entityClass = entityClass;
		columns = createDefaultEntity().getColumns();
	}
	
	//method
	@SuppressWarnings("unchecked")
	public E createDefaultEntity() {
		try {
			
			final var constructor = getEntityClass().getDeclaredConstructors()[0];
			constructor.setAccessible(true);
			
			final var entity = (E)constructor.newInstance();
			entity.getColumns();
			return entity;
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
	public E createPersistedEntity(final int id, final Iterable<Specification> allValuesInOrder) {
		
		final var entity = createDefaultEntity();
		entity.setId(id);
		entity.setAllValues(allValuesInOrder);
		entity.setPersisted();
		
		return entity;
	}
	
	//method
	public List<Column<?>> getColumns() {
		return columns;
	}
	
	//method
	public Class<E> getEntityClass() {
		return entityClass;
	}
}
