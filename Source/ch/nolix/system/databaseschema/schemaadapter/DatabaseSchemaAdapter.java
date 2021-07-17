//package declaration
package ch.nolix.system.databaseschema.schemaadapter;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.programcontrol.closeableelement.CloseController;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedPropertyType;
import ch.nolix.system.databaseschema.schema.Column;
import ch.nolix.system.databaseschema.schema.Database;
import ch.nolix.system.databaseschema.schema.Table;
import ch.nolix.techapi.databaseschemaapi.extendedschemaadapterapi.IExtendedDatabaseSchemaAdapter;
import ch.nolix.techapi.databaseschemaapi.schemaaccessorapi.IDatabaseAccessor;

//class
public abstract class DatabaseSchemaAdapter<DA extends IDatabaseAccessor> implements IExtendedDatabaseSchemaAdapter<
	Database,
	Table,
	Column,
	ParametrizedPropertyType<?>
> {
	
	//attributes
	private final String databaseName;
	private final CloseController closeController = new CloseController(this);
	private DatabaseSchemaSession<DatabaseSchemaAdapter<DA>, DA> session;
	
	//constructor
	public DatabaseSchemaAdapter(final String databaseName) {
		
		Validator.assertThat(databaseName).thatIsNamed("database name").isNotBlank();
		
		this.databaseName = databaseName;
		initializeSession();
	}
	
	//method
	@Override
	public final CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public final Database getRefDatabase() {
		return session.getRefDatabase();
	}
	
	//method
	@Override
	public final boolean hasChanges() {
		return session.hasChanges();
	}
	
	//method
	@Override
	public final void noteClose() {
		session.close();
	}
	
	//method
	@Override
	public final void reset() {
		session.close();
		initializeSession();
	}
	
	//method
	@Override
	public final void saveChanges() {
		session.saveChanges();
		reset();
	}
	
	//method declaration
	protected abstract IDatabaseAccessor getRefAccessorForDatabase();
	
	//method declaration
	protected abstract boolean accessorContainsAnyRecursiveChange(DA databaseAccessor);
	
	//method declaration
	protected abstract DA createAccessorForDatabase(Database database);
	
	//method declaration
	protected abstract void saveChangesRecursivelyToDatabaseFromAccessor(DA databaseAccessor);
	
	//method
	final String getDatabaseName() {
		return databaseName;
	}
	
	//method
	private void initializeSession() {
		session = new DatabaseSchemaSession<>(this);
	}
}
