//package declaration
package ch.nolix.system.objectschema.schemaadapter;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.programcontrol.groupcloseable.CloseController;
import ch.nolix.system.objectschema.parametrizedpropertytype.SchemaImplementation;
import ch.nolix.system.objectschema.schema.Database;
import ch.nolix.system.objectschema.schema.Table;
import ch.nolix.techapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.techapi.rawobjectschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public abstract class SchemaAdapter
implements ch.nolix.techapi.objectschemaapi.schemaadapterapi.ISchemaAdapter<SchemaImplementation> {
	
	//attributes
	private final String databaseName;
	private final CloseController closeController = new CloseController(this);
	private DatabaseSchemaSession session;
	
	//constructor
	public SchemaAdapter(final String databaseName) {
		
		Validator.assertThat(databaseName).thatIsNamed("database name").isNotBlank();
		
		this.databaseName = databaseName;
	}
	
	//method
	@Override
	public ch.nolix.techapi.objectschemaapi.schemaadapterapi.ISchemaAdapter<SchemaImplementation> addTable(
		final ITable<SchemaImplementation> table
	) {
		
		getRefDatabase().addTable(table);
		
		return this;
	}
	
	//method
	public ch.nolix.techapi.objectschemaapi.schemaadapterapi.ISchemaAdapter<SchemaImplementation> addTable(
		final Table... tables
	) {
		
		//TODO: Move this implementation.
		for (final var t : tables) {
			getRefDatabase().addTable(t);
		}
		
		return this;
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
	public final void saveChanges() {
		session.saveChanges();
	}
	
	//method declaration
	protected abstract ISchemaAdapter createRawSchemaAdapter();
	
	//method
	protected final void initializeSession() {
		session = new DatabaseSchemaSession(databaseName, createRawSchemaAdapter());
	}
}
