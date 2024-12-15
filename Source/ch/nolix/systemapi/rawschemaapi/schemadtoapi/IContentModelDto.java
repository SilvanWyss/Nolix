package ch.nolix.systemapi.rawschemaapi.schemadtoapi;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public interface IContentModelDto {

  DataType getDataType();

  ContentType getFieldType();
}
