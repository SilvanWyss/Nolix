package ch.nolix.systemapi.objectschemaapi.schemaapi;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IContentModelDto;

public interface IContentModel {

  IAbstractBackReferenceModel asBaseParameterizedBackReferenceType();

  IAbstractReferenceModel asBaseParameterizedReferenceType();

  IAbstractValueModel<?> asBaseParameterizedValueType();

  DataType getDataType();

  ContentType getContentType();

  boolean referencesTable(ITable table);

  boolean referencesBackColumn(IColumn column);

  IContentModelDto toDto();
}
