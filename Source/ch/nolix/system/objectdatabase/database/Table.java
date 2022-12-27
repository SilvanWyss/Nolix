//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programstructure.caching.CachingProperty;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.objectdatabase.databasehelper.TableHelper;
import ch.nolix.system.objectdatabase.databasevalidator.TableValidator;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IColumn;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IDatabase;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.ITableHelper;
import ch.nolix.systemapi.rawdatabaseapi.databaseandschemaadapterapi.IDataAndSchemaAdapter;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedRecordDTO;

//class
public final class Table<E extends IEntity<DataImplementation>> implements ITable<DataImplementation, E> {
	
	//static attribute
	private static final ITableHelper tableHelper = new TableHelper();
	
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
	private final LinkedList<IColumn<DataImplementation>> columns = new LinkedList<>();
	
	//multi-attribute
	private final LinkedList<E> entitiesInLocalData = new LinkedList<>();
	
	//constructor
	private Table(
		final Database parentDatabase,
		final String name,
		final String id,
		final Class<E> entityClass
	) {
		
		GlobalValidator.assertThat(parentDatabase).thatIsNamed("parent Database").isNotNull();
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		GlobalValidator.assertThat(id).thatIsNamed(LowerCaseCatalogue.ID).isNotBlank();
		GlobalValidator.assertThat(entityClass).thatIsNamed("entity class").isNotNull();
		
		this.parentDatabase = parentDatabase;
		this.name = name;
		this.id = id;
		this.entityClass = entityClass;
	}
	
	//method
	@Override
	public Class<E> getEntityType() {
		return entityClass;
	}
	
	//method
	@Override
	public IContainer<IColumn<DataImplementation>> getRefColumns() {
		return columns;
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
	public IContainer<E> getRefEntities() {
		
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
	public IDatabase<DataImplementation> getRefParentDatabase() {
		return parentDatabase;
	}
	
	//method
	@Override
	public DatabaseObjectState getState() {
		return parentDatabase.getState();
	}
	
	//method
	@Override
	public ITable<DataImplementation, E> insertEntity(final E entity) {
		
		@SuppressWarnings("unchecked")
		final var table = (ITable<DataImplementation, IEntity<DataImplementation>>)this;
		
		//The inserted Entity must know its Table to be inserted.
		((BaseEntity)entity).internalSetParentTable(table);
		
		TableValidator.INSTANCE.assertCanInsertGivenEntity(this, entity);
		
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
	void internalAddColumn(final IColumn<DataImplementation> column) {
		columns.addAtEnd(column);
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
		this.columns.clear();
		this.columns.addAtEnd(columns);
	}
	
	//method
	private void addEntityWithIdWhenIsNotAdded(final String id) {
		entitiesInLocalData.addAtEnd(loadEntityById(id));
	}
	
	//method
	@SuppressWarnings("unchecked")
	private E createEntityFrom(ILoadedRecordDTO pRecord) {
		return (E)entityMapper.createEntityFromRecordForGivenTable(pRecord, (Table<BaseEntity>)this);
	}
	
	//method
	private E getRefEntityByIdWhenIsInLocalData(final String id) {
		return technicalGetRefEntitiesInLocalData().getRefFirst(e -> e.hasId(id));
	}
	
	//method
	private void insertEntityFromGivenRecordInLocalDataIfNotInserted(ILoadedRecordDTO pRecord) {
		if (!tableHelper.containsEntityWithGivenIdInLocalData(this, pRecord.getId())) {
			entitiesInLocalData.addAtEnd(createEntityFrom(pRecord));
		}
	}
	
	//method
	private void insertWhenCanBeInserted(final E entity) {
		
		entitiesInLocalData.addAtEnd(entity);
		
		final var baseEntity = (BaseEntity)entity;
		baseEntity.internalUpdateProbableBackReferencesWhenIsNew();
		baseEntity.internalNoteInsert();
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
