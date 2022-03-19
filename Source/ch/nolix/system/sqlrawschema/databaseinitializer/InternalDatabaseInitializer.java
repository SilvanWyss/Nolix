//package declaration
package ch.nolix.system.sqlrawschema.databaseinitializer;

//own imports
import ch.nolix.system.sqlrawschema.columntable.ColumnTableSQLDTOCatalogue;
import ch.nolix.system.sqlrawschema.databasepropertytable.DatabasePropertyTableSQLDTOCatalogue;
import ch.nolix.system.sqlrawschema.multireferenceentrytable.MultiReferenceEntryTableSQLDTOCatalogue;
import ch.nolix.system.sqlrawschema.multivalueentrytable.MultiValueEntryTableSQLDTOCatalogue;
import ch.nolix.system.sqlrawschema.tabletable.TableTableSQLDTOCatalogue;
import ch.nolix.systemapi.sqlbasicschemaapi.schemaadapterapi.ISchemaAdapter;

//class
final class InternalDatabaseInitializer {
	
	//method
	public void initializeDatabase(final ISchemaAdapter schemaAdapter) {
		
		schemaAdapter.addTable(DatabasePropertyTableSQLDTOCatalogue.DATABASE_PROPERTY_TABLE_SQL_DTO);
		schemaAdapter.addTable(TableTableSQLDTOCatalogue.TABLE_TABLE_SQL_DTO);
		schemaAdapter.addTable(ColumnTableSQLDTOCatalogue.COLUMN_TABLE_SQL_DTO);
		
		schemaAdapter.addTable(MultiReferenceEntryTableSQLDTOCatalogue.MULTI_REFERENCE_ENTRY_TABLE_SQL_DTO);
		schemaAdapter.addTable(MultiValueEntryTableSQLDTOCatalogue.MULTI_VALUE_ENTRY_TABLE_SQL_DTO);
		
		schemaAdapter.saveChangesAndReset();
	}
}
