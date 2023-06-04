//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiReferenceEntry;

//class
final class MultiReferenceSaver {
	
	//method
	public void saveChangesOfMultiReference(final IMultiReference<?> multiReference, final Database database) {
		for (final var le : multiReference.getOriLocalEntries()) {
			saveChangeOfMultiReferenceEntry(le, database);
		}
	}
	
	//method
	private void saveChangeOfMultiReferenceEntry(
		final IMultiReferenceEntry<?> multiReferenceEntry,
		final Database database
	) {
		
		final var multiReferenceEntryState = multiReferenceEntry.getState();
		
		switch (multiReferenceEntryState) {
			case NEW:
				saveMultiReferenceEntryCreation(multiReferenceEntry, database);
				break;
			case LOADED:
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
		final IMultiReferenceEntry<?> multiReferenceEntry,
		final Database database
	) {
		
		final var entity = multiReferenceEntry.getOriParentMultiReference().getOriParentEntity();
		
		database.internalGetRefDataAndSchemaAdapter().insertMultiReferenceEntry(
			entity.getParentTableName(),
			entity.getId(),
			multiReferenceEntry.getOriParentMultiReference().getName(),
			multiReferenceEntry.getReferencedEntityId()
		);
	}
	
	//method
	private void saveMultiReferenceEntryDeletion(
		final IMultiReferenceEntry<?> multiReferenceEntry,
		final Database database
	) {
		
		final var entity = multiReferenceEntry.getOriParentMultiReference().getOriParentEntity();
		
		database.internalGetRefDataAndSchemaAdapter().deleteMultiReferenceEntry(
			entity.getParentTableName(),
			entity.getId(),
			multiReferenceEntry.getOriParentMultiReference().getName(),
			multiReferenceEntry.getReferencedEntityId()
		);
	}
}
