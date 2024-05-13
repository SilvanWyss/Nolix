//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

//interface
public interface IParameterizedFieldType {

  //method declaration
  IBaseParameterizedBackReferenceType<?> asBaseParameterizedBackReferenceType();

  //method declaration
  IBaseParameterizedReferenceType<?> asBaseParameterizedReferenceType();

  //method declaration
  IBaseParameterizedValueType<?> asBaseParameterizedValueType();

  //method declaration
  boolean referencesTable(ITable<?> table);

  //method declaration
  ContentType getFieldType();
}
