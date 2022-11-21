//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.system.objectschema.parametrizedpropertytype.SchemaImplementation;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ISchema;
import ch.nolix.systemapi.objectdatabaseapi.schemamapperapi.ITableMapper;
import ch.nolix.systemapi.objectschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class SchemaInitializer {
	
	//static attribute
	private static final ITableMapper<SchemaImplementation> tableMapper =
	new ch.nolix.system.objectdatabase.schemamapper.TableMapper();
	
	//method
	public void initializeDatabaseToGivenSchemaUsingGivenSchemaAdapterIfDatabaseIsEmpty(
		final ISchema<DataImplementation> schema,
		final ISchemaAdapter<SchemaImplementation> schemaAdapter
	) {
		if (databaseIsEmpty(schemaAdapter)) {
			initializeDatabaseToGivenSchemaUsingGivenSchemaAdapter(schema, schemaAdapter);
		}
	}

	//method
	private boolean databaseIsEmpty(final ISchemaAdapter<SchemaImplementation> schemaAdapter) {
		return (schemaAdapter.getTableCount() == 0);
	}
	
	//method
	private void initializeDatabaseToGivenSchemaUsingGivenSchemaAdapter(
		final ISchema<DataImplementation> schema,
		final ISchemaAdapter<SchemaImplementation> schemaAdapter
	) {
		
		for (final var t : tableMapper.createTablesFrom(schema)) {
			schemaAdapter.addTable(t);
		}
		
		schemaAdapter.saveChangesAndReset();
	}
}
