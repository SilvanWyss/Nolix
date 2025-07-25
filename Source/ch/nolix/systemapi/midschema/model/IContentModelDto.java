package ch.nolix.systemapi.midschema.model;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.ContentType;

public interface IContentModelDto {

  ContentType getContentType();

  DataType getDataType();
}
