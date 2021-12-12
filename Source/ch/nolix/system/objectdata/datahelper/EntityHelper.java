//package declaration
package ch.nolix.system.objectdata.datahelper;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ReferencedArgumentException;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.system.sqlrawobjectdata.datadto.RecordDeletionDTO;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.ITable;
import ch.nolix.techapi.objectdataapi.datahelperapi.IEntityHelper;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordDeletionDTO;

//class
public class EntityHelper extends DatabaseObjectHelper implements IEntityHelper {
	
	//method
	@Override
	public final void assertBelongsToTable(final IEntity<?> entity) {
		if (!entity.belongsToTable()) {
			throw new ArgumentDoesNotBelongToParentException(entity, ITable.class);
		}
	}
	
	//method
	@Override
	public final void assertCanBeDeleted(final IEntity<?> entity) {
		if (!canBeDeleted(entity)) {
			throw new InvalidArgumentException(entity, "cannot be deleted");
		}
	}
	
	//method
	@Override
	public final void assertHasSaveStamp(final IEntity<?> entity) {
		if (!entity.hasSaveStamp()) {
			throw new ArgumentDoesNotHaveAttributeException(entity, LowerCaseCatalogue.SAVE_STAMP);
		}
	}
	
	//method
	@Override
	public final void assertIsNotBackReferenced(final IEntity<?> entity) {
		if (entity.isBackReferenced()) {
			throw new InvalidArgumentException(entity, "is back referenced");
		}
	}
	
	//method
	@Override
	public final void assertIsNotReferenced(final IEntity<?> entity) {
		if (isReferenced(entity)) {
			throw new ReferencedArgumentException(entity);
		}
	}
	
	//method
	@Override
	public final boolean canBeDeleted(final IEntity<?> entity) {
		return (isLoaded(entity) && !isReferenced(entity));
	}
	
	//method
	@Override
	public final boolean canBeInsertedIntoTable(final IEntity<?> entity) {
		return isNew(entity) && !referencesUninsertedEntity(entity);
	}
	
	//method
	@Override
	public final IRecordDeletionDTO createRecordDeletionDTOForEntity(IEntity<?> entity) {
		return new RecordDeletionDTO(entity.getId(), entity.getSaveStamp());
	}
	
	//method
	@Override
	public final boolean isReferenced(final IEntity<?> entity) {
		return (isReferencedInLocalData(entity) || entity.isReferencedInPersistedData());
	}
	
	//method
	@Override
	public final <IMPL> boolean isReferencedInLocalData(final IEntity<IMPL> entity) {
		return entity.getParentTable().getReferencingColumns().containsAny(rc -> rc.referencesInLocalData(entity));
	}
	
	//method
	@Override
	public final boolean referencesUninsertedEntity(final IEntity<?> entity) {
		//TODO: Implement.
		return false;
	}
}
