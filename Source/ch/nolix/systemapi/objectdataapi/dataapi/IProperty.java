//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//own imports
import ch.nolix.core.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.core.functionapi.IAction;
import ch.nolix.core.requestapi.EmptinessRequestable;
import ch.nolix.core.requestapi.MandatoryRequestable;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IContentFieldDTO;

//interface
public interface IProperty<IMPL> extends EmptinessRequestable, IDatabaseObject, MandatoryRequestable, Named {
	
	//method declaration
	boolean belongsToEntity();
	
	//method declaration
	IEntity<IMPL> getParentEntity();
	
	//method declaration
	PropertyType getType();
	
	//method declaration
	boolean isLinkedWithRealDatabase();
	
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
