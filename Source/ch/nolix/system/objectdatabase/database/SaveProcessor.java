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
	
	//method
	public void peristChanges(final Database database) {
		
		DatabaseValidator.INSTANCE.assertCanSaveChanes(database);
		
		setEditedEntitiesAsUpdated(database);
		
		expectInitialSchemaTimestamp(database);
		
		persistChanges(database);
	}
	
	//method
	private void expectInitialSchemaTimestamp(final Database database) {
		database.internalGetRefDataAndSchemaAdapter().expectGivenSchemaTimestamp(database.getSchemaTimestamp());
	}
	
	//method
	private void persistChanges(final Database database) {
		database.internalGetRefDataAndSchemaAdapter().saveChangesAndReset();
	}
	
	//method
	private void setEditedEntitiesAsUpdated(final Database database) {
		final var entitiesInLocalData = databaseHelper.getRefEntitiesInLocalData(database);
		for (final var e : entitiesInLocalData) {
			if (entityHelper.isEdited(e)) {
				database.internalGetRefDataAndSchemaAdapter().setEntityAsUpdated(
					e.getParentTableName(),
					entityHelper.createRecordHeadDTOForEntity(e)
				);
			}
		}
	}
}
