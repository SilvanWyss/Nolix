//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.objectdataapi.dataapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.rawobjectdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;

//class
public final class Database implements IDatabase<DataImplementation> {
	
	//static attribute
	private static final TableMapper tableMapper = new TableMapper();
	
	//static method
	public static Database withDataAndSchemaAdapterAndSchema(
		final IDataAndSchemaAdapter dataAndSchemaAdapter,
		final Schema schema
	) {
		return new Database(dataAndSchemaAdapter, schema);
	}
	
	//attribute
	private final IDataAndSchemaAdapter dataAndSchemaAdapter;
	
	//attribute
	private final Schema schema;
	
	//multi-attribute
	private final LinkedList<ITable<DataImplementation, IEntity<DataImplementation>>> tablesInLocalData =
	new LinkedList<>();
	
	//constructor
	private Database(final IDataAndSchemaAdapter dataAndSchemaAdapter, final Schema schema) {
		
		Validator.assertThat(dataAndSchemaAdapter).thatIsNamed(IDataAndSchemaAdapter.class).isNotNull();
		Validator.assertThat(schema).thatIsNamed(Schema.class).isNotNull();
		
		this.dataAndSchemaAdapter = dataAndSchemaAdapter;
		this.schema = schema;
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
		
		final var table = tablesInLocalData.getRefFirstOrNull(t -> t.hasName(name));
		
		if (table == null) {
			
			addTableWithNameWhenIsNotAdded(name);
			
			return getRefTableByNameWhenIsAdded(name);
		}
		
		return table;
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
	@Override
	public IContainer<ITable<DataImplementation, IEntity<DataImplementation>>> technicalGetRefTablesInLocalData() {
		return tablesInLocalData;
	}
	
	//method
	void internalClose() {
		for (final var t : technicalGetRefTablesInLocalData()) {
			((Table<?>)t).internalClose();
		}
	}
	
	//method
	IDataAndSchemaAdapter internalGetRefDataAndSchemaAdapter() {
		return dataAndSchemaAdapter;
	}
	
	//method
	Schema internalGetSchema() {
		return schema;
	}
	
	//method
	void internalReset() {
		for (final var t : technicalGetRefTablesInLocalData()) {
			((Table<?>)t).internalReset();
		}
	}
	
	//method
	private void addTableWithNameWhenIsNotAdded(final String name) {
		tablesInLocalData.addAtEnd(loadTableWithName(name));
	}
	
	//method
	private ITable<DataImplementation, IEntity<DataImplementation>> getRefTableByNameWhenIsAdded(final String name) {
		return tablesInLocalData.getRefFirst(t -> t.hasName(name));
	}
	
	//method
	private ITable<DataImplementation, IEntity<DataImplementation>> loadTableWithName(final String name) {
		return
		tableMapper.createTableFromTableDTOForDatabase(
			internalGetRefDataAndSchemaAdapter().loadTableByName(name),
			this
		);
	}
}
