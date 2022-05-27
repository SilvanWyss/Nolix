//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

import ch.nolix.core.attributeuniversalapi.mandatoryattributeuniversalapi.Named;
import ch.nolix.core.functionuniversalapi.IAction;
import ch.nolix.core.requestapi.EmptinessRequestable;
import ch.nolix.core.requestapi.MandatoryRequestable;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IContentFieldDTO;

//interface
public interface IProperty<IMPL> extends EmptinessRequestable, IDatabaseObject, MandatoryRequestable, Named {
	
	//method declaration
	boolean belongsToEntity();
	
	//method
	IColumn<IMPL> getParentColumn();
	
	//method declaration
	IEntity<IMPL> getParentEntity();
	
	//method declaration
	PropertyType getType();
	
	//method declaration
	boolean isLinkedWithRealDatabase();
	
	//method declaration
	boolean knowsParentColumn();
	
	//method declaration
	boolean references(IEntity<?> entity);
	
	//method declaration
	boolean referencesBack(IEntity<?> entity);
	
	//method declaration
	boolean referencesUninsertedEntity();
	
	//method declaration
	void setUpdateAction(IAction updateAction);
	
	//method declaration
	IContentFieldDTO technicalToContentField();
}
