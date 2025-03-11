package ch.nolix.systemapi.objectschemaapi.modelapi;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectschemaapi.fieldproperty.ContentType;

public interface IContentModel {

  ContentType getContentType();

  DataType getDataType();

  boolean referencesBackColumn(IColumn column);

  boolean referencesTable(ITable table);
}
