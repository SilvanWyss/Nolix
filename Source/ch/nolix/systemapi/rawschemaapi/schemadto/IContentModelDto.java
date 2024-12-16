package ch.nolix.systemapi.rawschemaapi.schemadto;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public interface IContentModelDto {

  ContentType getContentType();

  DataType getDataType();
}
