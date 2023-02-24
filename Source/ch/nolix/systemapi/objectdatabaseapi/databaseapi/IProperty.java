//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Named;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.requestuniversalapi.EmptinessRequestable;
import ch.nolix.coreapi.functionapi.requestuniversalapi.MandatoryRequestable;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IContentFieldDTO;

//interface
public interface IProperty extends EmptinessRequestable, IDatabaseObject, MandatoryRequestable, Named {
	
	//method declaration
	boolean belongsToEntity();
	
	//method declaration
	IContainer<IProperty> getRefBackReferencingProperties();
	
	//method
	IColumn getRefParentColumn();
	
	//method declaration
	IEntity getRefParentEntity();
	
	//method declaration
	IContainer<IProperty> getRefReferencingProperties();
	
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
	IContentFieldDTO technicalToContentField();
}
