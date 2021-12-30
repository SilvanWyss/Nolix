//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.objectschema.schemahelper.ColumnHelper;
import ch.nolix.system.objectschema.schemahelper.DatabaseHelper;
import ch.nolix.system.objectschema.schemahelper.TableHelper;
import ch.nolix.techapi.objectschemaapi.schemahelperapi.IColumnHelper;
import ch.nolix.techapi.objectschemaapi.schemahelperapi.IDatabaseHelper;
import ch.nolix.techapi.objectschemaapi.schemahelperapi.ITableHelper;

//class
final class DatabaseMutationValidator {
	
	//static attributes
	private static final IDatabaseHelper databaseHelper = new DatabaseHelper();
	private static final ITableHelper tableHelper = new TableHelper();
	private static final IColumnHelper columnHelper = new ColumnHelper();
	
	//method
	public void assertCanAddTableToDatabase(
		final Database database,
		final Table table
	) {
		
		database.assertIsOpen();
		databaseHelper.assertIsNotDeleted(database);
		databaseHelper.assertDoesNotContainTableWithGivenName(database, table.getName());
		assertCanAddTableToDatabaseBecauseOfColumns(database, table);
		
		table.assertIsOpen();
		tableHelper.assertIsNotDeleted(table);
	}
	
	//method
	public void assertCanSetNameToDatabase(final Database database, final String name) {
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
	}
	
	//method
	private void assertCanAddTableToDatabaseBecauseOfColumn(
		final Database database,
		final Table table,
		final Column column
	) {
		switch (columnHelper.getBasePropertyType(column)) {
			case BASE_BACK_REFERENCE:
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
	private void assertCanAddTableToDatabaseBecauseOfColumns(
		final Database database,
		final Table table
	) {
		for (final var c : table.getRefColumns()) {
			assertCanAddTableToDatabaseBecauseOfColumn(database, table, (Column)c);
		}
	}
}
