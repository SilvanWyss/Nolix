//package declaration
package ch.nolix.system.databaseschema.schema;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedPropertyType;
import ch.nolix.techapi.databaseschemaapi.extendedschemaapi.IExtendedDatabase;
import ch.nolix.techapi.databaseschemaapi.extendedschemaapi.IExtendedDatabaseEngine;
import ch.nolix.techapi.databaseschemaapi.schemaaccessorapi.IDatabaseAccessor;

//class
public final class Database extends DatabaseObject
implements IExtendedDatabase<Database, Table, Column, ParametrizedPropertyType<?>> {
	
	//static attributes
	private static final DatabaseMutationPreValidator mutationPreValidator = new DatabaseMutationPreValidator();
	private static final DatabaseMutationExecutor mutationExecutor = new DatabaseMutationExecutor();
	
	//attributes
	private final String name;
	private boolean loadedTablesFromDatabase;
	
	//optional attribute
	private IDatabaseAccessor accessor;
	
	//multi-attribute
	private LinkedList<Table> tables = new LinkedList<>();
	
	//constructor
	public Database(final String name) {
		
		mutationPreValidator.assertCanSetNameToDatabase(name);
		
		this.name = name;
	}
	
	//method
	@Override
	public Database addTable(final Table table) {
		
		mutationPreValidator.assertCanAddTableToDatabase(this, table);
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
	public boolean isLinkedWithActualDatabase() {
		return (accessor != null);
	}
	
	//method
	@Override
	public void setAccessorForActualDatabase(final IDatabaseAccessor accessor) {
		
		Validator.assertThat(accessor).thatIsNamed("accessor").isNotNull();
		assertIsNotLinkedWithActualDatabase();
		
		this.accessor = accessor;
	}
	
	//method
	IDatabaseAccessor getRefAccessor() {
		
		assertIsLinkedWithActualDatabase();
		
		return accessor;
	}
	
	//method
	void removeTableAttribute(final Table table) {
		tables.removeFirst(table);
	}
	
	//method
	@Override
	protected void noteCloseDatabaseObject() {
		
		//Does not call getRefTables method to avoid that the tables need to be loaded from the database.
		tables.forEach(Table::close);
	}

	//method
	private boolean hasLoadedTablesFromDatabase() {
		return loadedTablesFromDatabase;
	}
	
	//method
	private void loadTablesFromDatabase() {
		
		tables = getRefAccessor().loadFlatTablesFromCurrentDatabase().to(Table::fromFlatDTO);
		tables.forEach(Table::setLoaded);
		
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
}
