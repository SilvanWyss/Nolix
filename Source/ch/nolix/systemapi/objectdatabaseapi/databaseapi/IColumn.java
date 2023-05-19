//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Identified;
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Named;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IColumn extends IDatabaseObject, Identified, Named {
	
	//method declaration
	IParametrizedPropertyType getParametrizedPropertyType();
	
	//method declaration
	ITable<IEntity> getOriParentTable();
	
	//method
	boolean technicalContainsGivenValueInPersistedData(String value);
}
