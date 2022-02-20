//package declaration
package ch.nolix.system.objectschema.schemaadapter;

//own imports
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
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
	private final ch.nolix.systemapi.rawobjectschemaapi.schemaadapterapi.ISchemaAdapter rawObjectSchemaAdapter;
	
	//attribute
	private int saveCount;
	
	//constructor
	public SchemaAdapter(
		final String databaseName,
		final ch.nolix.systemapi.rawobjectschemaapi.schemaadapterapi.ISchemaAdapter rawObjectSchemaAdapter
	) {
		
		Validator
		.assertThat(rawObjectSchemaAdapter)
		.thatIsNamed(ch.nolix.systemapi.rawobjectschemaapi.schemaadapterapi.ISchemaAdapter.class)
		.isNotNull();
		
		this.rawObjectSchemaAdapter = rawObjectSchemaAdapter;
		
		createCloseDependencyTo(rawObjectSchemaAdapter);
		
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
	public final int getSaveCount() {
		return saveCount;
	}
	
	//method
	@Override
	public final boolean hasChanges() {
		return rawObjectSchemaAdapter.hasChanges();
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
			rawObjectSchemaAdapter.saveChangesAndReset();
			
			saveCount++;
		} finally {
			reset();
		}
	}
	
	//method
	private void resetUsingDatabaseName(final String databaseName) {
		
		database = new Database(databaseName);
		database.setRawObjectSchemaAdapter(rawObjectSchemaAdapter);
		
		rawObjectSchemaAdapter.reset();
	}
}
