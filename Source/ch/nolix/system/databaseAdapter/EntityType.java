//package declaration
package ch.nolix.system.databaseAdapter;

//Java import
import java.lang.reflect.InvocationTargetException;

//own imports
import ch.nolix.common.attributeAPI.Named;
import ch.nolix.common.constants.PascalCaseNameCatalogue;
import ch.nolix.common.containers.List;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.valueCreator.ValueCreator;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.IdPropertyType;
import ch.nolix.system.entity.Propertyoid;

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
		columns = extractColumns();
	}
	
	//method
	@SuppressWarnings("unchecked")
	public E createDefaultEntity() {
		try {
			
			final var constructor = getEntityClass().getDeclaredConstructors()[0];
			constructor.setAccessible(true);
			
			final var entity = (E)constructor.newInstance();
			getColumns();
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
	public E createPersistedEntity(
		final long id,
		final Iterable<BaseNode> valuesInOrder,
		final ValueCreator valueCreator
	) {
		
		final var entity = createDefaultEntity();
		entity.setId(id);
		entity.setValues(valuesInOrder, valueCreator);
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
	
	//method
	public final List<Column<?>> extractColumns() {
		
		final var constructor = getEntityClass().getDeclaredConstructors()[0];
		constructor.setAccessible(true);
		try {
			final var entity = (Entity)constructor.newInstance();
			entity.getRefProperties();
			
			final var columns = new List<Column<?>>(new Column<>(PascalCaseNameCatalogue.ID, new IdPropertyType()));
			
			Class<?> lClass = entityClass.getClass();
			while (lClass != null) {
				
				for (final var f : lClass.getDeclaredFields()) {
					if (Propertyoid.class.isAssignableFrom(f.getType())) {
						try {
							
							f.setAccessible(true);
							
							final Propertyoid<?> property = (Propertyoid<?>)(f.get(this));
							
							Validator.suppose(property).isOfType(Propertyoid.class);
							
							columns.addAtEnd(
								new Column<>(
									f.getName(),
									property.getPropertyType()
								)
							);
						}
						catch (final IllegalArgumentException | IllegalAccessException exception) {
							throw new RuntimeException(exception);
						}
					}
				}
				
				lClass = lClass.getSuperclass();
			}
			
			return columns;
		}
		catch (
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
}
