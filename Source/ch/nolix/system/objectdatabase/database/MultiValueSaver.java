//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiValue;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiValueEntry;

//class
final class MultiValueSaver {
	
	//method
	public void saveChangesOfMultiValue(final IMultiValue<?> multiValue, final Database database) {
		for (final var le : multiValue.getOriLocalEntries()) {
			saveChangeOfMultiValueEntry(le, database);
		}
	}
	
	//method
	private void saveChangeOfMultiValueEntry(final IMultiValueEntry<?> multiValueEntry, final Database database) {
		
		final var multiValueEntryState = multiValueEntry.getState();
		
		switch (multiValueEntryState) {
			case NEW:
				saveMultiValueEntryCreation(multiValueEntry, database);
				break;
			case DELETED:
				saveMultiValueEntryDeletion(multiValueEntry, database);
				break;
			default:
				throw
				InvalidArgumentException.forArgumentNameAndArgument(
					"state of multi value",
					multiValueEntryState
				);
		}
	}
	
	//method
	private void saveMultiValueEntryCreation(final IMultiValueEntry<?> multiValueEntry, final Database database) {
		
		final var entity = multiValueEntry.getOriParentMultiValue().getOriParentEntity();
		
		database.internalGetRefDataAndSchemaAdapter().insertMultiValueEntry(
			entity.getParentTableName(),
			entity.getId(),
			multiValueEntry.getOriParentMultiValue().getName(),
			multiValueEntry.getOriValue().toString()
		);
	}
	
	//method
	private void saveMultiValueEntryDeletion(final IMultiValueEntry<?> multiValueEntry, final Database database) {
		
		final var entity = multiValueEntry.getOriParentMultiValue().getOriParentEntity();
		
		database.internalGetRefDataAndSchemaAdapter().deleteMultiValueEntry(
			entity.getParentTableName(),
			entity.getId(),
			multiValueEntry.getOriParentMultiValue().getName(),
			multiValueEntry.toString()
		);
	}
}
