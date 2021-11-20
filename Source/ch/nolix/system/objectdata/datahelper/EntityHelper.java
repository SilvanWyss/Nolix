//package declaration
package ch.nolix.system.objectdata.datahelper;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ReferencedArgumentException;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.ITable;
import ch.nolix.techapi.objectdataapi.datahelperapi.IEntityHelper;

//class
public final class EntityHelper extends DatabaseObjectHelper implements IEntityHelper {
	
	//method
	@Override
	public void assertBelongsToTable(final IEntity<?> entity) {
		if (!entity.belongsToTable()) {
			throw new ArgumentDoesNotBelongToParentException(entity, ITable.class);
		}
	}
	
	//method
	@Override
	public void assertCanBeDeleted(IEntity<?> entity) {
		if (!canBeDeleted(entity)) {
			throw new InvalidArgumentException(entity, "cannot be deleted");
		}
	}
	
	//method
	@Override
	public void assertIsNotBackReferenced(final IEntity<?> entity) {
		if (entity.isBackReferenced()) {
			throw new InvalidArgumentException(entity, "is back referenced");
		}
	}
	
	//method
	@Override
	public void assertIsNotReferenced(final IEntity<?> entity) {
		if (isReferenced(entity)) {
			throw new ReferencedArgumentException(entity);
		}
	}
	
	//method
	@Override
	public boolean canBeDeleted(final IEntity<?> entity) {
		return (isLoaded(entity) && !isReferenced(entity));
	}
	
	//method
	@Override
	public boolean canBeInsertedIntoTable(final IEntity<?> entity) {
		return isNew(entity) && !referencesUninsertedEntity(entity);
	}
	
	//method
	@Override
	public boolean isReferenced(IEntity<?> entity) {
		return (isReferencedInLocalData(entity) || entity.isReferencedInPersistedData());
	}
	
	//method
	@Override
	public <IMPL> boolean isReferencedInLocalData(final IEntity<IMPL> entity) {
		return entity.getParentTable().getReferencingColumns().containsAny(rc -> rc.referencesInLocalData(entity));
	}
	
	//method
	@Override
	public boolean referencesUninsertedEntity(final IEntity<?> entity) {
		//TODO: Implement.
		return false;
	}
}
