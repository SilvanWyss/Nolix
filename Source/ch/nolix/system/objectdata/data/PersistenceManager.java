//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.datahelper.DatabaseHelper;
import ch.nolix.system.objectdata.datahelper.EntityHelper;
import ch.nolix.systemapi.objectdataapi.datahelperapi.IDatabaseHelper;
import ch.nolix.systemapi.objectdataapi.datahelperapi.IEntityHelper;

//class
final class PersistenceManager {
	
	//static attribute
	private static final IDatabaseHelper databaseHelper = new DatabaseHelper();
	
	//static attribute
	private static final IEntityHelper entityHelper = new EntityHelper();
	
	//method
	public void peristChanges(final Database database) {
		
		assertGivenDatabaseIsReadyForPersistChanges(database);
		
		setEditedEntitiesAsUpdated(database);
		
		expectInitialSchemaTimestamp(database);
		
		persistChanges(database);
	}
	
	//method
	private void assertGivenDatabaseIsReadyForPersistChanges(final Database database) {
		if (!givenDatabaseIsReadyForPersistChanges(database)) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(database, "is not ready for persist changes");
		}
	}
	
	//method
	private void expectInitialSchemaTimestamp(final Database database) {
		database.internalGetRefDataAndSchemaAdapter().expectGivenSchemaTimestamp(database.getSchemaTimestamp());
	}
	
	//method
	private boolean givenDatabaseIsReadyForPersistChanges(final Database database) {
		return
		database.isOpen()
		&& database.isLinkedWithRealDatabase();
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
