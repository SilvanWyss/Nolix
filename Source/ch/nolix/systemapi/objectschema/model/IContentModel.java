package ch.nolix.systemapi.objectschema.model;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

public interface IContentModel {

  FieldType getContentType();

  DataType getDataType();

  boolean referencesBackColumn(IColumn column);

  boolean referencesTable(ITable table);
}
