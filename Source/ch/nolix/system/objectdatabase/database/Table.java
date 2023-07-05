//package declaration
package ch.nolix.system.objectdatabase.database;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programstructure.caching.CachingProperty;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdatabase.databasehelper.TableHelper;
import ch.nolix.system.objectdatabase.databasevalidator.TableValidator;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IColumn;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IDatabase;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.ITableHelper;
import ch.nolix.systemapi.rawdatabaseapi.databaseandschemaadapterapi.IDataAndSchemaAdapter;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedEntityDto;

//class
public final class Table<E extends IEntity> implements ITable<E> {
	
	//constant
	private static final TableValidator TABLE_VALIDATOR = new TableValidator();
	
	//static attribute
	private static final ITableHelper tableHelper = new TableHelper();
	
	//static attribute
	private static final EntityMapper entityMapper = new EntityMapper();
	
	//static method
	static <E2 extends IEntity> Table<E2> withParentDatabaseAndNameAndIdAndEntityClassAndColumns(
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
	private final CachingProperty<IContainer<IColumn>> columnsThatReferenceCurrentTable =
	new CachingProperty<>(() -> tableHelper.getColumsThatReferenceGivenTable(this));
	
	//attribute
	private boolean loadedAllEntitiesInLocalData;
	
	//multi-attribute
	private final LinkedList<IColumn> columns = new LinkedList<>();
	
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
	public boolean containsEntityWithId(final String id) {
		return internalGetRefDataAndSchemaAdapter().tableContainsEntityWithGivenId(getName(), id);
	}
	
	//method
	@Override
	public int getEntityCount() {
		return getOriEntities().getElementCount();
	}
	
	//method
	@Override
	public Class<E> getEntityType() {
		return entityClass;
	}
	
	//method
	@Override
	public IContainer<IColumn> getOriColumns() {
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
	public IContainer<E> getOriEntities() {
		
		loadAllEntitiesInLocalDataIfNotLoaded();
		
		return entitiesInLocalData;
	}
	
	//method
	@Override
	public E getOriEntityById(final String id) {
		
		final var entity = technicalGetRefEntitiesInLocalData().getOriFirstOrNull(e -> e.hasId(id));
		
		if (entity == null) {
			
			addEntityWithIdWhenIsNotAdded(id);
			
			return getOriEntityByIdWhenIsInLocalData(id);
		}
		
		return entity;
	}
	
	//method
	@Override
	public E getOriEntityByIdOrNull(final String id) {
		
		final var entity = technicalGetRefEntitiesInLocalData().getOriFirstOrNull(e -> e.hasId(id));
		
		if (entity == null) {
			
			if (internalGetRefDataAndSchemaAdapter().tableContainsEntityWithGivenId(getName(), id)) {
				
				addEntityWithIdWhenIsNotAdded(id);
				
				return getOriEntityByIdWhenIsInLocalData(id);
			}
			
			return null;
		}
		
		return entity;
	}
	
	//method
	@Override
	public IDatabase getOriParentDatabase() {
		return parentDatabase;
	}
	
	//method
	@Override
	public DatabaseObjectState getState() {
		return parentDatabase.getState();
	}
	
	//method
	@Override
	public ITable<E> insertEntity(final E entity) {
		
		@SuppressWarnings("unchecked")
		final var table = (ITable<IEntity>)this;
		
		//The inserted Entity must know its Table to be inserted.
		((BaseEntity)entity).internalSetParentTable(table);
		
		TABLE_VALIDATOR.assertCanInsertGivenEntity(this, entity);
		
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
	void internalAddColumn(final IColumn column) {
		columns.addAtEnd(column);
	}
		
	//method
	@SuppressWarnings("unchecked")
	void internalClose() {
		((IContainer<BaseEntity>)technicalGetRefEntitiesInLocalData()).forEach(BaseEntity::internalClose);
	}
	
	//method
	IContainer<IColumn> internalGetColumnsThatReferencesCurrentTable() {
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
	void internalSetColumns(final IContainer<IColumn> columns) {
		this.columns.clear();
		this.columns.addAtEnd(columns);
	}
	
	//method
	private void addEntityWithIdWhenIsNotAdded(final String id) {
		entitiesInLocalData.addAtEnd(loadEntityById(id));
	}
	
	//method
	@SuppressWarnings("unchecked")
	private E createLoadedEntityFromDto(ILoadedEntityDto loadedEntityDto) {
		return (E)entityMapper.createLoadedEntityFromDto(loadedEntityDto, (Table<BaseEntity>)this);
	}
	
	//method
	private E getOriEntityByIdWhenIsInLocalData(final String id) {
		return technicalGetRefEntitiesInLocalData().getOriFirst(e -> e.hasId(id));
	}
	
	//method
	private void insertEntityFromGivenLoadedEntityDtoInLocalDataIfNotInserted(ILoadedEntityDto loadedEntity) {
		if (!tableHelper.containsEntityWithGivenIdInLocalData(this, loadedEntity.getId())) {
			entitiesInLocalData.addAtEnd(createLoadedEntityFromDto(loadedEntity));
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
		
		for (final var r : internalGetRefDataAndSchemaAdapter().loadEntitiesOfTable(getName())) {
			insertEntityFromGivenLoadedEntityDtoInLocalDataIfNotInserted(r);
		}
		
		loadedAllEntitiesInLocalData = true;
	}
	
	//method
	private boolean loadedAllEntitiesInLocalData() {
		return loadedAllEntitiesInLocalData;
	}
	
	//method
	private E loadEntityById(final String id) {
		return createLoadedEntityFromDto(loadEntityDtoById(id));
	}
	
	//method
	private ILoadedEntityDto loadEntityDtoById(final String id) {
		return internalGetRefDataAndSchemaAdapter().loadEntity(getName(), id);
	}
}
