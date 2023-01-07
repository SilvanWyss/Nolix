//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.system.objectdatabase.databasehelper.DatabaseHelper;
import ch.nolix.system.objectdatabase.databasevalidator.DatabaseValidator;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.IDatabaseHelper;

//class
final class DatabaseSaver {
	
	//constant
	private static final DatabaseValidator DATABASE_VALIDATOR = new DatabaseValidator();
	
	//constant
	private static final EntitySaver ENTITY_SAVER = new EntitySaver();
	
	//constant
	private static final IDatabaseHelper DATABASE_HELPER = new DatabaseHelper();
	
	//constant
	private static final DatabaseSaveValidator DATABASE_SAVE_VALIDATOR = new DatabaseSaveValidator();
	
	//method
	public void saveChanges(final Database database) {
		
		DATABASE_VALIDATOR.assertCanSaveChanges(database);
		
		saveChangesOfEntitiesOfDatabase(database);
				
		assertNewlyReferencedEntitiesExists(database);
		
		expectInitialSchemaTimestamp(database);
		
		actualSaveChanges(database);
	}
	
	//method
	private void saveChangesOfEntitiesOfDatabase(final Database database) {
		
		final var entitiesInLocalData = DATABASE_HELPER.getRefEntitiesInLocalData(database);
		
		for (final var e : entitiesInLocalData) {
			ENTITY_SAVER.saveChangesOfEntity(e, database);
		}
	}
	
	//method
	private void expectInitialSchemaTimestamp(final Database database) {
		database.internalGetRefDataAndSchemaAdapter().expectGivenSchemaTimestamp(database.getSchemaTimestamp());
	}
	
	//method
	private void assertNewlyReferencedEntitiesExists(final Database database) {
		DATABASE_SAVE_VALIDATOR.addExpectionsThatNewlyReferencedEntitiesExistToDatabase(database);
	}
	
	//method
	private void actualSaveChanges(final Database database) {
		database.internalGetRefDataAndSchemaAdapter().saveChangesAndReset();
	}
}
