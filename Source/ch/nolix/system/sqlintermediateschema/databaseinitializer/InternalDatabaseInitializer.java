//package declaration
package ch.nolix.system.sqlintermediateschema.databaseinitializer;

//own imports
import ch.nolix.system.sqlintermediateschema.structure.SystemDataTableSQLDTOCatalogue;
import ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaWriter;

//class
final class InternalDatabaseInitializer {
	
	//class
	public void initializeDatabase(final ISchemaAdapter schemaAdapter) {
		initializeDatabase(schemaAdapter.getRefSchemaWriter());
	}
	
	//method
	private void initializeDatabase(final ISchemaWriter schemaWriter) {
		
		schemaWriter.addTable(SystemDataTableSQLDTOCatalogue.DATABASE_PROPERTY_SYSTEM_TABLE_SQL_DTO);
		schemaWriter.addTable(SystemDataTableSQLDTOCatalogue.TABLE_SYSTEM_TABLE_SQL_DTO);
		schemaWriter.addTable(SystemDataTableSQLDTOCatalogue.COLUMNS_SYSTEM_TABLE_SQL_DTO);
		
		schemaWriter.saveChanges();
	}
}
