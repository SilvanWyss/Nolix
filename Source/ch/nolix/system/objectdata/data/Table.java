//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.caching.CachingProperty;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.constant.PluralLowerCaseCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.objectdata.datahelper.EntityHelper;
import ch.nolix.system.objectdata.datahelper.TableHelper;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;
import ch.nolix.systemapi.objectdataapi.dataapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.datahelperapi.IEntityHelper;
import ch.nolix.systemapi.objectdataapi.datahelperapi.ITableHelper;
import ch.nolix.systemapi.rawdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedRecordDTO;

//class
public final class Table<E extends IEntity<DataImplementation>> implements ITable<DataImplementation, E> {
	
	//static attribute
	private static final ITableHelper tableHelper = new TableHelper();
	
	//static attribute
	private static final IEntityHelper entityHelper = new EntityHelper();
	
	//static attribute
	private static final EntityMapper entityMapper = new EntityMapper();
	
	//static method
	static <E2 extends IEntity<DataImplementation>> Table<E2> withParentDatabaseAndNameAndIdAndEntityClassAndColumns(
		final Database parentDatabase,
		final String name,
		final String id,
		final Class<E2> entityClass
	) {
		return new Table<>(parentDatabase, name, id, entityClass);
	}	
	
	//attribute
	private final Database parentDatabase;
	
	//attribute
	private final String name;
	
	//attribute
	private final String id;
	
	//attribute
	private final Class<E> entityClass;
	
	//attribute
	private final CachingProperty<IContainer<IColumn<DataImplementation>>> columnsThatReferenceCurrentTable =
	new CachingProperty<>(() -> tableHelper.getColumsThatReferenceGivenTable(this));
	
	//attribute
	private boolean loadedAllEntitiesInLocalData;
	
	//multi-attribute
	private IContainer<IColumn<DataImplementation>> columns;
	
	//multi-attribute
	private final LinkedList<E> entitiesInLocalData = new LinkedList<>();
	
	//constructor
	private Table(
		final Database parentDatabase,
		final String name,
		final String id,
		final Class<E> entityClass
	) {
		
		Validator.assertThat(parentDatabase).thatIsNamed("parent Database").isNotNull();
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		Validator.assertThat(id).thatIsNamed(LowerCaseCatalogue.ID).isNotBlank();
		Validator.assertThat(entityClass).thatIsNamed("entity class").isNotNull();
		
		this.parentDatabase = parentDatabase;
		this.name = name;
		this.id = id;
		this.entityClass = entityClass;
	}
	
	//method
	@Override
	public IContainer<IColumn<DataImplementation>> getColumns() {
		return columns;
	}
	
	//method
	@Override
	public Class<E> getEntityClass() {
		return entityClass;
	}
	
	//method
	@Override
	public String getId() {
		return id;
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
		
		final var entity = technicalGetRefEntitiesInLocalData().getRefFirstOrNull(e -> e.hasId(id));
		
		if (entity == null) {
			
			addEntityWithIdWhenIsNotAdded(id);
			
			return getRefEntityByIdWhenIsInLocalData(id);
		}
		
		return entity;
	}
	
	//method
	@Override
	public DatabaseObjectState getState() {
		return parentDatabase.getState();
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
	public boolean isClosed() {
		return parentDatabase.isClosed();
	}
	
	//method
	@Override
	public boolean isDeleted() {
		return parentDatabase.isDeleted();
	}
	
	//method
	@Override
	public boolean isLinkedWithRealDatabase() {
		return parentDatabase.isLinkedWithRealDatabase();
	}
	
	//method
	@Override
	public IContainer<E> technicalGetRefEntitiesInLocalData() {
		return entitiesInLocalData;
	}
	
	//method
	@SuppressWarnings("unchecked")
	void internalClose() {
		((IContainer<BaseEntity>)technicalGetRefEntitiesInLocalData()).forEach(BaseEntity::internalClose);
	}
	
	//method
	IContainer<IColumn<DataImplementation>> internalGetColumnsThatReferencesCurrentTable() {
		return columnsThatReferenceCurrentTable.getValue();
	}
	
	//method
	IDataAndSchemaAdapter internalGetRefDataAndSchemaAdapter() {
		return parentDatabase.internalGetRefDataAndSchemaAdapter();
	}
	
	//method
	@SuppressWarnings("unchecked")
	void internalReset() {
		
		((IContainer<BaseEntity>)technicalGetRefEntitiesInLocalData()).forEach(BaseEntity::internalClose);
		
		loadedAllEntitiesInLocalData = false;
		entitiesInLocalData.clear();
	}
	
	//method
	void internalSetColumns(final IContainer<IColumn<DataImplementation>> columns) {
		
		Validator.assertThat(columns).thatIsNamed(PluralLowerCaseCatalogue.COLUMNS).isNotNull();
		
		this.columns = columns;
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
	private E getRefEntityByIdWhenIsInLocalData(final String id) {
		return technicalGetRefEntitiesInLocalData().getRefFirst(e -> e.hasId(id));
	}
	
	//method
	private void insertEntityFromGivenRecordInLocalDataIfNotInserted(ILoadedRecordDTO record) {
		if (!tableHelper.containsEntityWithGivenIdInLocalData(this, record.getId())) {
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
