//package declaration
package ch.nolix.system.databaseAdapter;

//Java import
import java.lang.reflect.InvocationTargetException;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.skillAPI.Named;

//class
public final class EntityType<E extends Entity> implements Named {

	//attribute
	private final String name;
	private final Class<E> entityClass;
	
	//multi-attribute
	private final List<Column<?>> columns;
	
	//constructor
	public EntityType(final Class<E> entityClass) {	
		name = entityClass.getSimpleName();
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
	public E createPersistedEntity(final int id, final Iterable<DocumentNodeoid> valuesInOrder) {
		
		final var entity = createDefaultEntity();
		entity.setId(id);
		entity.setValues(valuesInOrder);
		entity.setPersisted();
		
		return entity;
	}
	
	//method
	public List<Column<?>> getAnyDataAndReferenceColumns() {
		return columns.getRefSelected(c -> c.isAnyDataOrReferenceColumn());
	}
	
	//method
	public List<Column<?>> getColumns() {
		return columns;
	}
		
	//method
	public Class<E> getEntityClass() {
		return entityClass;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
}
