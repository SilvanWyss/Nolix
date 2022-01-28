//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.objectdata.datahelper.EntityHelper;
import ch.nolix.system.objectdata.datahelper.TableHelper;
import ch.nolix.systemapi.objectdataapi.dataapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.datahelperapi.IEntityHelper;
import ch.nolix.systemapi.objectdataapi.datahelperapi.ITableHelper;
import ch.nolix.systemapi.rawobjectdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.ILoadedRecordDTO;

//class
public final class Table<E extends IEntity<DataImplementation>> extends ImmutableDatabaseObject
implements ITable<DataImplementation, E> {
	
	//static attribute
	private static final ITableHelper tableHelper = new TableHelper();
	
	//static attribute
	private static final IEntityHelper entityHelper = new EntityHelper();
	
	//static attribute
	private static final EntityMapper entityMapper = new EntityMapper();
	
	//attribute
	private final String name;
	
	//attribute
	private final Class<E> entityClass;
	
	//attribute
	private final Database parentDatabase;
	
	//attribute
	private boolean loadedAllEntitiesInLocalData;
	
	//multi-attribute
	private final LinkedList<E> entitiesInLocalData = new LinkedList<>();
	
	//constructor
	Table(final String name, final Class<E> entityClass, final Database parentDatabase) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		Validator.assertThat(entityClass).thatIsNamed("entity class").isNotNull();
		Validator.assertThat(parentDatabase).thatIsNamed("parent Database").isNotNull();
		
		this.name = name;
		this.entityClass = entityClass;
		this.parentDatabase = parentDatabase;
	}
	
	//method
	@Override
	public boolean containsEntityWithGivenIdInLocalData(final String id) {
		return entitiesInLocalData.containsAny(e -> e.hasId(id));
	}
	
	//method
	@Override
	public Class<E> getEntityClass() {
		return entityClass;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
	
	//method
	@Override
	public IDatabase<DataImplementation> getParentDatabase() {
		return parentDatabase;
	}
	
	//method
	@Override
	public IContainer<E> getRefAllEntities() {
		
		loadAllEntitiesInLocalDataIfNotLoaded();
		
		return entitiesInLocalData;
	}
	
	//method
	@Override
	public E getRefEntityById(final String id) {
		
		final var entity = getRefEntitiesInLocalData().getRefFirstOrNull(e -> e.hasId(id));
		
		if (entity == null) {
			
			addEntityWithIdWhenIsNotAdded(id);
			
			return getRefEntityByIdWhenIsInLocalData(id);
		}
		
		return entity;
	}
	
	//method
	@Override
	public ITable<DataImplementation, E> insert(final E entity) {
		
		tableHelper.assertCanInsertGivenEntity(this, entity);
		
		insertWhenCanBeInserted(entity);
		
		return this;
	}
	
	//method
	@Override
	public IContainer<E> technicalGetRefEntitiesInLocalData() {
		return entitiesInLocalData;
	}
	
	//method
	IDataAndSchemaAdapter internalGetRefDataAndSchemaAdapter() {
		return parentDatabase.internalGetRefDataAndSchemaAdapter();
	}
	
	//method
	private void addEntityWithIdWhenIsNotAdded(final String id) {
		entitiesInLocalData.addAtEnd(loadEntityById(id));
	}
	
	//method
	@SuppressWarnings("unchecked")
	private E createEntityFrom(ILoadedRecordDTO record) {
		return (E)entityMapper.createEntityFromRecordForGivenTable(record, (Table<BaseEntity>)this);
	}
	
	//method
	private IContainer<E> getRefEntitiesInLocalData() {
		return entitiesInLocalData;
	}
	
	//method
	private E getRefEntityByIdWhenIsInLocalData(final String id) {
		return getRefEntitiesInLocalData().getRefFirst(e -> e.hasId(id));
	}
	
	//method
	private void insertEntityFromGivenRecordInLocalDataIfNotInserted(ILoadedRecordDTO record) {
		if (!containsEntityWithGivenIdInLocalData(record.getId())) {
			entitiesInLocalData.addAtEnd(createEntityFrom(record));
		}
	}
	
	//method
	@SuppressWarnings("unchecked")
	private void insertWhenCanBeInserted(final E entity) {
		
		((BaseEntity)entity).internalSetParentTable((ITable<DataImplementation, IEntity<DataImplementation>>)this);
		
		entitiesInLocalData.addAtEnd(entity);
		
		internalGetRefDataAndSchemaAdapter().insertRecordIntoTable(getName(), entityHelper.createRecordFor(entity));
	}
	
	//method
	private void loadAllEntitiesInLocalDataIfNotLoaded() {
		if (!loadedAllEntitiesInLocalData()) {
			loadAllEntitiesInLocalDataWhenNotLoadedAll();
		}
	}
	
	//method
	private void loadAllEntitiesInLocalDataWhenNotLoadedAll() {
		
		for (final var r : internalGetRefDataAndSchemaAdapter().loadAllRecordsFromTable(getName())) {
			insertEntityFromGivenRecordInLocalDataIfNotInserted(r);
		}
		
		loadedAllEntitiesInLocalData = true;
	}
	
	//method
	private boolean loadedAllEntitiesInLocalData() {
		return loadedAllEntitiesInLocalData;
	}
	
	//method
	private E loadEntityById(final String id) {
		return createEntityFrom(loadRecordOfEntityById(id));
	}
	
	//method
	private ILoadedRecordDTO loadRecordOfEntityById(final String id) {
		return internalGetRefDataAndSchemaAdapter().loadRecordFromTableById(getName(), id);
	}
}
