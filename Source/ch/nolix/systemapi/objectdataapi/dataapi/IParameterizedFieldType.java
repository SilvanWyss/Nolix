package ch.nolix.systemapi.objectdataapi.dataapi;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public interface IParameterizedFieldType {

  IBaseParameterizedBackReferenceType<?> asBaseParameterizedBackReferenceType();

  IBaseParameterizedReferenceType<?> asBaseParameterizedReferenceType();

  IBaseParameterizedValueType<?> asBaseParameterizedValueType();

  boolean referencesTable(ITable<?> table);

  ContentType getFieldType();
}
