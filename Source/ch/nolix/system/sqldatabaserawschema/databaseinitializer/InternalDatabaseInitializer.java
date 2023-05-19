//package declaration
package ch.nolix.system.sqldatabaserawschema.databaseinitializer;

import ch.nolix.core.sql.SQLConnectionPool;
import ch.nolix.system.sqldatabaserawschema.columntable.ColumnTableSQLDTOCatalogue;
import ch.nolix.system.sqldatabaserawschema.databasepropertytable.DatabaseProperty;
import ch.nolix.system.sqldatabaserawschema.databasepropertytable.DatabasePropertySystemTableColumn;
import ch.nolix.system.sqldatabaserawschema.databasepropertytable.DatabasePropertyTableSQLDTOCatalogue;
import ch.nolix.system.sqldatabaserawschema.multireferenceentrytable.MultiReferenceEntryTableSQLDTOCatalogue;
import ch.nolix.system.sqldatabaserawschema.multivalueentrytable.MultiValueEntryTableSQLDTOCatalogue;
import ch.nolix.system.sqldatabaserawschema.structure.SystemDataTable;
import ch.nolix.system.sqldatabaserawschema.tabletable.TableTableSQLDTOCatalogue;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemaadapterapi.ISchemaAdapter;

//class
final class InternalDatabaseInitializer {
	
	//method
	public void initializeDatabase(
		final String databaseName,
		final ISchemaAdapter schemaAdapter,
		final SQLConnectionPool pSQLConnectionPool
	) {
		
		schemaAdapter.addTable(DatabasePropertyTableSQLDTOCatalogue.DATABASE_PROPERTY_TABLE_SQL_DTO);
		schemaAdapter.addTable(TableTableSQLDTOCatalogue.TABLE_TABLE_SQL_DTO);
		schemaAdapter.addTable(ColumnTableSQLDTOCatalogue.COLUMN_TABLE_SQL_DTO);
		
		schemaAdapter.addTable(MultiReferenceEntryTableSQLDTOCatalogue.MULTI_REFERENCE_ENTRY_TABLE_SQL_DTO);
		schemaAdapter.addTable(MultiValueEntryTableSQLDTOCatalogue.MULTI_VALUE_ENTRY_TABLE_SQL_DTO);
		
		schemaAdapter.saveChangesAndReset();
		
		createSchemaTimestampEntry(databaseName, pSQLConnectionPool);
	}

	private void createSchemaTimestampEntry(final String databaseName, SQLConnectionPool pSQLConnectionPool) {
		try (final var lSQLConnection = pSQLConnectionPool.borrowSQLConnection()) {
			lSQLConnection.execute("USE " + databaseName);
			lSQLConnection.execute(createSQLStatementToCreateSchemaTimestampEntry());
		}
	}
	
	private String createSQLStatementToCreateSchemaTimestampEntry() {
		return
		"INSERT INTO "
		+ SystemDataTable.DATABASE_PROPERTY.getQualifiedName()
		+ " ("
		+ DatabasePropertySystemTableColumn.KEY.getLabel()
		+ ", "
		+ DatabasePropertySystemTableColumn.VALUE.getLabel()
		+ ") VALUES ("
		+ DatabaseProperty.SCHEMA_TIMESTAMP.getLabelInQuotes()
		+ ", '"
		+ Time.ofNow().getSpecification().getOriSingleChildNode()
		+ "');";
	}
}
