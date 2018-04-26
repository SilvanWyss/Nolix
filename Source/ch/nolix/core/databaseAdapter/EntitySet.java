//package declaration
package ch.nolix.core.databaseAdapter;

//own imports
import ch.nolix.core.bases.NamedElement;
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.primitive.invalidArgumentException.Argument;
import ch.nolix.primitive.invalidArgumentException.ErrorPredicate;
import ch.nolix.primitive.invalidArgumentException.InvalidArgumentException;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.validator2.Validator;

//class
public final class EntitySet<E extends Entity>
extends NamedElement {

	//attributes
	private final DatabaseConnectorWrapper<?> databaseConnectorWrapper;
	private final EntityType<E> entityType;
	
	//multi-attributes
	private final List<Column<?>> columns = new List<Column<?>>();
	private final List<E> loadedAndCreatedEntities = new List<E>();
	
	//static method
	static EntitySet<Entity> createEntitySet(
		final DatabaseConnectorWrapper<?> databaseConnectorWrapper,
		final EntityType<Entity> entityType
	) {
		return new EntitySet<Entity>(databaseConnectorWrapper, entityType);
	}
	
	//package-visible constructor
	EntitySet(
		final DatabaseConnectorWrapper<?> databaseConnectorWrapper,
		final EntityType<E> entityType
	) {
		
		super(entityType.getName());
		
		Validator
		.suppose(databaseConnectorWrapper)
		.thatIsOfType(DatabaseConnectorWrapper.class)
		.isNotNull();
		
		Validator
		.suppose(entityType)
		.thatIsOfType(EntityType.class)
		.isNotNull();
		
		this.databaseConnectorWrapper = databaseConnectorWrapper;
		this.entityType = entityType;
				
		columns
		.addAtEnd(new Column<>(PascalCaseNameCatalogue.ID, new PropertyType<Integer>(Integer.class)))	
		.addAtEnd(entityType.getColumns());
	}
	
	//method
	public EntitySet<E> addEntities(final Iterable<E> entities) {
		
		entities.forEach(e -> addEntity(e));
		
		return this;
	}
	
	//method
	public EntitySet<E> addEntity(final E entity) {
		
		if (!entity.isCreated()) {
			throw new InvalidArgumentException(
				new Argument(entity),
				new ErrorPredicate("is not created")
			);
		}
		
		loadedAndCreatedEntities.addAtEndRegardingSingularity(entity);
		
		databaseConnectorWrapper.noteAddEntity(this, entity);
		
		return this;
	}
	
	//method
	@SuppressWarnings("unchecked")
	public EntitySet<E> addEntity(final E... entities) {
		return addEntities(new ReadContainer<E>(entities));
	}
	
	//method
	public EntitySet<E> deleteEntity(final E entity) {
				
		entity.setDeleted();
		
		loadedAndCreatedEntities.removeFirst(entity);
		
		databaseConnectorWrapper.noteDeleteEntity(this, entity);
		
		return this;
	}
	
	//method
	public IContainer<Column<?>> getColumns() {
		return columns;
	}
	
	//method
	public EntityType<E> getEntityType() {
		return entityType;
	}
	
	//method
	public IContainer<E> getRefEntities() {
		
		final var entities =  databaseConnectorWrapper.getEntities(this);
		final var newlyLoadedEntities = new List<E>();
		
		for (final var e : entities) {
			
			if (!loadedAndCreatedEntities.contains(e2 -> e2.getId() == e.getId())) {
			
				if (!e.isPersisted()) {
					throw new InvalidArgumentException(
						new Argument(e),
						new ErrorPredicate("is not persisted")
					);
				}						
			
				newlyLoadedEntities.addAtEnd(e);
			}
		}
		
		loadedAndCreatedEntities.addAtEnd(newlyLoadedEntities);
		
		return loadedAndCreatedEntities;
	}
	
	//method
	public E getRefEntityById(final int id) {
		
		final var loadedEntity = loadedAndCreatedEntities.getRefFirstOrNull(e -> e.getId() == id);
		if (loadedEntity != null) {
			return loadedEntity;
		}
		
		final var entity = databaseConnectorWrapper.getEntity(id, this);
		if (!entity.isPersisted()) {
			throw new InvalidStateException(entity, "is not persisted");
		}
		loadedAndCreatedEntities.addAtEnd(entity);
		return entity;
	}
	
	//method
	public List<E> getRefUpdatedEntities() {
		return loadedAndCreatedEntities.getRefSelected(e -> e.isUpdated());
	}
	
	//method
	public boolean hasChanges() {
		return loadedAndCreatedEntities.containsAny();
	}
	
	//package-visible method
	void reset() {
		loadedAndCreatedEntities.forEach(e -> e.setRejected());
		loadedAndCreatedEntities.clear();
	}
}
