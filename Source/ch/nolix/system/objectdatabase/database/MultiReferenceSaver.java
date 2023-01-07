//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiReferenceEntry;

//class
final class MultiReferenceSaver {
	
	//method
	public void saveChangesOfMultiReference(final IMultiReference<?, ?> multiReference, final Database database) {
		for (final var le : multiReference.getRefLocalEntries()) {
			saveChangeOfMultiReferenceEntry(le, database);
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
				saveMultiReferenceEntryCreation(multiReferenceEntry, database);
				break;
			case DELETED:
				saveMultiReferenceEntryDeletion(multiReferenceEntry, database);
				break;
			default:
				throw
				InvalidArgumentException.forArgumentNameAndArgument(
					"state of multi reference",
					multiReferenceEntryState
				);
		}
	}
	
	//method
	private void saveMultiReferenceEntryCreation(
		final IMultiReferenceEntry<?, ?> multiReferenceEntry,
		final Database database
	) {
		
		final var entity = multiReferenceEntry.getRefParentMultiReference().getRefParentEntity();
		
		database.internalGetRefDataAndSchemaAdapter().insertMultiReferenceEntry(
			entity.getParentTableName(),
			entity.getId(),
			multiReferenceEntry.getRefParentMultiReference().getName(),
			multiReferenceEntry.getReferencedEntityId()
		);
	}
	
	//method
	private void saveMultiReferenceEntryDeletion(
		final IMultiReferenceEntry<?, ?> multiReferenceEntry,
		final Database database
	) {
		
		final var entity = multiReferenceEntry.getRefParentMultiReference().getRefParentEntity();
		
		database.internalGetRefDataAndSchemaAdapter().deleteMultiReferenceEntry(
			entity.getParentTableName(),
			entity.getId(),
			multiReferenceEntry.getRefParentMultiReference().getName(),
			multiReferenceEntry.getReferencedEntityId()
		);
	}
}
