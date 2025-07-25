package ch.nolix.systemapi.objectschema.model;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.ContentType;

public interface IContentModel {

  ContentType getContentType();

  DataType getDataType();

  boolean referencesBackColumn(IColumn column);

  boolean referencesTable(ITable table);
}
