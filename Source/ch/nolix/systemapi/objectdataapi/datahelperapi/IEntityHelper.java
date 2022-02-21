//package declaration
package ch.nolix.systemapi.objectdataapi.datahelperapi;

//own imports
import ch.nolix.systemapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IEntityHeadDTO;

//interface
public interface IEntityHelper extends IDatabaseObjectHelper {
	
	//method declaration
	void assertBelongsToTable(IEntity<?> entity);
	
	//method declaration
	void assertCanBeDeleted(IEntity<?> entity);
	
	//method declaration
	void assertDoesNotBelongToTable(IEntity<?> entity);
	
	//method declaration
	void assertHasSaveStamp(IEntity<?> entity);
	
	//method declaration
	void assertIsNotReferenced(IEntity<?> entity);
	
	//method declaration
	boolean canBeDeleted(IEntity<?> entity);
	
	//method declaration
	boolean canBeInsertedIntoTable(IEntity<?> entity);
	
	//method declaration
	boolean containsMandatoryAndEmptyBaseValuesOrBaseReferences(IEntity<?> entity);
	
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
