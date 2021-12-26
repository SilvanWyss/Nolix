//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.techapi.objectdataapi.dataapi.IDatabase;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.ITable;
import ch.nolix.techapi.rawobjectdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;

//class
public final class Database extends ImmutableDatabaseObject implements IDatabase<DataImplementation> {
	
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
	private final LinkedList<Table<IEntity<DataImplementation>>> tables = new LinkedList<>();
	
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
		
		final var table = tables.getRefFirstOrNull(t -> t.hasName(name));
		
		if (table == null) {
			
			addTableWithNameWhenIsNotAdded(name);
			
			return getRefTableByNameWhenIsAdded(name);
		}
		
		return table;
	}
	
	//method
	@Override
	@SuppressWarnings("unchecked")
	public <E extends IEntity<DataImplementation>> IDatabase<DataImplementation> insert(final E entity) {
		
		getRefTableByEntityClass(entity.getClass()).insert(entity);
		
		return this;
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
	private void addTableWithNameWhenIsNotAdded(final String name) {
		tables.addAtEnd(loadTableWithName(name));
	}
	
	//method
	private Table<IEntity<DataImplementation>> getRefTableByNameWhenIsAdded(final String name) {
		return tables.getRefFirst(t -> t.hasName(name));
	}
	
	//method
	private Table<IEntity<DataImplementation>> loadTableWithName(final String name) {
		return
		tableMapper.createTableFromTableDTOForDatabase(
			internalGetRefDataAndSchemaAdapter().loadTable(name),
			this
		);
	}
}
