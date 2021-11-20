//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Headered;
import ch.nolix.techapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IColumn<IMPL> extends Headered, IDatabaseObject {
	
	//method declaration
	IParametrizedPropertyType<IMPL> getParametrizedPropertyType();
	
	//method declaration
	ITable<IMPL> getParentTable();
	
	//method declaration
	boolean referencesInLocalData(IEntity<IMPL> entity);
}
