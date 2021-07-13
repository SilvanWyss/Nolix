//package declaration
package ch.nolix.system.databaseschema.schemaadapter;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.databaseschema.schema.Database;
import ch.nolix.techapi.databaseschemaapi.schemaaccessorapi.IDatabaseAccessor;

//class
final class DatabaseSchemaSession<DSA extends DatabaseSchemaAdapter<DA>, DA extends IDatabaseAccessor>
implements AutoCloseable {
	
	//attributes
	private final DSA parentAdapter;
	private final Database database;
	private final DA accessor;
	
	//constructor
	public DatabaseSchemaSession(final DSA parentAdapter) {
		
		Validator.assertThat(parentAdapter).thatIsNamed("parent adapter").isNotNull();
		
		this.parentAdapter = parentAdapter;
		database = new Database(parentAdapter.getDatabaseName());
		accessor = parentAdapter.createAccessorForDatabase(database);
	}
	
	//method
	@Override
	public void close() {
		database.close();
	}
	
	//method
	public Database getRefDatabase() {
		return database;
	}
	
	//method
	public boolean hasChanges() {
		return parentAdapter.accessorContainsAnyRecursiveChange(accessor);
	}
	
	//method
	public void saveChanges() {
		parentAdapter.saveChangesRecursivelyToDatabaseFromAccessor(accessor);
		close();
	}
}
