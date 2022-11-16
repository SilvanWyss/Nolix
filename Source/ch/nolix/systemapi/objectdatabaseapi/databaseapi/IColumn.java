//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.IdentifiedByString;
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Named;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IColumn<IMPL> extends IDatabaseObject, IdentifiedByString, Named {
	
	//method declaration
	IParametrizedPropertyType<IMPL> getParametrizedPropertyType();
	
	//method declaration
	ITable<IMPL, IEntity<IMPL>> getParentTable();
	
	//method
	boolean technicalContainsGivenValueInPersistedData(String value);
}
