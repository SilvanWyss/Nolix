package ch.nolix.systemapi.midschema.model;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

public interface IContentModelDto {

  FieldType getFieldType();

  DataType getDataType();
}
