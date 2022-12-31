//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdatabase.databasehelper.DatabaseHelper;
import ch.nolix.system.objectdatabase.databasehelper.EntityHelper;
import ch.nolix.system.objectdatabase.databasevalidator.DatabaseValidator;
import ch.nolix.system.objectdatabase.propertyhelper.PropertyHelper;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiReferenceEntry;
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
	
	//static attribute
	private static final DatabaseSaveValidator databaseSaveValidator = new DatabaseSaveValidator();
	
	//method
	public void saveChanges(final Database database) {
		
		DatabaseValidator.INSTANCE.assertCanSaveChanges(database);
		
		processChangedEntitiesOfDatabase(database);
				
		assertNewlyReferencedEntitiesExists(database);
		
		expectInitialSchemaTimestamp(database);
		
		actualSaveChanges(database);
	}
	
	//method
	private void processChangedEntitiesOfDatabase(final Database database) {
		
		final var entitiesInLocalData = databaseHelper.getRefEntitiesInLocalData(database);
		
		for (final var e : entitiesInLocalData) {
			switch (e.getState()) {
				case NEW:
					
					database.internalGetRefDataAndSchemaAdapter().insertNewEntity(
						e.getParentTableName(),
						entityHelper.createNewEntityDTOForEntity(e)
					);
					
					saveMultiReferenceChangesOfEntity(e, database);
					
					break;
				case EDITED:
					
					database.internalGetRefDataAndSchemaAdapter().updateEntity(
						e.getParentTableName(),
						entityHelper.createEntityUpdateDTOForEntity(e)
					);
					
					saveMultiReferenceChangesOfEntity(e, database);
					
					break;
				case DELETED:
					
					database.internalGetRefDataAndSchemaAdapter().deleteEntity(
						e.getRefParentTable().getName(),
						entityHelper.createEntityHeadDTOForEntity(e)
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
	
	//method
	private void saveMultiReferenceChangesOfEntity(
		final IEntity<DataImplementation> entity,
		final Database database
	) {
		for (final var p : entity.technicalGetRefProperties()) {
			if (propertyHelper.isNewOrEdited(p) && p.getType() == PropertyType.MULTI_REFERENCE) {
				saveChangesOfMultiReference((IMultiReference<?, ?>)p, database);
			}
		}
	}
	
	//method
	private void saveChangesOfMultiReference(final IMultiReference<?, ?> multiReference, final Database database) {
		for (final var e : multiReference.getRefLocalEntries()) {
			saveChangeOfMultiReferenceEntry(e, database);
		}
	}
	
	//method
	private void saveChangeOfMultiReferenceEntry(
		final IMultiReferenceEntry<?, ?> multiReferenceEntry,
		final Database database
	) {
		
		final var multiReferenceEntryState = multiReferenceEntry.getState();
		
		switch (multiReferenceEntryState) {
			case NEW:
				
				final var entity = multiReferenceEntry.getRefParentMultiReference().getRefParentEntity();
				
				database.internalGetRefDataAndSchemaAdapter().insertMultiReferenceEntry(
					entity.getParentTableName(),
					entity.getId(),
					multiReferenceEntry.getRefParentMultiReference().getName(),
					multiReferenceEntry.getReferencedEntityId()
				);
				
				break;
			case DELETED:
				
				final var entity2 = multiReferenceEntry.getRefParentMultiReference().getRefParentEntity();
				
				database.internalGetRefDataAndSchemaAdapter().deleteMultiReferenceEntry(
					entity2.getParentTableName(),
					entity2.getId(),
					multiReferenceEntry.getRefParentMultiReference().getName(),
					multiReferenceEntry.getReferencedEntityId()
				);
				
				break;
			default:
				throw
				InvalidArgumentException.forArgumentNameAndArgument(
					"state of multi reference",
					multiReferenceEntryState
				);
		}
	}
}
