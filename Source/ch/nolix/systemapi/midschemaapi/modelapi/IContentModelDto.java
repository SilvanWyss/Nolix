package ch.nolix.systemapi.midschemaapi.modelapi;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectschemaapi.fieldproperty.ContentType;

public interface IContentModelDto {

  ContentType getContentType();

  DataType getDataType();
}
