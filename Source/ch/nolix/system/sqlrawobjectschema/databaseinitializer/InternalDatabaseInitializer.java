//package declaration
package ch.nolix.system.sqlrawobjectschema.databaseinitializer;

//own imports
import ch.nolix.system.sqlrawobjectschema.columntable.ColumnTableSQLDTOCatalogue;
import ch.nolix.system.sqlrawobjectschema.databasepropertytable.DatabasePropertyTableSQLDTOCatalogue;
import ch.nolix.system.sqlrawobjectschema.multireferenceentrytable.MultiReferenceEntryTableSQLDTOCatalogue;
import ch.nolix.system.sqlrawobjectschema.multivalueentrytable.MultiValueEntryTableSQLDTOCatalogue;
import ch.nolix.system.sqlrawobjectschema.tabletable.TableTableSQLDTOCatalogue;
import ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter;

//class
final class InternalDatabaseInitializer {
	
	//method
	public void initializeDatabase(final ISchemaAdapter schemaAdapter) {
		
		schemaAdapter.addTable(DatabasePropertyTableSQLDTOCatalogue.DATABASE_PROPERTY_TABLE_SQL_DTO);
		schemaAdapter.addTable(TableTableSQLDTOCatalogue.TABLE_TABLE_SQL_DTO);
		schemaAdapter.addTable(ColumnTableSQLDTOCatalogue.COLUMN_TABLE_SQL_DTO);
		
		schemaAdapter.addTable(MultiReferenceEntryTableSQLDTOCatalogue.MULTI_REFERENCE_ENTRY_TABLE_SQL_DTO);
		schemaAdapter.addTable(MultiValueEntryTableSQLDTOCatalogue.MULTI_VALUE_ENTRY_TABLE_SQL_DTO);
		
		schemaAdapter.saveChanges();
	}
}
