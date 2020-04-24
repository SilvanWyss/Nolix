//package declaration
package ch.nolix.system.databaseAdapter;

import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.validator.Validator;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.IEntitySet;

//class
public final class EntitySet<E extends Entity> implements IEntitySet<E> {
	
	//attributes
	private final String name;
	private final DatabaseAdapter parentDatabaseAdapter;
	private final EntityType<E> entityType;
	
	//multi-attributes
	private final LinkedList<Column<?>> columns;
	private final LinkedList<E> loadedAndCreatedEntities = new LinkedList<>();
	
	//static method
	static EntitySet<Entity> createEntitySet(
		final DatabaseAdapter parentDatabaseAdapter,
		final EntityType<Entity> entityType
	) {
		return new EntitySet<>(parentDatabaseAdapter, entityType);
	}
	
	//constructor
	EntitySet(
		final DatabaseAdapter parentDatabaseAdapter,
		final EntityType<E> entityType
	) {
		
		name = entityType.getName();
		
		Validator
		.assertThat(parentDatabaseAdapter)
		.thatIsNamed("parent database adapter")
		.isNotNull();
		
		Validator.assertThat(entityType).isOfType(EntityType.class);
		
		this.parentDatabaseAdapter = parentDatabaseAdapter;
		this.entityType = entityType;
		columns = entityType.getColumns();
	}
	
	//method
	@Override
	public EntitySet<E> addEntities(final Iterable<E> entities) {
		
		entities.forEach(e -> addEntity(e));
		
		return this;
	}
	
	//method
	@SuppressWarnings("unchecked")
	public EntitySet<E> addEntity(final E entity) {
		
		entity.supposeIsNew();
		
		entity.setParentEntitySet((EntitySet<Entity>)this);
		
		loadedAndCreatedEntities.addAtEndRegardingSingularity(entity);
		getParentDatabaseAdapter().noteMutatedEntity(entity);
		
		return this;
	}
	
	//method
	@SuppressWarnings("unchecked")
	@Override
	public EntitySet<E> addEntity(final E... entities) {
		return addEntities(new ReadContainer<E>(entities));
	}
	
	//method
	public boolean canReference(final Entity entity) {
		return getColumns().contains(c -> c.canReference(entity));
	}
	
	//method
	public <E2 extends Entity> boolean canReferenceBackEntityOfType(final Class<E2> type) {
		return columns.contains(c -> c.canReferenceBackEntityOfType(type));
	}
	
	//method
	public <E2 extends Entity> boolean canReferenceEntityOfType(final Class<E2> type) {
		return columns.contains(c -> c.canReferenceEntityOfType(type));
	}
	
	//method
	@Override
	public IEntitySet<E> clear() {
		
		getRefEntities().toList().forEach(this::deleteEntity);
		
		return this;
	}
	
	//method
	@Override
	public boolean containsAny() {
		return getRefEntities().containsAny();
	}
	
	//method
	public boolean containsEntity(final int id) {
		return
		getParentDatabaseAdapter()
		.getEntitySetAdapter(this)
		.containsEntity(id);
	}
	
	//method
	public E createEmptyEntity() {
		return getEntityType().createEmptyEntity();
	}
	
	//method
	@Override
	public EntitySet<E> deleteEntity(final E entity) {
		
		loadedAndCreatedEntities.removeFirst(entity);
		getParentDatabaseAdapter().noteMutatedEntity(entity);
		
		return this;
	}
	
	//method
	public EntitySet<E> deleteEntity(final long id) {
		
		getRefEntityById(id).setDeleted();
		
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
	@Override
	public String getName() {
		return name;
	}
	
	//method
	public DatabaseAdapter getParentDatabaseAdapter() {
		return parentDatabaseAdapter;
	}
	
	//method
	public LinkedList<E> getRefEditedEntities() {
		return loadedAndCreatedEntities.getRefSelected(e -> e.isEdited());
	}
	
	//method
	@SuppressWarnings("unchecked")
	public IContainer<E> getRefEntities() {
		
		final var newlyLoadedEntities = new LinkedList<E>();
		
		for (
			final var e :
			parentDatabaseAdapter
			.getEntitySetAdapter(this)
			.getEntities()
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
		.getEntity(id);
		
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
	public boolean isEmpty() {
		return getRefEntities().isEmpty();
	}
	
	//method
	@Override
	public void noteMutatedEntity(E entity) {
		getParentDatabaseAdapter().noteMutatedEntity(entity);
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
