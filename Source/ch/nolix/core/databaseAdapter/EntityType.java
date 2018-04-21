//package declaration
package ch.nolix.core.databaseAdapter;

//Java import
import java.lang.reflect.InvocationTargetException;

//own imports
import ch.nolix.core.bases.NamedElement;
import ch.nolix.core.container.List;

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
	public Entity createDefaultEntity() {
		try {
			
			final var constructor = entityClass.getDeclaredConstructors()[0];
			constructor.setAccessible(true);
			
			return (Entity)constructor.newInstance();
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
	public List<Column<?>> getColumns() {
		return createDefaultEntity().getColumns();
	}
	
	//method
	public Class<E> getEntityClass() {
		return entityClass;
	}
}
