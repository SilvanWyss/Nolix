//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

import ch.nolix.systemapi.fieldapi.datatypeapi.DataType;
import ch.nolix.systemapi.fieldapi.mainapi.FieldType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedPropertyTypeDto;

//interface
public interface IParameterizedFieldType {

  //method declaration
  IBaseParameterizedBackReferenceType asBaseParameterizedBackReferenceType();

  //method declaration
  IBaseParameterizedReferenceType asBaseParameterizedReferenceType();

  //method declaration
  IBaseParameterizedValueType<?> asBaseParameterizedValueType();

  //method declaration
  DataType getDataType();

  //method declaration
  FieldType getPropertyType();

  //method declaration
  boolean referencesTable(ITable table);

  //method declaration
  boolean referencesBackColumn(IColumn column);

  //method declaration
  IParameterizedPropertyTypeDto toDto();
}
