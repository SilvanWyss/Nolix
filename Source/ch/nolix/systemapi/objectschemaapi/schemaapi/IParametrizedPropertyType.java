//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

//own imports
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;

//interface
public interface IParametrizedPropertyType {
	
	//method declaration
	IBaseParametrizedBackReferenceType asBaseParametrizedBackReferenceType();
	
	//method declaration
	IBaseParametrizedReferenceType asBaseParametrizedReferenceType();
	
	//method declaration
	IBaseParametrizedValueType<?> asBaseParametrizedValueType();
	
	//method declaration
	DataType getDataType();
	
	//method declaration
	PropertyType getPropertyType();
	
	//method declaration
	boolean referencesTable(ITable table);
	
	//method declaration
	boolean referencesBackColumn(IColumn column);
	
	//method declaration
	IParametrizedPropertyTypeDTO toDTO();
}
