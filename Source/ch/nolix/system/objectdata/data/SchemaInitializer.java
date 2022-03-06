//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.system.objectschema.parametrizedpropertytype.SchemaImplementation;
import ch.nolix.systemapi.objectdataapi.dataapi.ISchema;
import ch.nolix.systemapi.objectdataapi.schemamapperapi.ITableMapper;
import ch.nolix.systemapi.objectschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class SchemaInitializer {
	
	//static attribute
	private static final ITableMapper<SchemaImplementation> tableMapper =
	new ch.nolix.system.objectdata.schemamapper.TableMapper();
	
	//method
	public void initializeDatabaseWithGivenSchemaUsingGivenSchemaAdapterIfDatabaseIsEmpty(
		final ISchema<DataImplementation> schema,
		final ISchemaAdapter<SchemaImplementation> schemaAdapter
	) {
		if (schemaAdapter.getTableCount() == 0) {
			for (final var t : tableMapper.createTablesFrom(schema)) {
				schemaAdapter.addTable(t);
			}
			schemaAdapter.saveChangesAndReset();
		}
	}
}
