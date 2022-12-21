//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.system.objectdatabase.databasehelper.DatabaseHelper;
import ch.nolix.system.objectdatabase.databasehelper.EntityHelper;
import ch.nolix.system.objectdatabase.databasevalidator.DatabaseValidator;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.IDatabaseHelper;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.IEntityHelper;

//class
final class SaveProcessor {
	
	//static attribute
	private static final IDatabaseHelper databaseHelper = new DatabaseHelper();
	
	//static attribute
	private static final IEntityHelper entityHelper = new EntityHelper();
	
	//static attribute
	private static final DatabaseSaveValidator databaseSaveValidator = new DatabaseSaveValidator();
	
	//method
	public void saveChanges(final Database database) {
		
		DatabaseValidator.INSTANCE.assertCanSaveChanges(database);
		
		processDeletedEntities(database);
		
		setEditedEntitiesAsUpdated(database);
		
		expectInitialSchemaTimestamp(database);
		
		assertNewlyReferencedEntitiesExists(database);
		
		actualSaveChanges(database);
	}
	
	//method
	private void processDeletedEntities(final Database database) {
		
		final var entitiesInLocalData = databaseHelper.getRefEntitiesInLocalData(database);
		
		for (final var e : entitiesInLocalData) {
			if (entityHelper.isDeleted(e)) {
				database.internalGetRefDataAndSchemaAdapter().deleteRecordFromTable(
					e.getRefParentTable().getName(),
					entityHelper.createRecordHeadDTOForEntity(e)
				);
			}
		}
	}
	
	//method
	private void setEditedEntitiesAsUpdated(final Database database) {
		
		final var entitiesInLocalData = databaseHelper.getRefEntitiesInLocalData(database);
		
		for (final var e : entitiesInLocalData) {
			switch (e.getState()) {
				case NEW:
					
					database.internalGetRefDataAndSchemaAdapter().insertRecordIntoTable(
						e.getParentTableName(),
						entityHelper.createRecordFor(e)
					);
					
					//TODO: Beautify this step.
					((BaseEntity)e).internalUpdateMultiPropertiesWhenIsNew();
					
					break;
				case EDITED:
					
					database.internalGetRefDataAndSchemaAdapter().updateEntityOnTable(
						e.getParentTableName(),
						entityHelper.createEntityUpdateDTOForEntity(e)
					);
					
					break;
				default:
					//Does nothing.
			}
		}
	}
	
	//method
	private void expectInitialSchemaTimestamp(final Database database) {
		database.internalGetRefDataAndSchemaAdapter().expectGivenSchemaTimestamp(database.getSchemaTimestamp());
	}
	
	//method
	private void assertNewlyReferencedEntitiesExists(final Database database) {
		databaseSaveValidator.addExpectionsThatNewlyReferencedEntitiesExistToDatabase(database);
	}
	
	//method
	private void actualSaveChanges(final Database database) {
		database.internalGetRefDataAndSchemaAdapter().saveChangesAndReset();
	}
}
