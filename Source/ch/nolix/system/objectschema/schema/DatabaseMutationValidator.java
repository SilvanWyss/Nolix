//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.objectschema.schemahelper.ColumnHelper;
import ch.nolix.system.objectschema.schemahelper.DatabaseEngineHelper;
import ch.nolix.system.objectschema.schemahelper.DatabaseHelper;
import ch.nolix.system.objectschema.schemahelper.TableHelper;
import ch.nolix.techapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.techapi.objectschemaapi.schemahelperapi.IColumnHelper;
import ch.nolix.techapi.objectschemaapi.schemahelperapi.IDatabaseEngineHelper;
import ch.nolix.techapi.objectschemaapi.schemahelperapi.IDatabaseHelper;
import ch.nolix.techapi.objectschemaapi.schemahelperapi.ITableHelper;

//class
final class DatabaseMutationValidator {
	
	//static attributes
	private static final IDatabaseEngineHelper databaseEngineHelper = new DatabaseEngineHelper();
	private static final IDatabaseHelper databaseHelper = new DatabaseHelper();
	private static final ITableHelper tableHelper = new TableHelper();
	private static final IColumnHelper columnHelper = new ColumnHelper();
	
	//method
	public void assertCanAddTableToDatabase(final Database database, final ITable table) {
		
		database.assertIsOpen();
		database.assertIsNotDeleted();
		databaseHelper.assertDoesNotContainTableWithGivenName(database, table.getName());
		assertCanAddTableToDatabaseBecauseOfColumns(database, table);
		
		table.assertIsOpen();
		tableHelper.assertIsNotDeleted(table);
	}
	
	//method
	public void assertCanSetNameToDatabase(final Database database, final String name) {
		
		if (database.belongsToEngine()) {
			databaseEngineHelper.assertDoesNotContainDatabaseWithGivenName(database.getParentEngine(), name);
		}
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
	}
	
	//method
	private void assertCanAddTableToDatabaseBecauseOfColumn(
		final Database database,
		final ITable table,
		final IColumn column
	) {
		switch (columnHelper.getBasePropertyType(column)) {
			case BASE_BACK_REFERENCE:
				
				//TODO
				/*
				if (!tableHelper.containsColumnThatReferencesBackGivenColumn(table, column)) {
					databaseHelper.assertContainsTableWithColumnBackReferencedByGivenColumn(database, column);
				}
				*/
				
				break;
			case BASE_REFERENCE:
				
				if (!columnHelper.referencesGivenTable(column, table)) {
					databaseHelper.assertContainsTableReferencedByGivenColumn(database, column);
				}
				
				break;
			case BASE_VALUE:
				break;
		}
	}
	
	//method
	private void assertCanAddTableToDatabaseBecauseOfColumns(final Database database, final ITable table) {
		for (final var c : table.getRefColumns()) {
			assertCanAddTableToDatabaseBecauseOfColumn(database, table, c);
		}
	}
}
