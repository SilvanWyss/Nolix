//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Named;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.requestuniversalapi.EmptinessRequestable;
import ch.nolix.coreapi.functionapi.requestuniversalapi.MandatoryRequestable;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IContentFieldDTO;

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
	@Override
	boolean isLinkedWithRealDatabase();
	
	//method declaration
	boolean knowsParentColumn();
	
	//method declaration
	boolean references(IEntity<?> entity);
	
	//method declaration
	boolean referencesBack(IEntity<?> entity);
	
	//method declaration
	boolean referencesBackProperty(IProperty<?> property);
	
	//method declaration
	boolean canReferencesBackProperty(IProperty<?> property);
	
	//method declaration
	boolean referencesUninsertedEntity();
	
	//method declaration
	void setUpdateAction(IAction updateAction);
	
	//method declaration
	IContentFieldDTO technicalToContentField();
}
