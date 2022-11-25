//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databasehelperapi;

//own imports
import ch.nolix.systemapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IRecordDTO;

//interface
public interface IEntityHelper extends IDatabaseObjectHelper {
	
	//method declaration
	boolean canBeDeleted(IEntity<?> entity);
	
	//method declaration
	boolean canBeInsertedIntoTable(IEntity<?> entity);
	
	//method declaration
	boolean containsMandatoryAndEmptyBaseValuesOrBaseReferences(IEntity<?> entity);
	
	//method declaration
	IEntityUpdateDTO createEntityUpdateDTOForEntity(IEntity<?> entity);
	
	//method declaration
	IEntityHeadDTO createRecordHeadDTOForEntity(IEntity<?> entity);
	
	//method declaration
	IRecordDTO createRecordFor(IEntity<?> entity);
	
	//method declaration
	boolean isReferenced(IEntity<?> entity);
	
	//method declaration
	<IMPL> boolean isReferencedInLocalData(IEntity<IMPL> entity);
	
	//method declaration
	<IMPL> boolean referencesGivenEntity(IEntity<IMPL> sourceEntity, IEntity<IMPL> entity);
	
	//method declaration
	boolean referencesUninsertedEntity(IEntity<?> entity);
}
