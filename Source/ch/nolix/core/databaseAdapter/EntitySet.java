//package declaration
package ch.nolix.core.databaseAdapter;

//own imports
import ch.nolix.core.bases.NamedElement;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.validator2.Validator;

//class
public final class EntitySet<E extends Entity> extends NamedElement {

	//attributes
	private final DatabaseAdapter parentDatabaseAdapter;
	private final EntityType<E> entityType;
	
	//multi-attributes
	private final List<Column<?>> columns;
	private final List<E> loadedAndCreatedEntities = new List<E>();
	
	//package-visible static method
	static EntitySet<Entity> createEntitySet(
		final DatabaseAdapter parentDatabaseAdapter,
		final EntityType<Entity> entityType
	) {
		return new EntitySet<Entity>(parentDatabaseAdapter, entityType);
	}
	
	//package-visible constructor
	EntitySet(
		final DatabaseAdapter parentDatabaseAdapter,
		final EntityType<E> entityType
	) {
		super(entityType.getName());
		
		Validator
		.suppose(parentDatabaseAdapter)
		.thatIsNamed("parent database adapter")
		.isNotNull();
		
		Validator.suppose(entityType).isOfType(EntityType.class);
		
		this.parentDatabaseAdapter = parentDatabaseAdapter;
		this.entityType = entityType;
		columns = entityType.getColumns();
	}
	
	//method
	public EntitySet<E> addEntities(final Iterable<E> entities) {
		
		entities.forEach(e -> addEntity(e));
		
		return this;
	}
	
	//method
	@SuppressWarnings("unchecked")
	public EntitySet<E> addEntity(final E entity) {
		
		if (!entity.isCreated()) {
			throw new InvalidArgumentException(entity, "is not created");
		}
		
		entity.setParentEntitySet((EntitySet<Entity>)this);
		
		loadedAndCreatedEntities.addAtEndRegardingSingularity(entity);
		getParentDatabaseAdapter().noteMutatedEntity(entity);
		
		return this;
	}
	
	//method
	@SuppressWarnings("unchecked")
	public EntitySet<E> addEntity(final E... entities) {
		return addEntities(new ReadContainer<E>(entities));
	}
	
	//method
	public boolean canReference(final Entity entity) {
		return getColumns().contains(c -> c.canReference(entity));
	}
	
	//method
	public boolean containsEntity(final int id) {
		return
		getParentDatabaseAdapter()
		.getEntitySetAdapter(this)
		.containsEntity(id);
	}
	
	//method
	public E createDefaultEntity() {
		return getEntityType().createDefaultEntity();
	}
	
	//method
	public EntitySet<E> deleteEntity(final E entity) {
				
		entity.setDeleted();
		loadedAndCreatedEntities.removeFirst(entity);
		getParentDatabaseAdapter().noteMutatedEntity(entity);
		
		return this;
	}
	
	//method
	public EntitySet<E> deleteEntity(final long id) {
		return deleteEntity(getRefEntityById(id));
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
	public DatabaseAdapter getParentDatabaseAdapter() {
		return parentDatabaseAdapter;
	}
	
	//method
	public List<E> getRefChangedEntities() {
		return loadedAndCreatedEntities.getRefSelected(e -> e.isChanged());
	}
	
	//method
	@SuppressWarnings("unchecked")
	public IContainer<E> getRefEntities() {
		
		final var newlyLoadedEntities = new List<E>();
		
		for (
			final var e :
			parentDatabaseAdapter
			.getEntitySetAdapter(this)
			.getEntities(getEntityType())
		) {
			if (!loadedAndCreatedEntities.contains(e2 -> e2.getId() == e.getId())) {
			
				if (!e.isPersisted()) {
					throw new InvalidArgumentException(e, "is not persisted");
				}
				
				e.setParentEntitySet((EntitySet<Entity>)this);
				newlyLoadedEntities.addAtEnd(e);
			}
		}
		
		loadedAndCreatedEntities.addAtEnd(newlyLoadedEntities);
		
		return loadedAndCreatedEntities;
	}
	
	//method
	@SuppressWarnings("unchecked")
	public E getRefEntityById(final long id) {
		
		final var loadedEntity = loadedAndCreatedEntities.getRefFirstOrNull(e -> e.getId() == id);
		if (loadedEntity != null) {
			return loadedEntity;
		}
		
		final var entity
		= parentDatabaseAdapter
		.getEntitySetAdapter(this)
		.getEntity(id, getEntityType());
		
		if (!entity.isPersisted()) {
			throw new InvalidArgumentException(entity, "is not persisted");
		}
		
		entity.setParentEntitySet((EntitySet<Entity>)this);
		loadedAndCreatedEntities.addAtEnd(entity);
		
		return entity;
	}
	
	//method
	public boolean hasChanges() {
		return loadedAndCreatedEntities.containsAny();
	}
	
	//method
	public boolean references(final Entity entity) {
		
		if (!canReference(entity)) {
			return false;
		}
		
		return getRefEntities().contains(e -> e.references(entity));
	}
	
	//method
	public boolean reference(final String header, final Entity entity) {
		
		if (!canReference(entity)) {
			return false;
		}
		
		return getRefEntities().contains(e -> e.references(header, entity));
	}
}
