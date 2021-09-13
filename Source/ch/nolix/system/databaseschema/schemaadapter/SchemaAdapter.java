//package declaration
package ch.nolix.system.databaseschema.schemaadapter;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.programcontrol.groupcloseable.CloseController;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedPropertyType;
import ch.nolix.system.databaseschema.schema.Column;
import ch.nolix.system.databaseschema.schema.Database;
import ch.nolix.system.databaseschema.schema.Table;
import ch.nolix.techapi.databaseschemaapi.extendedschemaadapterapi.IExtendedSchemaAdapter;
import ch.nolix.techapi.intermediateschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public abstract class SchemaAdapter implements IExtendedSchemaAdapter<
	Database,
	Table,
	Column,
	ParametrizedPropertyType<?>
> {
	
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
	protected abstract ISchemaAdapter createIntermediateSchemaAdapter();
	
	//method
	protected final void initializeSession() {
		session = new DatabaseSchemaSession(databaseName, createIntermediateSchemaAdapter());
	}
}
