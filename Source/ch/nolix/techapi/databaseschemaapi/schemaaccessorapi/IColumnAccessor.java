//package declaration
package ch.nolix.techapi.databaseschemaapi.schemaaccessorapi;

//own imports
import ch.nolix.techapi.databaseschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;

//interface
public interface IColumnAccessor {
	
	//method declaration
	boolean currentColumnIsEmptyOnDatabase();
	
	//method declaration
	String getHeaderOfCurrentColumn();
	
	//method declaration
	void setHeaderOfCurrentColumnToDatabase(String header);
	
	//method declaration
	void setParametrizedPropertyTypeForCurrentColumnToDatabase(IParametrizedPropertyTypeDTO parametrizedPropertyType);

}
