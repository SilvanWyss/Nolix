//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

//own imports
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedPropertyTypeDto;

//interface
public interface IParameterizedPropertyType {
	
	//method declaration
	IBaseParameterizedBackReferenceType asBaseParametrizedBackReferenceType();
	
	//method declaration
	IBaseParameterizedReferenceType asBaseParametrizedReferenceType();
	
	//method declaration
	IBaseParameterizedValueType<?> asBaseParametrizedValueType();
	
	//method declaration
	DataType getDataType();
	
	//method declaration
	PropertyType getPropertyType();
	
	//method declaration
	boolean referencesTable(ITable table);
	
	//method declaration
	boolean referencesBackColumn(IColumn column);
	
	//method declaration
	IParameterizedPropertyTypeDto toDto();
}
