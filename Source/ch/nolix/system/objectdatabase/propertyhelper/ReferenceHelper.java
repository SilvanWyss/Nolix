//package declaration
package ch.nolix.system.objectdatabase.propertyhelper;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.sqlrawdata.datadto.ContentFieldDTO;
import ch.nolix.system.sqlrawdata.datadto.RecordUpdateDTO;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IReference;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IReferenceHelper;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordUpdateDTO;

//class
public final class ReferenceHelper extends PropertyHelper implements IReferenceHelper {
	
	//method
	@Override
	public void assertCanSetGivenEntity(final IReference<?, ?> reference, final IEntity<?> entity) {
		if (!canSetGivenEntity(reference, entity)) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(reference, "cannot reference the given entity");
		}
	}
	
	//method
	@Override
	public boolean canSetGivenEntity(final IReference<?, ?> reference, final IEntity<?> entity) {
		return
		canSetEntity(reference)
		&& entity != null
		&& entity.isOpen()
		&& reference.getReferencedTableName().equals(entity.getParentTableName());
	}
	
	//method
	@Override
	public IRecordUpdateDTO createRecordUpdateDTOForSetEntity(IReference<?, ?> reference, IEntity<?> entity) {
		
		final var parentEntity = reference.getParentEntity();
		
		return new RecordUpdateDTO(
			parentEntity.getId(),
			parentEntity.getSaveStamp(),
			new ContentFieldDTO(reference.getName(), entity.getId())
		);
	}
	
	//method
	private boolean canSetEntity(final IReference<?, ?> reference) {
		return
		reference != null
		&& reference.belongsToEntity()
		&& reference.getParentEntity().isOpen();
	}
}
