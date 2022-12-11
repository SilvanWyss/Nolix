//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.system.objectdatabase.databasehelper.DatabaseHelper;
import ch.nolix.system.objectdatabase.databasehelper.EntityHelper;
import ch.nolix.system.objectdatabase.databasevalidator.DatabaseValidator;
import ch.nolix.system.objectdatabase.propertyhelper.PropertyHelper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IReference;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.IDatabaseHelper;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.IEntityHelper;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IPropertyHelper;

//class
final class SaveProcessor {
	
	//static attribute
	private static final IDatabaseHelper databaseHelper = new DatabaseHelper();
	
	//static attribute
	private static final IEntityHelper entityHelper = new EntityHelper();
	
	//static attribute
	private static final IPropertyHelper propertyHelper = new PropertyHelper();
	
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
					
					database.internalGetRefDataAndSchemaAdapter().setEntityAsUpdated(
						e.getParentTableName(),
						entityHelper.createRecordHeadDTOForEntity(e)
					);
					
					database.internalGetRefDataAndSchemaAdapter().updateRecordOnTable(
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
	
	//TODO: Refactor this method.
	//method
	private void assertNewlyReferencedEntitiesExists(final Database database) {
		
		final var entitiesInLocalData = databaseHelper.getRefEntitiesInLocalData(database);
		
		for (final var e : entitiesInLocalData) {
			if (entityHelper.isNewOrEdited(e)) {
				for (final var p : e.technicalGetRefProperties()) {
					if (propertyHelper.isNewOrEdited(p)) {
						switch (p.getType()) {
							case REFERENCE:
								
								final var reference = (IReference<?, ?>)p;
															
								database.internalGetRefDataAndSchemaAdapter().expectTableContainsEntity(
									reference.getReferencedTableName(),
									reference.getEntityId()
								);
															
								break;
							case OPTIONAL_REFERENCE:
								
								final var optionalReference = (OptionalReference<?>)p;
								
								if (optionalReference.containsAny()) {
									database.internalGetRefDataAndSchemaAdapter().expectTableContainsEntity(
										optionalReference.getReferencedTableName(),
										optionalReference.getEntityId()
									);
								}
								
								break;
							case MULTI_REFERENCE:
								//TODO: Implement.
								break;
							default:
								//Does nothing.
						}
					}
				}
			}
		}
	}
	
	//method
	private void actualSaveChanges(final Database database) {
		database.internalGetRefDataAndSchemaAdapter().saveChangesAndReset();
	}
}
