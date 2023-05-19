//package declaration
package ch.nolix.system.objectdatabase.database;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IDatabase;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.objectdatabaseapi.schemaapi.ISchema;
import ch.nolix.systemapi.rawdatabaseapi.databaseandschemaadapterapi.IDataAndSchemaAdapter;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
public final class Database implements IDatabase {
	
	//static attribute
	private static final DatabaseTableLoader databaseTableLoader = new DatabaseTableLoader();
	
	//static method
	public static Database withDataAndSchemaAdapterAndSchema(
		final IDataAndSchemaAdapter dataAndSchemaAdapter,
		final ISchema schema
	) {
		return new Database(dataAndSchemaAdapter, schema);
	}
	
	//attribute
	private final ITime schemaTimestamp;
	
	//attribute
	private final IDataAndSchemaAdapter dataAndSchemaAdapter;
	
	//attribute
	private final ISchema schema;
	
	//multi-attribute
	private final LinkedList<? extends ITable<IEntity>> tables;
	
	//constructor
	private Database(final IDataAndSchemaAdapter dataAndSchemaAdapter, final ISchema schema) {
		
		GlobalValidator.assertThat(dataAndSchemaAdapter).thatIsNamed(IDataAndSchemaAdapter.class).isNotNull();
		GlobalValidator.assertThat(schema).thatIsNamed(ISchema.class).isNotNull();
		
		schemaTimestamp = dataAndSchemaAdapter.getSchemaTimestamp();
		this.dataAndSchemaAdapter = dataAndSchemaAdapter;
		this.schema = schema;
		tables = loadTables();
	}
	
	//method
	@Override
	public <E extends IEntity> IContainer<E> getOriEntitiesByType(final Class<E> type) {
		return getOriTableByEntityType(type).getOriEntities();
	}
	
	//method
	@Override
	@SuppressWarnings("unchecked")
	public <E extends IEntity> ITable<E> getOriTableByEntityType(
		final Class<E> entityClass
	) {
		return (ITable<E>)getOriTableByName(entityClass.getSimpleName());		
	}
	
	//method	
	@Override
	public ITable<IEntity> getOriTableByName(final String name) {
		return tables.getOriFirst(t -> t.hasName(name));
	}
	
	//method
	@Override
	public IContainer<? extends ITable<IEntity>> getOriTables() {
		return tables;
	}
	
	//method
	@Override
	public ITime getSchemaTimestamp() {
		return schemaTimestamp;
	}
	
	//method
	@Override
	public DatabaseObjectState getState() {
		
		if (isClosed()) {
			return DatabaseObjectState.CLOSED;
		}
		
		if (internalGetRefDataAndSchemaAdapter().hasChanges()) {
			return DatabaseObjectState.EDITED;
		}
		
		return DatabaseObjectState.LOADED;
	}
	
	//method
	@Override
	@SuppressWarnings("unchecked")
	public <E extends IEntity> IDatabase insertEntity(final E entity) {
		
		getOriTableByEntityType((Class<E>)entity.getClass()).insertEntity(entity);
		
		return this;
	}
	
	//method
	@Override
	public boolean isClosed() {
		return internalGetRefDataAndSchemaAdapter().isClosed();
	}
	
	//method
	@Override
	public boolean isDeleted() {
		return false;
	}
	
	//method
	@Override
	public boolean isLinkedWithRealDatabase() {
		return true;
	}
	
	//method
	void internalClose() {
		for (final var t : getOriTables()) {
			((Table<?>)t).internalClose();
		}
	}
	
	//method
	IDataAndSchemaAdapter internalGetRefDataAndSchemaAdapter() {
		return dataAndSchemaAdapter;
	}
	
	//method
	ISchema internalGetSchema() {
		return schema;
	}
	
	//method
	void internalReset() {
		for (final var t : getOriTables()) {
			((Table<?>)t).internalReset();
		}
	}
	
	//method
	private LinkedList<Table<IEntity>> loadTables() {
		return databaseTableLoader.loadTablesForDatabase(this);
	}
}
