//package declaration
package ch.nolix.system.objectschema.schema;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.IContainer;
import ch.nolix.system.objectschema.parametrizedpropertytype.SchemaImplementation;
import ch.nolix.system.objectschema.schemahelper.DatabaseHelper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IDatabaseHelper;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class Database extends SchemaObject implements IDatabase<SchemaImplementation> {
	
	//static attribute
	private final IDatabaseHelper databaseHelper = new DatabaseHelper();
	
	//static attributes
	private static final DatabaseMutationExecutor mutationExecutor = new DatabaseMutationExecutor();
	
	//attribute
	private final String name;
	
	//attribute
	private boolean loadedTablesFromDatabase;
	
	//optional attribute
	private RawSchemaAdapter rawSchemaAdapter;
	
	//multi-attribute
	private LinkedList<ITable<SchemaImplementation>> tables = new LinkedList<>();
	
	//constructor
	public Database(final String name) {
		
		databaseHelper.assertCanSetGivenNameToDatabase(name);
		
		this.name = name;
	}
	
	//method
	@Override
	public Database addTable(final ITable<SchemaImplementation> table) {
		
		databaseHelper.assertCanAddGivenTable(this, table);
		mutationExecutor.addTableToDatabase(this, (Table)table);
		
		return this;
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
	public IContainer<ITable<SchemaImplementation>> getRefTables() {
		
		loadTablesFromDatabaseIfNeeded();
		
		return tables;
	}
	
	//method
	@Override
	public int getTableCount() {
		
		if (!isLinkedWithRealDatabase() || hasLoadedTablesFromDatabase()) {
			return tables.getElementCount();
		}
		
		return rawSchemaAdapter.getTableCount();
	}
	
	//method
	@Override
	public boolean isLinkedWithRealDatabase() {
		return (rawSchemaAdapter != null);
	}
	
	//method
	@Override
	public void setRawSchemaAdapter(final ISchemaAdapter rawSchemaAdapter) {
		setRawSchemaAdapter(new RawSchemaAdapter(rawSchemaAdapter));
	}
	
	//method
	@Override
	protected void noteClose() {
		
		//Does not call getRefTables method to avoid that the tables need to be loaded from the database.
		for (final var t : tables) {
			((Table)t).internalClose();
		}
	}
	
	//method
	void addTableAttribute(final ITable<SchemaImplementation> table) {
		tables.addAtEnd(table);
	}
	
	//method
	RawSchemaAdapter internalGetRefRawSchemaAdapter() {
		
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
		
		tables = LinkedList.fromIterable(internalGetRefRawSchemaAdapter().loadFlatTables().to(Table::fromFlatDTO));
		for (final var t : tables) {
			final var table = (Table)t;
			table.internalSetLoaded();
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
	private void setRawSchemaAdapter(final RawSchemaAdapter rawSchemaAdapter) {
		
		GlobalValidator.assertThat(rawSchemaAdapter).thatIsNamed(RawSchemaAdapter.class).isNotNull();
		databaseHelper.assertIsNotLinkedWithRealDatabase(this);
		
		internalSetLoaded();
		this.rawSchemaAdapter = rawSchemaAdapter;
	}
}
