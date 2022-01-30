//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

import ch.nolix.core.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IColumn<IMPL> extends IDatabaseObject, Named {
	
	//method declaration
	IParametrizedPropertyType<IMPL> getParametrizedPropertyType();
	
	//method declaration
	ITable<IMPL, IEntity<IMPL>> getParentTable();
}
