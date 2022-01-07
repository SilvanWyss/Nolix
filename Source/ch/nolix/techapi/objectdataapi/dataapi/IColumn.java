//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.techapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IColumn<IMPL> extends IDatabaseObject, Named {
	
	//method declaration
	IParametrizedPropertyType<IMPL> getParametrizedPropertyType();
	
	//method declaration
	ITable<IMPL, IEntity<IMPL>> getParentTable();
}
