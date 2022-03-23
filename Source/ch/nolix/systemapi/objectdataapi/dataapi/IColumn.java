//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//own imports
import ch.nolix.core.attributeapi.mandatoryattributeapi.IdentifiedByString;
import ch.nolix.core.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IColumn<IMPL> extends IDatabaseObject, IdentifiedByString, Named {
	
	//method declaration
	IParametrizedPropertyType<IMPL> getParametrizedPropertyType();
	
	//method declaration
	ITable<IMPL, IEntity<IMPL>> getParentTable();
}
