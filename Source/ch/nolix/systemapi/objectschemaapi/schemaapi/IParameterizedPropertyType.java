//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

//own imports
import ch.nolix.systemapi.entitypropertyapi.datatypeapi.DataType;
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedPropertyTypeDto;

//interface
public interface IParameterizedPropertyType {

  //method declaration
  IBaseParameterizedBackReferenceType asBaseParameterizedBackReferenceType();

  //method declaration
  IBaseParameterizedReferenceType asBaseParameterizedReferenceType();

  //method declaration
  IBaseParameterizedValueType<?> asBaseParameterizedValueType();

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
