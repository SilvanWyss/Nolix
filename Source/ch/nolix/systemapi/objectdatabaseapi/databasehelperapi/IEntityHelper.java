//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databasehelperapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.systemapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.INewEntityDTO;

//interface
public interface IEntityHelper extends IDatabaseObjectHelper {
	
	//method declaration
	boolean allNewAndEditedMandatoryPropertiesAreSet(IEntity<?> entity);
	
	//method declaration
	boolean canBeDeleted(IEntity<?> entity);
	
	//method declaration
	boolean canBeInsertedIntoTable(IEntity<?> entity);
	
	//method declaration
	boolean containsMandatoryAndEmptyBaseValuesOrBaseReferences(IEntity<?> entity);
	
	//method declaration
	IEntityUpdateDTO createEntityUpdateDTOForEntity(IEntity<?> entity);
	
	//method declaration
	IEntityHeadDTO createEntityHeadDTOForEntity(IEntity<?> entity);
	
	//method declaration
	INewEntityDTO createNewEntityDTOForEntity(IEntity<?> entity);
	
	//method declaration
	<IMPL> IContainer<IProperty<IMPL>> getRefBackReferencingProperties(IEntity<IMPL> entity);
	
	//method declaration
	<IMPL> IContainer<? extends IProperty<IMPL>> getRefEditedProperties(IEntity<IMPL> entity);
	
	//method declaration
	<IMPL> IContainer<? extends IProperty<IMPL>> getRefReferencingProperties(IEntity<IMPL> entity);
	
	//method declaration
	boolean isReferenced(IEntity<?> entity);
	
	//method declaration
	<IMPL> boolean isReferencedInLocalData(IEntity<IMPL> entity);
	
	//method declaration
	<IMPL> boolean referencesGivenEntity(IEntity<IMPL> sourceEntity, IEntity<IMPL> entity);
	
	//method declaration
	boolean referencesUninsertedEntity(IEntity<?> entity);
}
