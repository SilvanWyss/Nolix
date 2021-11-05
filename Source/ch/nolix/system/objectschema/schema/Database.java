//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedPropertyType;
import ch.nolix.techapi.objectschemaapi.extendedschemaapi.IExtendedDatabase;
import ch.nolix.techapi.objectschemaapi.extendedschemaapi.IExtendedDatabaseEngine;
import ch.nolix.techapi.rawobjectschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class Database extends DatabaseObject
implements IExtendedDatabase<Database, Table, Column, ParametrizedPropertyType<?>> {
	
	//static attributes
	private static final DatabaseMutationValidator mutationValidator = new DatabaseMutationValidator();
	private static final DatabaseMutationExecutor mutationExecutor = new DatabaseMutationExecutor();
	
	//attributes
	private final String name;
	private boolean loadedTablesFromDatabase;
	
	//optional attribute
	private IntermediateSchemaAdapter intermediateSchemaAdapter;
	
	//multi-attribute
	private LinkedList<Table> tables = new LinkedList<>();
	
	//constructor
	public Database(final String name) {
		
		mutationValidator.assertCanSetNameToDatabase(this, name);
		
		this.name = name;
	}
	
	//method
	@Override
	public Database addTable(final Table table) {
		
		mutationValidator.assertCanAddTableToDatabase(this, table);
		mutationExecutor.addTableToDatabase(this, table);
		
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
	public IExtendedDatabaseEngine<?, Database, Table, Column, ParametrizedPropertyType<?>> getParentEngine() {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public IContainer<Table> getRefTables() {
		
		loadTablesFromDatabaseIfNeeded();
		
		return tables;
	}
	
	//method
	@Override
	public boolean isLinkedWithRealDatabase() {
		return (intermediateSchemaAdapter != null);
	}
	
	//method
	@Override
	public void setRealSchemaAdapter(final ISchemaAdapter schemaAdapter) {
		setRealSchemaAdapter(new IntermediateSchemaAdapter(schemaAdapter));
	}
	
	//method
	@Override
	protected void noteCloseDatabaseObject() {
		
		//Does not call getRefTables method to avoid that the tables need to be loaded from the database.
		tables.forEach(Table::close);
	}
	
	//method
	void addTableAttribute(final Table table) {
		tables.addAtEnd(table);
	}
	
	//method
	IntermediateSchemaAdapter getRefRealSchemaAdapter() {
		
		assertIsLinkedWithRealDatabase();
		
		return intermediateSchemaAdapter;
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
		
		tables = getRefRealSchemaAdapter().getRefIntermediateSchemaReader().loadFlatTables().to(Table::fromFlatDTO);
		tables.forEach(Table::setLoaded);
		tables.forEach(t -> t.setParentDatabase(this));
		
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
		return (isLoaded() && !hasLoadedTablesFromDatabase());
	}
	
	//method
	private void setRealSchemaAdapter(final IntermediateSchemaAdapter intermediateSchemaAdapter) {
		
		Validator.assertThat(intermediateSchemaAdapter).thatIsNamed(IntermediateSchemaAdapter.class).isNotNull();
		assertIsNotLinkedWithActualDatabase();
		
		setLoaded();
		this.intermediateSchemaAdapter = intermediateSchemaAdapter;
	}
}
