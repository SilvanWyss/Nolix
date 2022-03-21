//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.element.time.base.Time;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.objectdataapi.dataapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ISchema;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.rawdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;

//class
public final class Database implements IDatabase<DataImplementation> {
	
	//static attribute
	private static final TableMapper tableMapper = new TableMapper();
	
	//static method
	public static Database withDataAndSchemaAdapterAndSchema(
		final IDataAndSchemaAdapter dataAndSchemaAdapter,
		final ISchema<DataImplementation> schema
	) {
		return new Database(dataAndSchemaAdapter, schema);
	}
	
	//attribute
	private final Time schemaTimestamp;
	
	//attribute
	private final IDataAndSchemaAdapter dataAndSchemaAdapter;
	
	//attribute
	private final ISchema<DataImplementation> schema;
	
	//multi-attribute
	private final LinkedList<ITable<DataImplementation, IEntity<DataImplementation>>> tables;
	
	//constructor
	private Database(final IDataAndSchemaAdapter dataAndSchemaAdapter, final ISchema<DataImplementation> schema) {
		
		Validator.assertThat(dataAndSchemaAdapter).thatIsNamed(IDataAndSchemaAdapter.class).isNotNull();
		Validator.assertThat(schema).thatIsNamed(ISchema.class).isNotNull();
		
		schemaTimestamp = dataAndSchemaAdapter.getSchemaTimestamp();
		this.dataAndSchemaAdapter = dataAndSchemaAdapter;
		this.schema = schema;
		tables = loadTables();
	}
	
	//method
	@Override
	@SuppressWarnings("unchecked")
	public <E extends IEntity<DataImplementation>> ITable<DataImplementation, E> getRefTableByEntityClass(
		final Class<E> entityClass
	) {
		return (ITable<DataImplementation, E>)getRefTableByName(entityClass.getSimpleName());		
	}
	
	//method	
	@Override
	public ITable<DataImplementation, IEntity<DataImplementation>> getRefTableByName(final String name) {
		return tables.getRefFirst(t -> t.hasName(name));
	}
	
	//method
	@Override
	public IContainer<ITable<DataImplementation, IEntity<DataImplementation>>> getRefTables() {
		return tables;
	}
	
	//method
	@Override
	public Time getSchemaTimestamp() {
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
	public <E extends IEntity<DataImplementation>> IDatabase<DataImplementation> insert(final E entity) {
		
		getRefTableByEntityClass(entity.getClass()).insert(entity);
		
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
		for (final var t : getRefTables()) {
			((Table<?>)t).internalClose();
		}
	}
	
	//method
	IDataAndSchemaAdapter internalGetRefDataAndSchemaAdapter() {
		return dataAndSchemaAdapter;
	}
	
	//method
	ISchema<DataImplementation> internalGetSchema() {
		return schema;
	}
	
	//method
	void internalReset() {
		for (final var t : getRefTables()) {
			((Table<?>)t).internalReset();
		}
	}
	
	private LinkedList<ITable<DataImplementation, IEntity<DataImplementation>>> loadTables() {
		return
		internalGetRefDataAndSchemaAdapter()
		.loadTables()
		.to(t -> tableMapper.createTableFromTableDTOForDatabase(t, this));
	}
}
