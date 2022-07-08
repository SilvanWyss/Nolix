//package declaration
package ch.nolix.system.objectschema.schemaadapter;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.objectschema.parametrizedpropertytype.SchemaImplementation;
import ch.nolix.system.objectschema.schema.Database;
import ch.nolix.system.objectschema.schemahelper.DatabaseHelper;
import ch.nolix.systemapi.objectschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IDatabaseHelper;

//class
public abstract class SchemaAdapter implements ISchemaAdapter<SchemaImplementation> {
	
	//static attribute
	private static final IDatabaseHelper databaseHelper = new DatabaseHelper();
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//attribute
	private IDatabase<SchemaImplementation> database;
	
	//attribute
	private final ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter rawSchemaAdapter;
	
	//attribute
	private int saveCount;
	
	//constructor
	protected SchemaAdapter(
		final String databaseName,
		final ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter rawSchemaAdapter
	) {
		
		GlobalValidator
		.assertThat(rawSchemaAdapter)
		.thatIsNamed(ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter.class)
		.isNotNull();
		
		this.rawSchemaAdapter = rawSchemaAdapter;
		
		getRefCloseController().createCloseDependencyTo(rawSchemaAdapter);
		
		resetUsingDatabaseName(databaseName);
	}
	
	//method
	@Override
	public ISchemaAdapter<SchemaImplementation> addTable(final ITable<SchemaImplementation> table) {
		
		database.addTable(table);
		
		return this;
	}
	
	//method
	@Override
	public final CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public final ITable<SchemaImplementation> getRefTableByName(final String name) {
		return databaseHelper.getRefTableWithGivenName(database, name);
	}
	
	//method
	@Override
	public final IContainer<ITable<SchemaImplementation>> getRefTables() {
		return database.getRefTables();
	}
	
	//method
	@Override
	public final int getSaveCount() {
		return saveCount;
	}
	
	//method
	@Override
	public int getTableCount() {
		return database.getTableCount();
	}
	
	//method
	@Override
	public final boolean hasChanges() {
		return rawSchemaAdapter.hasChanges();
	}
	
	//method
	@Override
	public final void noteClose() {}
	
	//method
	@Override
	public final void reset() {
		resetUsingDatabaseName(database.getName());
	}
	
	//method
	@Override
	public final void saveChangesAndReset() {
		try {
			
			databaseHelper.assertAllBackReferencesAreValid(database);
			rawSchemaAdapter.saveChangesAndReset();
			
			saveCount++;
		} finally {
			reset();
		}
	}
	
	//method
	private void resetUsingDatabaseName(final String databaseName) {
		
		database = new Database(databaseName);
		database.setRawSchemaAdapter(rawSchemaAdapter);
		
		rawSchemaAdapter.reset();
	}
}
