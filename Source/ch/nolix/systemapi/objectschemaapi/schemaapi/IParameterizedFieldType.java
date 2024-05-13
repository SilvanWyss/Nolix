//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.fieldapi.mainapi.FieldType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedFieldTypeDto;

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
  FieldType getFieldType();

  //method declaration
  boolean referencesTable(ITable table);

  //method declaration
  boolean referencesBackColumn(IColumn column);

  //method declaration
  IParameterizedFieldTypeDto toDto();
}
