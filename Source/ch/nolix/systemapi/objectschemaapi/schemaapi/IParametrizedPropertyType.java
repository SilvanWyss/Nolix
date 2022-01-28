//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;

//interface
public interface IParametrizedPropertyType<IMPL, DT> {
	
	//method declaration
	IBaseParametrizedBackReferenceType<IMPL> asBaseParametrizedBackReferenceType();
	
	//method declaration
	IBaseParametrizedReferenceType<IMPL> asBaseParametrizedReferenceType();
	
	//method declaration
	IBaseParametrizedValueType<IMPL, ?> asBaseParametrizedValueType();
	
	//method declaration
	Class<DT> getDataType();
	
	//method declaration
	PropertyType getPropertyType();
	
	//method declaration
	boolean referencesTable(ITable<?> table);
	
	//method declaration
	boolean referencesBackColumn(IColumn<?> column);
	
	//method declaration
	IParametrizedPropertyTypeDTO toDTO();
}
