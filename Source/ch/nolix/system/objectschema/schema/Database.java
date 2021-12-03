//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.objectschema.parametrizedpropertytype.SchemaImplementation;
import ch.nolix.system.objectschema.schemahelper.DatabaseHelper;
import ch.nolix.techapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.techapi.objectschemaapi.schemaapi.IDatabaseEngine;
import ch.nolix.techapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.techapi.objectschemaapi.schemahelperapi.IDatabaseHelper;
import ch.nolix.techapi.rawobjectschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class Database extends DatabaseObject implements IDatabase<SchemaImplementation> {
	
	//static attribute
	private final IDatabaseHelper databaseHelper = new DatabaseHelper();
	
	//static attributes
	private static final DatabaseMutationValidator mutationValidator = new DatabaseMutationValidator();
	private static final DatabaseMutationExecutor mutationExecutor = new DatabaseMutationExecutor();
	
	//attributes
	private final String name;
	private boolean loadedTablesFromDatabase;
	
	//optional attribute
	private RawSchemaAdapter rawSchemaAdapter;
	
	//multi-attribute
	private LinkedList<ITable<SchemaImplementation>> tables = new LinkedList<>();
	
	//constructor
	public Database(final String name) {
		
		mutationValidator.assertCanSetNameToDatabase(this, name);
		
		this.name = name;
	}
	
	//method
	@Override
	public Database addTable(final ITable<SchemaImplementation> table) {
		
		mutationValidator.assertCanAddTableToDatabase(this, (Table)table);
		mutationExecutor.addTableToDatabase(this, (Table)table);
		
		return this;
	}
	
	//method
	@Override
	public boolean belongsToEngine() {
		//TODO: Implement.
		return false;
	}
	
	//method
	@Override
	public Database createTableWithName(final String name) {
		return addTable(new Table(name));
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
	
	//method
	@Override
	public IDatabaseEngine<SchemaImplementation> getParentEngine() {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public IContainer<ITable<SchemaImplementation>> getRefTables() {
		
		loadTablesFromDatabaseIfNeeded();
		
		return tables;
	}
	
	//method
	@Override
	public boolean isLinkedWithRealDatabase() {
		return (rawSchemaAdapter != null);
	}
	
	//method
	@Override
	public void setRealSchemaAdapter(final ISchemaAdapter schemaAdapter) {
		setRealSchemaAdapter(new RawSchemaAdapter(schemaAdapter));
	}
	
	//method
	@Override
	protected void noteCloseDatabaseObject() {
		
		//Does not call getRefTables method to avoid that the tables need to be loaded from the database.
		tables.forEach(ITable::close);
	}
	
	//method
	void addTableAttribute(final ITable<SchemaImplementation> table) {
		tables.addAtEnd(table);
	}
	
	//method
	RawSchemaAdapter getRefRealSchemaAdapter() {
		
		databaseHelper.assertIsLinkedWithRealDatabase(this);
		
		return rawSchemaAdapter;
	}
	
	//method
	void removeTableAttribute(final Table table) {
		tables.removeFirst(table);
	}
	
	//method
	private boolean hasLoadedTablesFromDatabase() {
		return loadedTablesFromDatabase;
	}
	
	//method
	private void loadTablesFromDatabase() {
		
		tables = getRefRealSchemaAdapter().getRefRawSchemaReader().loadFlatTables().to(Table::fromFlatDTO);
		for (final var t : tables) {
			final var table = (Table)t;
			table.setLoaded();
			table.setParentDatabase(this);
		}
		
		loadedTablesFromDatabase = true;		
	}
	
	//method
	private void loadTablesFromDatabaseIfNeeded() {
		if (needsToLoadTablesFromDatabase()) {
			loadTablesFromDatabase();
		}
	}
	
	//method
	private boolean needsToLoadTablesFromDatabase() {
		return (databaseHelper.isLoaded(this) && !hasLoadedTablesFromDatabase());
	}
	
	//method
	private void setRealSchemaAdapter(final RawSchemaAdapter rawSchemaAdapter) {
		
		Validator.assertThat(rawSchemaAdapter).thatIsNamed(RawSchemaAdapter.class).isNotNull();
		databaseHelper.assertIsNotLinkedWithRealDatabase(this);
		
		setLoaded();
		this.rawSchemaAdapter = rawSchemaAdapter;
	}
}
