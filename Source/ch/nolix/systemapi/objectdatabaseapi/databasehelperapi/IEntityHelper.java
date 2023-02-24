//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databasehelperapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.INewEntityDTO;

//interface
public interface IEntityHelper extends IDatabaseObjectHelper {
	
	//method declaration
	boolean allNewAndEditedMandatoryPropertiesAreSet(IEntity entity);
	
	//method declaration
	boolean canBeDeleted(IEntity entity);
	
	//method declaration
	boolean canBeInsertedIntoTable(IEntity entity);
	
	//method declaration
	boolean containsMandatoryAndEmptyBaseValuesOrBaseReferences(IEntity entity);
	
	//method declaration
	IEntityUpdateDTO createEntityUpdateDTOForEntity(IEntity entity);
	
	//method declaration
	IEntityHeadDTO createEntityHeadDTOForEntity(IEntity entity);
	
	//method declaration
	INewEntityDTO createNewEntityDTOForEntity(IEntity entity);
	
	//method declaration
	 IContainer<IProperty> getRefBackReferencingProperties(IEntity entity);
	
	//method declaration
	 IContainer<? extends IProperty> getRefEditedProperties(IEntity entity);
	
	//method declaration
	 IContainer<? extends IProperty> getRefReferencingProperties(IEntity entity);
	
	//method declaration
	boolean isReferenced(IEntity entity);
	
	//method declaration
	 boolean isReferencedInLocalData(IEntity entity);
	
	//method declaration
	 boolean referencesGivenEntity(IEntity sourceEntity, IEntity entity);
	
	//method declaration
	boolean referencesUninsertedEntity(IEntity entity);
}
