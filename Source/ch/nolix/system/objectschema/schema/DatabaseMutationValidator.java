//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;

//class
final class DatabaseMutationValidator {
	
	//method
	public void assertCanAddTableToDatabase(final Database database, final Table table) {
		
		database.assertIsOpen();
		database.assertIsNotDeleted();
		database.assertDoesNotContainTableWithName(table.getName());
		assertCanAddTableToDatabaseBecauseOfColumns(database, table);
		
		table.assertIsOpen();
		table.assertIsNotDeleted();
	}
	
	//method
	public void assertCanSetNameToDatabase(final Database database, final String name) {
		
		if (database.belongsToEngine()) {
			database.getParentEngine().assertDoesNotContainDatabaseWithName(name);
		}
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
	}
	
	//method
	private void assertCanAddTableToDatabaseBecauseOfColumn(
		final Database database,
		final Table table,
		final Column column
	) {
		switch (column.getBasePropertyType()) {
			case BASE_BACK_REFERENCE:
				
				if (!table.containsColumnThatReferencesBackColumn(column)) {
					database.assertContainsTableWithColumnBackReferencedByColumn(column);
				}
				
				break;
			case BASE_REFERENCE:
				
				if (!column.references(table)) {
					database.assertContainsTableReferencedByColumn(column);
				}
				
				break;
			case BASE_VALUE:
				break;
		}
	}
	
	//method
	private void assertCanAddTableToDatabaseBecauseOfColumns(final Database database, final Table table) {
		for (final var c : table.getRefColumns()) {
			assertCanAddTableToDatabaseBecauseOfColumn(database, table, c);
		}
	}
}
