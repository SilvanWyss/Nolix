package ch.nolix.systemapi.objectschemaapi.schemaapi;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedFieldTypeDto;

public interface IParameterizedFieldType {

  IBaseParameterizedBackReferenceType asBaseParameterizedBackReferenceType();

  IBaseParameterizedReferenceType asBaseParameterizedReferenceType();

  IBaseParameterizedValueType<?> asBaseParameterizedValueType();

  DataType getDataType();

  ContentType getFieldType();

  boolean referencesTable(ITable table);

  boolean referencesBackColumn(IColumn column);

  IParameterizedFieldTypeDto toDto();
}
