//package declaration
package ch.nolix.system.objectschema.schemaadapter;

//own imports
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.system.objectschema.parametrizedpropertytype.SchemaImplementation;
import ch.nolix.system.objectschema.schema.Database;
import ch.nolix.system.objectschema.schema.Table;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawobjectschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public abstract class SchemaAdapter
implements ch.nolix.systemapi.objectschemaapi.schemaadapterapi.ISchemaAdapter<SchemaImplementation> {
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//attribute
	private final String databaseName;
	
	//attribute
	private DatabaseSchemaSession session;
	
	//constructor
	public SchemaAdapter(final String databaseName) {
		
		Validator.assertThat(databaseName).thatIsNamed("database name").isNotBlank();
		
		this.databaseName = databaseName;
	}
	
	//method
	@Override
	public ch.nolix.systemapi.objectschemaapi.schemaadapterapi.ISchemaAdapter<SchemaImplementation> addTable(
		final ITable<SchemaImplementation> table
	) {
		
		getRefDatabase().addTable(table);
		
		return this;
	}
	
	//method
	public ch.nolix.systemapi.objectschemaapi.schemaadapterapi.ISchemaAdapter<SchemaImplementation> addTable(
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
	public final int getSaveCount() {
		
		//TODO: Implement.
		return 0;
	}
	
	//method
	@Override
	public final boolean hasChanges() {
		return session.hasChanges();
	}
	
	//method
	@Override
	public final void noteClose() {}
	
	//method
	@Override
	public final void reset() {
		//TODO: Implement.
	}
	
	//method
	@Override
	public final void saveChangesAndReset() {
		try {
			session.saveChanges();
		} finally {
			reset();
		}
	}
	
	//method declaration
	protected abstract ISchemaAdapter createRawSchemaAdapter();
	
	//method
	protected final void initializeSession() {
		session = new DatabaseSchemaSession(databaseName, createRawSchemaAdapter());
	}
}
