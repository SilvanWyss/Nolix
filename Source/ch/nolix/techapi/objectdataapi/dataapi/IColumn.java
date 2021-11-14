//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.techapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IColumn<P extends IProperty<P>> extends IDatabaseObject {
	
	//method declaration
	IParametrizedPropertyType getParametrizedPropertyType();
	
	//method declaration
	ITable<?, ?, P> getParentTable();
	
	//method declaration
	boolean referencesInLocalData(IEntity<?, P> entity);
}
