//package declaration
package ch.nolix.system.sqlrawobjectschema.databaseinitializer;

//own imports
import ch.nolix.system.sqlrawobjectschema.columntable.ColumnTableSQLDTOCatalogue;
import ch.nolix.system.sqlrawobjectschema.structure.SystemDataTableSQLDTOCatalogue;
import ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter;

//class
final class InternalDatabaseInitializer {
	
	//method
	public void initializeDatabase(final ISchemaAdapter schemaAdapter) {
		
		schemaAdapter.addTable(SystemDataTableSQLDTOCatalogue.DATABASE_PROPERTY_SYSTEM_TABLE_SQL_DTO);
		schemaAdapter.addTable(SystemDataTableSQLDTOCatalogue.TABLE_SYSTEM_TABLE_SQL_DTO);
		schemaAdapter.addTable(ColumnTableSQLDTOCatalogue.COLUMNS_TABLE_SQL_DTO);
		
		schemaAdapter.saveChanges();
	}
}
