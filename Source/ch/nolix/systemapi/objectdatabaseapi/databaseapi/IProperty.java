//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.requestapi.EmptinessRequestable;
import ch.nolix.coreapi.functionapi.requestapi.MandatoryRequestable;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IContentFieldDto;

//interface
public interface IProperty extends EmptinessRequestable, IDatabaseObject, MandatoryRequestable, Named {
	
	//method declaration
	boolean belongsToEntity();
	
	//method declaration
	IContainer<IProperty> getOriBackReferencingProperties();
	
	//method
	IColumn getOriParentColumn();
	
	//method declaration
	IEntity getOriParentEntity();
	
	//method declaration
	IContainer<IProperty> getOriReferencingProperties();
	
	//method declaration
	PropertyType getType();
	
	//method declaration
	boolean knowsParentColumn();
	
	//method declaration
	boolean referencesBackEntity(IEntity entity);
	
	//method declaration
	boolean referencesBackProperty(IProperty property);
	
	//method declaration
	boolean referencesEntity(IEntity entity);
	
	//method declaration
	boolean referencesUninsertedEntity();
	
	//method declaration
	void setUpdateAction(IAction updateAction);
	
	//method declaration
	IContentFieldDto technicalToContentField();
}
